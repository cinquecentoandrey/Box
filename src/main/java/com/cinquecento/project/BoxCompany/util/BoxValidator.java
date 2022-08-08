package com.cinquecento.project.BoxCompany.util;


import com.cinquecento.project.BoxCompany.models.Box;
import com.cinquecento.project.BoxCompany.services.BoxesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BoxValidator implements Validator {

    private final BoxesService boxesService;

    @Autowired
    public BoxValidator(BoxesService boxesService) {
        this.boxesService = boxesService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Box.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Box box = (Box) target;

        if(boxesService.findByName(box.getBoxName()).isPresent()) {
            errors.rejectValue("boxName", "", "There is already a box with that name.");
        }
    }
}
