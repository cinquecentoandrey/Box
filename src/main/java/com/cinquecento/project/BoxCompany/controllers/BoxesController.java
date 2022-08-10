package com.cinquecento.project.BoxCompany.controllers;


import com.cinquecento.project.BoxCompany.util.BoxNotUpdatedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import com.cinquecento.project.BoxCompany.dto.BoxDTO;
import com.cinquecento.project.BoxCompany.dto.OrderDetailsDTO;
import com.cinquecento.project.BoxCompany.models.Box;
import com.cinquecento.project.BoxCompany.models.OrderDetails;
import com.cinquecento.project.BoxCompany.services.BoxesService;
import com.cinquecento.project.BoxCompany.services.OrdersDetailsService;
import com.cinquecento.project.BoxCompany.util.BoxErrorsResponse;
import com.cinquecento.project.BoxCompany.util.BoxNotCreatedException;
import com.cinquecento.project.BoxCompany.util.BoxValidator;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/boxes")
public class BoxesController {

    private final BoxesService boxesService;
    private final OrdersDetailsService ordersDetailsService;
    private final ModelMapper modelMapper;
    private final BoxValidator boxValidator;

    @Autowired
    public BoxesController(BoxesService boxesService, OrdersDetailsService ordersDetailsService, ModelMapper modelMapper, BoxValidator boxValidator) {
        this.boxesService = boxesService;
        this.ordersDetailsService = ordersDetailsService;
        this.modelMapper = modelMapper;
        this.boxValidator = boxValidator;
    }


    // get all boxes
    @GetMapping()
    public List<BoxDTO> getBoxes() {
        return boxesService.findAll().stream().map(this::convertToBoxDTO).collect(Collectors.toList());
    }


    // add one box
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid BoxDTO boxDTO,
                                             BindingResult bindingResult) {

        boxValidator.validate(convertToBox(boxDTO), bindingResult);
        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError e : errors){
                errorMsg.append(e
                                .getField())
                        .append(" - ")
                        .append(e.getDefaultMessage())
                        .append(";");
            }

            throw new BoxNotCreatedException(errorMsg.toString());
        }

        boxesService.add(convertToBox(boxDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // get all boxes on orders
    @GetMapping("/{id}/onOrders")
    public List<OrderDetailsDTO> inOrder(@PathVariable("id") int id) {
        Optional<Box> box = boxesService.findById(id);
        if(box.isPresent()) {
            List<OrderDetails> orderDetails = box.get().getOrderDetails();
            System.out.println(orderDetails.toString());
            if (!orderDetails.isEmpty()) {
                return orderDetails.stream().map(this::convertToOrderDetailsDTO).collect(Collectors.toList());
            }
        }
        return Collections.singletonList((OrderDetailsDTO) Collections.emptyList());
    }

    // update box, but with no handler
    @PostMapping("/{id}/update")
    public ResponseEntity<HttpStatus> updateBox(@PathVariable("id") int id,
                          @RequestBody @Valid BoxDTO boxDTO) {

        boxesService.update(id, convertToBox(boxDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}/updateOnOrderCount")
    public ResponseEntity<HttpStatus> updateOnOrderStatus(@PathVariable("id") int id) {
        boxesService.updateOnOrderStatus(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }



    @ExceptionHandler
    private ResponseEntity<BoxErrorsResponse> handlerException(BoxNotUpdatedException e) {
        BoxErrorsResponse response = new BoxErrorsResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private OrderDetailsDTO convertToOrderDetailsDTO(OrderDetails orderDetails) {
        return modelMapper.map(orderDetails, OrderDetailsDTO.class);
    }
    private Box convertToBox(BoxDTO boxDTO) {
        return modelMapper.map(boxDTO, Box.class);
    }

    private BoxDTO convertToBoxDTO(Box box) {
        return modelMapper.map(box, BoxDTO.class);
    }

}
