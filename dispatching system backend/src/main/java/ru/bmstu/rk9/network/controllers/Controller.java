package ru.bmstu.rk9.network.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public abstract class Controller {

  private ObjectMapper mapper = new ObjectMapper();
  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  public static final String WRONG_PARAMETER_TYPE = "Wrong parameter type";

  protected <T> String getViolationString(T entity) {
    Set<ConstraintViolation<T>> violations = validator.validate(entity);
    StringBuilder violationString = new StringBuilder();

    for (ConstraintViolation<T> violation : violations) {
      violationString.append(violation.getPropertyPath()).append(" ")
          .append(violation.getMessage()).append("\n");
    }
    return violationString.toString();
  }

  protected String wrapErrorMessage(String message) throws JsonProcessingException {
    ObjectNode errorMessage = mapper.createObjectNode();
    errorMessage.put("message", message);

    return mapper.writeValueAsString(errorMessage);
  }
}
