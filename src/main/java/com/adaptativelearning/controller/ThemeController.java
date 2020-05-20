package com.adaptativelearning.controller;

import com.adaptativelearning.base.BaseController;
import com.adaptativelearning.theme.Theme;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/themes")
public class ThemeController extends BaseController<Theme, Integer, Theme, Theme>
{
    public ThemeController()
    {
        super(Theme.class, Theme.class, Theme.class);
    }
}
