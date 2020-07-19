package com.adaptativelearning.mediacontent;

import com.adaptativelearning.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/media-content")
public class MediaContentController
    extends BaseController<MediaContent, Integer, MediaContent, MediaContent>
{
    public MediaContentController()
    {
        super(MediaContent.class, MediaContent.class, MediaContent.class);
    }
}
