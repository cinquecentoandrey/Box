package com.cinquecento.project.BoxCompany.controllers;


import com.cinquecento.project.BoxCompany.util.ErrorMessage;
import com.cinquecento.project.BoxCompany.util.responces.OrderDetailsErrorsResponse;
import com.cinquecento.project.BoxCompany.util.exceptions.OrderDetailsNotCreatedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.cinquecento.project.BoxCompany.dto.OrderDetailsDTO;
import com.cinquecento.project.BoxCompany.models.OrderDetails;
import com.cinquecento.project.BoxCompany.services.OrdersDetailsService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orderDetails")
public class OrderDetailsController {
    private final OrdersDetailsService ordersDetailsService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderDetailsController(OrdersDetailsService ordersDetailsService, ModelMapper modelMapper) {
        this.ordersDetailsService = ordersDetailsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<OrderDetailsDTO> getOrderDetails() {
        return ordersDetailsService
                .findAll()
                .stream()
                .map(this::convertToOrderDetailsDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid List<OrderDetailsDTO> orderDetailsDTO,
                                             BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new OrderDetailsNotCreatedException(ErrorMessage.errorMessage(bindingResult));
        }

        ordersDetailsService.add(orderDetailsDTO
                                    .stream()
                                    .map(this::convertToOrderDetails)
                                    .collect(Collectors.toList()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{id}/setOrderDetails")
    public ResponseEntity<HttpStatus> setOrder(@RequestParam("box_id") int box_id,
                                               @RequestParam("order_id") int order_id,
                                               @PathVariable("id") int id) {
        Optional<OrderDetails> orderDetails = ordersDetailsService.findById(id);

        if(orderDetails.isPresent()) {
            ordersDetailsService.setCredentials(orderDetails.get(), box_id, order_id);
            return ResponseEntity.ok(HttpStatus.OK);
        }

        return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<OrderDetailsErrorsResponse> handlerException(OrderDetailsNotCreatedException e) {
        OrderDetailsErrorsResponse response = new OrderDetailsErrorsResponse(
                e.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private OrderDetails convertToOrderDetails(OrderDetailsDTO orderDetailsDTO) {
        return modelMapper.map(orderDetailsDTO, OrderDetails.class);
    }

    private OrderDetailsDTO convertToOrderDetailsDTO(OrderDetails orderDetails) {
        return modelMapper.map(orderDetails, OrderDetailsDTO.class);
    }
}
