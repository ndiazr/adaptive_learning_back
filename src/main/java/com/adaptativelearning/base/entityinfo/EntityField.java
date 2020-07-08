package com.adaptativelearning.base.entityinfo;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class EntityField
{
    private String name;
    private String dataType;
    private boolean hidden;
    private boolean editable;
    private boolean nullable;
    private boolean pk;
    private boolean identity;
    private String defaultValue;
    private Integer size;
    private Number maxValue;
    private boolean percentage;
    private boolean numeric;
    private Integer precision;
    private Integer scalar;
    private Boolean decimal;
    private boolean dateField;
    private List<Map<String, Object>> options;
}
