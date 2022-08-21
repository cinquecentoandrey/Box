package com.cinquecento.project.Box.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorMessage {
    public static String errorMessage(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for(FieldError e : errors){
            errorMsg.append(e
                            .getField())
                    .append(" - ")
                    .append(e.getDefaultMessage())
                    .append(";");
        }
        return errorMsg.toString();
    }
}
