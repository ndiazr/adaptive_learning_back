package com.adaptativelearning.base;

import static org.apache.commons.lang3.reflect.FieldUtils.getFieldsListWithAnnotation;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ReflectionUtils;

public class BaseService<T extends BaseEntity, I extends Serializable>
{
    @Autowired
    private BaseRepository<T, I> repository;

    /**
     * Retrieve all resources for base entity.
     *
     * @return The list of resources.
     */
    public List<T> getAll()
    {
        return repository.findAll();
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
    public Page<T> getAll(Integer page, Integer size, String sortBy, String sortType)
    {
        Pageable paging = PageRequest.of(page, size);

        if (sortBy != null && sortType != null)
        {
            if (sortType.equals("asc"))
            {
                paging = PageRequest.of(page, size, Sort.by(sortBy).ascending());
            }
            else if (sortType.equals("desc"))
            {
                paging = PageRequest.of(page, size, Sort.by(sortBy).descending());
            }
        }

        return repository.findAll(paging);
    }

    /**
     * Find a resource of base entity by id.
     *
     * @param resourceId The id of the resource.
     *
     * @return The resource found.
     */
    public T findById(I resourceId)
    {
        return repository.findById(resourceId).orElse(null);
    }

    /**
     * Creates a resource.
     *
     * @param modelObject The request entity to created.
     *
     * @return The resource created.
     */
    public T save(T modelObject)
    {
        return repository.save(modelObject);
    }

    /**
     * Updates a resource.
     *
     * @param resourceId The resource id to update.
     * @param modelObject The request entity to update.
     *
     * @return The resource updated.
     */
    public T update(I resourceId, T modelObject)
    {
        T found = findById(resourceId);
        if (found == null)
        {
            return null;
        }

        Field idField = getFieldsListWithAnnotation(modelObject.getClass(), Id.class).stream()
            .findFirst().orElse(null);

        if (idField == null)
        {
            return null;
        }
        else
        {
            ReflectionUtils.makeAccessible(idField);
            ReflectionUtils.setField(idField, modelObject, resourceId);
            return repository.save(modelObject);
        }
    }

    /**
     * Deletes a resource of base entity.
     *
     * @param modelObject The object to delete.
     */
    public void delete(T modelObject)
    {
        repository.delete(modelObject);
    }
}
