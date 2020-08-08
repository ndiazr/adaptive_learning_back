package com.adaptativelearning.question;

import static java.lang.String.format;

import com.adaptativelearning.base.entityinfo.EntityInfo;
import com.adaptativelearning.base.entityinfo.EntityInfoService;
import com.adaptativelearning.base.validators.NullValidatorBuilder;
import com.adaptativelearning.configuration.MediaContentDTO;
import com.adaptativelearning.mediacontent.MediaContent;
import com.adaptativelearning.mediacontent.MediaContentService;
import com.adaptativelearning.thirds.AWSS3Service;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/questions")
public class QuestionController
{
    private static final String NOT_FOUND_ERROR_FORMAT = "The resource %s was not found";

    @Autowired
    private AWSS3Service awss3Service;

    @Autowired
    private MediaContentService mediaContentService;

    @Autowired
    private EntityInfoService entityInfoService;

    @Autowired
    private QuestionService questionService;

    @ApiOperation(value = "Creates a resource", code = 201)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<Question> createResource(
        @RequestPart(value = "file", required = false) MultipartFile multipartFile,
        @Valid @RequestBody Question requestEntity)
    throws Exception
    {
        if (multipartFile != null)
        {
            MediaContentDTO mediaContentDTO = awss3Service.uploadFile(multipartFile);

            MediaContent mediaContent = new MediaContent();
            mediaContent.setMime(mediaContentDTO.getMime());
            mediaContent.setRefContent(mediaContentDTO.getReference());
            mediaContent.setTypeContent(mediaContentDTO.getTypeContent());

            mediaContent = mediaContentService.save(mediaContent);

            requestEntity.setIdContent(mediaContent.getId());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.save(requestEntity));
    }

    /**
     * Retrieve all resources for base entity.
     *
     * @return The list of resources.
     */
    @ApiOperation(value = "Retrieve all resources")
    @GetMapping()
    public ResponseEntity<List<Question>> getAllResources()
    {
        return ResponseEntity.ok(questionService.getAll());
    }

    /**
     * Retrieve all resources for base entity with pagination.
     *
     * @param page Number of the page.
     * @param size Number of elements per page.
     * @param sortBy Name of the field to sort.
     * @param sortType Type of the sort, asc or desc.
     *
     * @return The Page of resources.
     */
    @ApiOperation(value = "Retrieve all resources with pagination")
    @GetMapping("/pageable")
    public ResponseEntity<Page<Question>> getAllResources(@RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size,
        @RequestParam(defaultValue = "id", required = false) String sortBy,
        @RequestParam(defaultValue = "asc", required = false) String sortType)
    {
        Page<Question> baseEntityList = questionService.getAll(page, size, sortBy, sortType);
        return ResponseEntity.ok(baseEntityList);
    }

    /**
     * Find a resource of base entity by id.
     *
     * @param id The id of the resource.
     *
     * @return The resource found.
     */
    @ApiOperation(value = "Find a resource by his id")
    @ApiResponse(code = 404, message = "The resource was not found")
    @GetMapping(value = "{id}")
    public ResponseEntity<Question> findResourceById(@PathVariable() Integer id)
    {
        Question foundObject = validateObject(id);
        return ResponseEntity.ok(foundObject);
    }

    /**
     * Updates a resource.
     *
     * @param id The resource id to update.
     * @param requestEntity The request entity to update.
     *
     * @return The resource updated.
     */
    @ApiOperation(value = "Updates a resource")
    @ApiResponse(code = 404, message = "The resource was not found")
    @PutMapping(value = "{id}")
    public ResponseEntity<Question> updateResource(
        @RequestPart(value = "file", required = false) MultipartFile multipartFile,
        @PathVariable() Integer id,
        @RequestBody Question requestEntity)
    throws Exception
    {
        if (multipartFile != null)
        {
            MediaContent mediaContent = mediaContentService.findById(requestEntity.getIdContent());
            awss3Service.deleteFile(mediaContent.getRefContent());

            MediaContentDTO mediaContentDTO = awss3Service.uploadFile(multipartFile);

            mediaContent.setMime(mediaContentDTO.getMime());
            mediaContent.setRefContent(mediaContentDTO.getReference());
            mediaContent.setTypeContent(mediaContentDTO.getTypeContent());

            mediaContentService.update(mediaContent.getId(), mediaContent);
        }
        Question baseEntityUpdated = questionService.update(id, requestEntity);

        NullValidatorBuilder.builder().httpStatus(HttpStatus.NOT_FOUND).message(format(NOT_FOUND_ERROR_FORMAT,
            id)).validate(baseEntityUpdated);

        Question baseEntityResponse = questionService.findById(baseEntityUpdated.getId());

        return ResponseEntity.ok(baseEntityResponse);
    }

    /**
     * Deletes a resource of base entity by id.
     *
     * @param id The id of the resource.
     */
    @ApiOperation(value = "Deletes a resource by his id")
    @ApiResponse(code = 404, message = "The resource was not found")
    @DeleteMapping(value = "{id}")
    public void deleteResource(@PathVariable() Integer id)
    {
        Question foundObject = validateObject(id);
        questionService.delete(foundObject);

        if (foundObject.getMediaContent() != null)
        {
            awss3Service.deleteFile(foundObject.getMediaContent().getRefContent());
            mediaContentService.delete(foundObject.getMediaContent());
        }
    }

    /**
     * Retrieve all resources for base entity.
     *
     * @return The list of resources.
     */
    @ApiOperation(value = "Information of the resource")
    @GetMapping("/info")
    public ResponseEntity<EntityInfo> getEntityInfo()
    {
        entityInfoService.setBaseEntityClass(Question.class);
        return ResponseEntity.ok(entityInfoService.geEntityInfo());
    }

    /**
     * Validate if a user exists or throws and exception.
     *
     * @param id The resource id to validate.
     *
     * @return The entity found.
     */
    public Question validateObject(Integer id)
    {
        Question foundObject = questionService.findById(id);

        NullValidatorBuilder.builder().httpStatus(HttpStatus.NOT_FOUND).message(format(NOT_FOUND_ERROR_FORMAT,
            id)).validate(foundObject);

        return foundObject;
    }
}
