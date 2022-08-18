package com.cinquecento.project.BoxCompany.controllers;


import com.cinquecento.project.BoxCompany.util.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.cinquecento.project.BoxCompany.dto.BoxDTO;
import com.cinquecento.project.BoxCompany.dto.OrderDetailsDTO;
import com.cinquecento.project.BoxCompany.models.Box;
import com.cinquecento.project.BoxCompany.models.OrderDetails;
import com.cinquecento.project.BoxCompany.services.BoxesService;

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
    private final ModelMapper modelMapper;
    private final BoxValidator boxValidator;

    @Autowired
    public BoxesController(BoxesService boxesService, ModelMapper modelMapper, BoxValidator boxValidator) {
        this.boxesService = boxesService;
        this.modelMapper = modelMapper;
        this.boxValidator = boxValidator;
    }

    @GetMapping()
    public List<BoxDTO> getBoxes() {
        return boxesService
                .findAll()
                .stream()
                .map(this::convertToBoxDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/createBox")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid BoxDTO boxDTO,
                                             BindingResult bindingResult) {

        boxValidator.validate(convertToBox(boxDTO), bindingResult);
        if(bindingResult.hasErrors()) {
            throw new BoxNotCreatedException(ErrorMessage.errorMessage(bindingResult));
        }

        boxesService.add(convertToBox(boxDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}/onOrders")
    public List<OrderDetailsDTO> onOrder(@PathVariable("id") int id) {
        Optional<Box> box = boxesService.findById(id);
        if(box.isPresent()) {
            List<OrderDetails> orderDetails = box.get().getOrderDetails();
            if (!orderDetails.isEmpty()) {
                return orderDetails
                        .stream()
                        .map(this::convertToOrderDetailsDTO)
                        .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<HttpStatus> updateBox(@PathVariable("id") int id,
                                                @RequestBody @Valid BoxDTO boxDTO,
                                                BindingResult bindingResult) {
        boxValidator.validate(convertToBox(boxDTO), bindingResult);
        if (bindingResult.hasErrors()) {
            throw new BoxNotUpdatedException(ErrorMessage.errorMessage(bindingResult));
        }
        boxesService.update(id, convertToBox(boxDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}/updateOnOrderStatus")
    public ResponseEntity<HttpStatus> updateOnOrderStatusById(@PathVariable("id") int id) {
        boxesService.updateOnOrderStatusById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/updateOnOrderStatus")
    public ResponseEntity<HttpStatus> updateOnOrderStatus() {
        boxesService.updateOnOrderStatus();
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
