package com.cinquecento.project.BoxCompany.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import com.cinquecento.project.BoxCompany.dto.OrderDTO;
import com.cinquecento.project.BoxCompany.models.Order;
import com.cinquecento.project.BoxCompany.services.CustomersService;
import com.cinquecento.project.BoxCompany.services.OrdersService;
import com.cinquecento.project.BoxCompany.util.OrderErrorsResponse;
import com.cinquecento.project.BoxCompany.util.OrderNotCreatedException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final ModelMapper modelMapper;

    private final CustomersService customersService;

    @Autowired
    public OrdersController(OrdersService ordersService, ModelMapper modelMapper, CustomersService customersService) {
        this.ordersService = ordersService;
        this.modelMapper = modelMapper;
        this.customersService = customersService;
    }

    // create orders
    @GetMapping()
    public List<OrderDTO> getOrders() {
        return ordersService.findAll().stream().map(this::convertToOrderDTO).collect(Collectors.toList());
    }


    // add one order
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid OrderDTO orderDTO,
                                             BindingResult bindingResult) {

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
            throw new OrderNotCreatedException(errorMsg.toString());
        }


        ordersService.add(convertToOrders(orderDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}/sendOrder")
    public ResponseEntity<HttpStatus> send(@PathVariable("id") int id) {
        ordersService.orderReceipt(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<OrderErrorsResponse> handlerException(OrderNotCreatedException e) {
        OrderErrorsResponse response = new OrderErrorsResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Order convertToOrders(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }

    private OrderDTO convertToOrderDTO(Order order) {
    return modelMapper.map(order, OrderDTO.class);
}
}
