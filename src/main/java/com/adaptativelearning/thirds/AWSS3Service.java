package com.adaptativelearning.thirds;

import com.adaptativelearning.configuration.MediaContentDTO;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AWSS3Service
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AWSS3Service.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.endpoint_url}")
    private String endpointUrl;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public MediaContentDTO uploadFile(final MultipartFile multipartFile)
    throws Exception
    {
        LOGGER.info("File upload in progress.");
        MediaContentDTO mediaContentDTO = new MediaContentDTO();
        try
        {
            final File file = convertMultiPartFileToFile(multipartFile);

            mediaContentDTO.setMime(Files.probeContentType(file.toPath()));

            String fileName = uploadFileToS3Bucket(bucketName, file);

            mediaContentDTO.setReference(endpointUrl + fileName);
            mediaContentDTO.setTypeContent("image");

            LOGGER.info("File upload is completed.");
            file.delete();

            return mediaContentDTO;
        }
        catch (final AmazonServiceException | IOException ex)
        {
            LOGGER.info("File upload is failed.");
            LOGGER.error("Error= {} while uploading file.", ex.getMessage());
            throw ex;
        }
    }

    private File convertMultiPartFileToFile(final MultipartFile multipartFile)
    {
        final File file = new File(multipartFile.getOriginalFilename().replace(" ", "_"));
        try (final FileOutputStream outputStream = new FileOutputStream(file))
        {
            outputStream.write(multipartFile.getBytes());
        }
        catch (final IOException ex)
        {
            LOGGER.error("Error converting the multi-part file to file= ", ex.getMessage());
        }
        return file;
    }

    private String uploadFileToS3Bucket(final String bucketName, final File file)
    {
        final String uniqueFileName = LocalDateTime.now() + "_" + file.getName();
        LOGGER.info("Uploading file with name= " + uniqueFileName);
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
            uniqueFileName,
            file);
        amazonS3.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead));

        return uniqueFileName;
    }

    public void deleteFile(String fileUrl)
    {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        amazonS3.deleteObject(new DeleteObjectRequest(bucketName, fileName));
    }
}
