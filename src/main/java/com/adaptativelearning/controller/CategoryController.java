package com.adaptativelearning.controller;

import com.adaptativelearning.base.BaseController;
import com.adaptativelearning.category.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController extends BaseController<Category, Integer, Category, Category>
{
    public CategoryController()
    {
        super(Category.class, Category.class, Category.class);
    }
}
