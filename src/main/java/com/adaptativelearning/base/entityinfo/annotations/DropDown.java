package com.adaptativelearning.base.entityinfo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DropDown
{
    boolean hidden() default false;

    boolean editable() default true;

    String query() default "";
}
