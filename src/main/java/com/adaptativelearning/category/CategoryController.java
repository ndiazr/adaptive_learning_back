package com.adaptativelearning.category;

import com.adaptativelearning.base.BaseController;
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
