package com.adaptativelearning.base.entityinfo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LineText
{
    boolean hidden() default false;

    boolean hiddenInTable() default false;

    boolean editable() default true;

    boolean date() default false;

    boolean percentage() default false;

    int maxValue() default -1;

    String defaultValue() default "";
}
