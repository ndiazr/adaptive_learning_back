package com.adaptativelearning.controller.validators;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NullValidatorBuilder
{
    private String message;
    private HttpStatus httpStatus;

    /**
     * Private constructor.
     */
    private NullValidatorBuilder()
    {
    }

    /**
     * Builder to class.
     *
     * @return A instance of Builder.
     */
    public static NullValidatorBuilder builder()
    {
        return new NullValidatorBuilder();
    }

    /**
     * Builder setter message.
     *
     * @param message The message to set.
     *
     * @return The instance.
     */
    public NullValidatorBuilder message(String message)
    {
        this.message = message;
        return this;
    }

    /**
     * Builder setter status.
     *
     * @param httpStatus The status to set.
     *
     * @return The instance.
     */
    public NullValidatorBuilder httpStatus(HttpStatus httpStatus)
    {
        this.httpStatus = httpStatus;
        return this;
    }

    /**
     * Validate if the value is null to throw an exception base on builder properties.
     *
     * @param toValidate The value to validate.
     */
    public void validate(Object toValidate)
    {
        if (toValidate == null)
        {
            throw new ResponseStatusException(httpStatus, message);
        }
    }
}
