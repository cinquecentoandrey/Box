package com.cinquecento.project.Box.controllers;


import com.cinquecento.project.Box.util.*;
import com.cinquecento.project.Box.util.exceptions.BoxNotCreatedException;
import com.cinquecento.project.Box.util.exceptions.BoxNotUpdatedException;
import com.cinquecento.project.Box.util.responces.BoxErrorsResponse;
import com.cinquecento.project.Box.util.validators.BoxValidator;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.cinquecento.project.Box.dto.BoxDTO;
import com.cinquecento.project.Box.dto.OrderDetailsDTO;
import com.cinquecento.project.Box.models.Box;
import com.cinquecento.project.Box.models.OrderDetails;
import com.cinquecento.project.Box.services.BoxesService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
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
    public List<OrderDetailsDTO> onOrderById(@PathVariable("id") int id) {
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

    @GetMapping("/onOrders")
    public List<BoxDTO> onOrder() {
        return boxesService.findAll()
                .stream()
                .filter(box -> box.getBoxOnOrder() > 0)
                .map(this::convertToBoxDTO)
                .collect(Collectors.toList());
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
    private ResponseEntity<BoxErrorsResponse> handlerException(BoxNotCreatedException e) {
        BoxErrorsResponse response = new BoxErrorsResponse(
                e.getMessage(),
                LocalDateTime.now()
        );

        log.error(response.getName());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<BoxErrorsResponse> handlerException(BoxNotUpdatedException e) {
        BoxErrorsResponse response = new BoxErrorsResponse(
                e.getMessage(),
                LocalDateTime.now()
        );

        log.error(response.getName());
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
