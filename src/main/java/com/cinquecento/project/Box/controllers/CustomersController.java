package com.cinquecento.project.Box.controllers;



import com.cinquecento.project.Box.services.CustomersService;
import com.cinquecento.project.Box.services.OrdersService;
import com.cinquecento.project.Box.util.*;
import com.cinquecento.project.Box.util.exceptions.CustomerNotCreatedException;
import com.cinquecento.project.Box.util.exceptions.CustomerNotFoundException;
import com.cinquecento.project.Box.util.responces.CustomerErrorsResponse;
import com.cinquecento.project.Box.util.validators.CustomerValidator;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.cinquecento.project.Box.dto.CustomerDTO;
import com.cinquecento.project.Box.dto.OrderDTO;
import com.cinquecento.project.Box.models.Customer;
import com.cinquecento.project.Box.models.Order;

import javax.validation.Valid;
import java.time.LocalDateTime;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/customers")
public class CustomersController {

    private final CustomersService customersService;
    private final ModelMapper modelMapper;
    private final CustomerValidator customerValidator;
    private final OrdersService ordersService;
    private final MessageSource messageSource;

    @Autowired
    public CustomersController(CustomersService customersService, ModelMapper modelMapper, CustomerValidator customerValidator, OrdersService ordersService, MessageSource messageSource) {
        this.customersService = customersService;
        this.modelMapper = modelMapper;
        this.customerValidator = customerValidator;
        this.ordersService = ordersService;
        this.messageSource = messageSource;
    }

    @GetMapping()
    public List<CustomerDTO> getCustomers() {
        return customersService
                .findAll()
                .stream()
                .map(this::convertToCustomerDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid CustomerDTO customerDTO,
                                             BindingResult bindingResult) {
        customerValidator.validate(convertToCustomer(customerDTO), bindingResult);
        if(bindingResult.hasErrors()) {
            throw new CustomerNotCreatedException(ErrorMessage.errorMessage(bindingResult));
        }

        customersService.add(convertToCustomer(customerDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{id}/createOrder")
    public ResponseEntity<HttpStatus> createOrder(@PathVariable("id") int id,
                                                  @RequestBody @Valid OrderDTO orderDTO) {
        Optional<Customer> customer = customersService.findById(id);

        if(customer.isPresent()) {
            Order order = convertToOrders(orderDTO);
            order.setCustomer(customer.get());
            ordersService.add(order);
            return ResponseEntity.ok(HttpStatus.OK);
        }

        throw new CustomerNotFoundException(messageSource.getMessage("customer.notFoundMessage", null, Locale.ENGLISH));
    }

    @GetMapping("/{id}/getOrders")
    public List<OrderDTO> getOrders(@PathVariable("id") int id) {
        Optional<Customer> customer = customersService.findById(id);

        if (customer.isPresent()) {
            List<Order> orders = customer.get().getOrder();
            if(!orders.isEmpty()) {
                return orders
                        .stream()
                        .map(this::convertToOrderDTO).collect(Collectors.toList());
            }
        }

        return Collections.emptyList();
    }

    @ExceptionHandler
    private ResponseEntity<CustomerErrorsResponse> handlerException(CustomerNotCreatedException e) {
        CustomerErrorsResponse response = new CustomerErrorsResponse(
                e.getMessage(),
                LocalDateTime.now()
        );

        log.error(response.getName());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<CustomerErrorsResponse> handlerException(CustomerNotFoundException e) {

        CustomerErrorsResponse response = new CustomerErrorsResponse(
                e.getMessage(),
                LocalDateTime.now()
        );

        log.error(response.getName());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Order convertToOrders(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }

    private OrderDTO convertToOrderDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    private Customer convertToCustomer(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }

    private CustomerDTO convertToCustomerDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }
}
