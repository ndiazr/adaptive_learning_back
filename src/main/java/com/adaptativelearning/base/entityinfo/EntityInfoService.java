package com.adaptativelearning.base.entityinfo;

import static org.apache.commons.lang3.reflect.FieldUtils.getFieldsListWithAnnotation;

import com.adaptativelearning.base.entityinfo.annotations.DropDown;
import com.adaptativelearning.base.entityinfo.annotations.LineText;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EntityInfoService
{
    private static final String LINE_TEXT = "lineText";
    private static final String DROP_DOWN = "dropDown";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Setter
    private Class baseEntityClass;

    public EntityInfo geEntityInfo()
    {
        EntityInfo entityInfo = new EntityInfo();
        entityInfo.setClassName(baseEntityClass.getName());

        Field[] allFields = baseEntityClass.getDeclaredFields();
        List<EntityField> fields = new ArrayList<>();
        Field idField = getFieldsListWithAnnotation(baseEntityClass, Id.class).stream().findFirst()
            .orElse(null);
        Field generatedField = getFieldsListWithAnnotation(baseEntityClass, Generated.class)
            .stream().findFirst().orElse(null);
        Field generatedValueField = getFieldsListWithAnnotation(baseEntityClass,
            GeneratedValue.class).stream().findFirst().orElse(null);

        Field embeddedId = getFieldsListWithAnnotation(baseEntityClass, EmbeddedId.class).stream()
            .findFirst().orElse(null);
        addEmbeddedIdPk(embeddedId, fields);
        for (Field field : allFields)
        {
            EntityField entityField = new EntityField();
            entityField.setName(field.getName());
            if (field.equals(idField))
            {
                entityField.setPk(true);
            }
            if (field.equals(generatedField) || field.equals(generatedValueField))
            {
                entityField.setIdentity(true);
            }
            typeInfo(entityField, field);
            if (entityField.getDataType() != null)
            {
                fields.add(entityField);
            }
        }
        entityInfo.setFields(fields);
        return entityInfo;
    }

    private void addEmbeddedIdPk(Field embeddedId, List<EntityField> fields)
    {
        if (embeddedId != null)
        {

            Field[] allFieldsEmbbed = embeddedId.getType().getDeclaredFields();

            for (Field field : allFieldsEmbbed)
            {
                EntityField entityField = new EntityField();
                entityField.setName(field.getName());
                entityField.setPk(true);
                typeInfo(entityField, field);
                if (entityField.getDataType() != null)
                {
                    fields.add(entityField);
                }
            }
        }
    }

    private void typeInfo(EntityField entityField, Field field)
    {
        LineText lineText = field.getAnnotation(LineText.class);
        Column column = field.getAnnotation(Column.class);
        if (lineText != null && column != null)
        {
            entityField.setDataType(LINE_TEXT);
            entityField.setEditable(lineText.editable());
            entityField.setHidden(lineText.hidden());
            entityField.setNullable(column.nullable());
            entityField.setSize(column.length());
            entityField.setMaxValue(lineText.maxValue());
            entityField.setPercentage(lineText.percentage());
            entityField.setDefaultValue(lineText.defaultValue());
            entityField.setDateField(lineText.date());
        }
        DropDown dropDown = field.getAnnotation(DropDown.class);
        if (dropDown != null && column != null)
        {
            entityField.setDataType(DROP_DOWN);
            entityField.setEditable(dropDown.editable());
            entityField.setHidden(dropDown.hidden());
            entityField.setOptions(getOptions(dropDown.query()));
        }
        if (Number.class.isAssignableFrom(field.getType()))
        {
            entityField.setNumeric(true);
            if (lineText != null && lineText.maxValue() == -1)
            {
                entityField.setMaxValue(getMaxValue(field.getType(), column, entityField));
            }
        }
        if (Boolean.class.isAssignableFrom(field.getType()))
        {
            entityField.setNumeric(true);
        }
    }

    private Number getMaxValue(Class dataType, Column column, EntityField entityField)
    {
        if (Integer.class.isAssignableFrom(dataType))
        {
            return Integer.MAX_VALUE;
        }
        if (Short.class.isAssignableFrom(dataType))
        {
            return Short.MAX_VALUE;
        }
        entityField.setDecimal(true);
        entityField.setPrecision(column.precision());
        entityField.setScalar(column.scale());

        return -1;
    }

    private Map<Object, Object> getOptions(String sql)
    {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        HashMap<Object, Object> options = new HashMap<>();
        mapList.forEach(c -> {
            List<Object> list = new ArrayList<>(c.values());
            options.put(list.get(0), list.get(1));
        });
        return options;
    }
}
