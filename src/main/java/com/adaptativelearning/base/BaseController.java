package com.adaptativelearning.base;

import static java.lang.String.format;

import com.adaptativelearning.base.entityinfo.EntityInfo;
import com.adaptativelearning.base.entityinfo.EntityInfoService;
import com.adaptativelearning.base.validators.NullValidatorBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.io.Serializable;
import java.util.List;
import javax.validation.Valid;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseController<T extends BaseEntity, I extends Serializable, R, A>
{
    private static final String NOT_FOUND_ERROR_FORMAT = "The resource %s was not found";

    @Autowired
    private EntityInfoService entityInfoService;

    @Autowired
    @Setter
    protected BaseService<T, I> baseCrudService;

    @Setter
    protected ModelMapper modelMapper;

    @Autowired
    @Setter
    protected ObjectMapper objectMapper;

    protected Class<T> baseEntityClass;
    protected Class<R> requestClass;
    protected Class<A> responseClass;
    protected TypeToken<List<A>> responseListToken = new TypeToken<List<A>>(){};
    protected TypeToken<Page<A>> responsePageToken = new TypeToken<Page<A>>(){};

    /**
     * Creates and instance of BaseController.
     *
     * @param baseEntityClass The base entity class.
     * @param requestClass The request class.
     * @param responseClass The response class.
     */
    public BaseController(Class<T> baseEntityClass, Class<R> requestClass, Class<A> responseClass)
    {
        this.baseEntityClass = baseEntityClass;
        this.requestClass = requestClass;
        this.responseClass = responseClass;
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Retrieve all resources for base entity.
     *
     * @return The list of resources.
     */
    @ApiOperation(value = "Retrieve all resources")
    @GetMapping()
    public ResponseEntity<List<A>> getAllResources()
    {
        List<T> baseEntityList = baseCrudService.getAll();
        return ResponseEntity.ok(modelMapper.map(baseEntityList, responseListToken.getType()));
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
    @ApiOperation(value = "Retrieve all resources with pagination")
    @GetMapping("/pageable")
    public ResponseEntity<Page<A>> getAllResources(@RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size,
        @RequestParam(defaultValue = "id", required = false) String sortBy,
        @RequestParam(defaultValue = "asc", required = false) String sortType)
    {
        Page<T> baseEntityList = baseCrudService.getAll(page, size, sortBy, sortType);
        return ResponseEntity.ok(modelMapper.map(baseEntityList, responsePageToken.getType()));
    }

    /**
     * Find a resource of base entity by id.
     *
     * @param id The id of the resource.
     *
     * @return The resource found.
     */
    @ApiOperation(value = "Find a resource by his id")
    @ApiResponse(code = 404, message = "The resource was not found")
    @GetMapping(value = "{id}")
    public ResponseEntity<A> findResourceById(@PathVariable() I id)
    {
        T foundObject = validateObject(id);
        return ResponseEntity.ok(modelMapper.map(foundObject, responseClass));

    }

    /**
     * Creates a resource.
     *
     * @param requestEntity The request entity to created.
     *
     * @return The resource created.
     */
    @ApiOperation(value = "Creates a resource", code = 201)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<A> createResource(@Valid @RequestBody R requestEntity)
    {
        T baseEntityCreated = baseCrudService.save(modelMapper.map(requestEntity, baseEntityClass));

        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper
            .map(baseEntityCreated, responseClass));
    }

    /**
     * Updates a resource.
     *
     * @param id The resource id to update.
     * @param requestEntity The request entity to update.
     *
     * @return The resource updated.
     */
    @ApiOperation(value = "Updates a resource")
    @ApiResponse(code = 404, message = "The resource was not found")
    @PutMapping(value = "{id}")
    public ResponseEntity<A> updateResource(@PathVariable() I id, @RequestBody R requestEntity)
    {
        T objectToUpdate = modelMapper.map(requestEntity, baseEntityClass);
        T baseEntityUpdated = baseCrudService.update(id, objectToUpdate);

        NullValidatorBuilder.builder().httpStatus(HttpStatus.NOT_FOUND).message(format(NOT_FOUND_ERROR_FORMAT,
            id)).validate(baseEntityUpdated);

        return ResponseEntity.ok(modelMapper.map(baseEntityUpdated, responseClass));
    }

    /**
     * Deletes a resource of base entity by id.
     *
     * @param id The id of the resource.
     */
    @ApiOperation(value = "Deletes a resource by his id")
    @ApiResponse(code = 404, message = "The resource was not found")
    @DeleteMapping(value = "{id}")
    public void deleteResource(@PathVariable() I id)
    {
        T foundObject = validateObject(id);
        baseCrudService.delete(foundObject);
    }

    /**
     * Retrieve all resources for base entity.
     *
     * @return The list of resources.
     */
    @ApiOperation(value = "Information of the resource")
    @GetMapping("/info")
    public ResponseEntity<EntityInfo> getEntityInfo()
    {
        entityInfoService.setBaseEntityClass(baseEntityClass);
        return ResponseEntity.ok(entityInfoService.geEntityInfo());
    }

    /**
     * Validate if a user exists or throws and exception.
     *
     * @param id The resource id to validate.
     *
     * @return The entity found.
     */
    public T validateObject(I id)
    {
        T foundObject = baseCrudService.findById(id);

        NullValidatorBuilder.builder().httpStatus(HttpStatus.NOT_FOUND).message(format(NOT_FOUND_ERROR_FORMAT,
            id)).validate(foundObject);

        return foundObject;
    }
}
