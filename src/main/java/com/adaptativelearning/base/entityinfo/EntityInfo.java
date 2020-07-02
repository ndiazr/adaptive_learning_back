package com.adaptativelearning.base.entityinfo;

import java.util.List;
import lombok.Data;

@Data
public class EntityInfo
{
    private String className;
    private List<EntityField> fields;
}
