package com.cinquecento.project.BoxCompany.controllers;


import com.cinquecento.project.BoxCompany.models.OrderDetails;
import com.cinquecento.project.BoxCompany.services.CustomersService;
import com.cinquecento.project.BoxCompany.services.OrdersDetailsService;
import com.cinquecento.project.BoxCompany.services.OrdersService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import com.cinquecento.project.BoxCompany.dto.CustomerDTO;
import com.cinquecento.project.BoxCompany.dto.OrderDTO;
import com.cinquecento.project.BoxCompany.models.Customer;
import com.cinquecento.project.BoxCompany.models.Order;
import com.cinquecento.project.BoxCompany.util.CustomerErrorsResponse;
import com.cinquecento.project.BoxCompany.util.CustomerNotCreatedException;
import com.cinquecento.project.BoxCompany.util.CustomerNotFoundException;
import com.cinquecento.project.BoxCompany.util.CustomerValidator;

import javax.validation.Valid;
import java.time.LocalDateTime;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    private final CustomersService customersService;
    private final ModelMapper modelMapper;
    private final CustomerValidator customerValidator;
    private final OrdersService ordersService;
    private final OrdersDetailsService ordersDetailsService;

    @Autowired
    public CustomersController(CustomersService customersService, ModelMapper modelMapper, CustomerValidator customerValidator, OrdersService ordersService, OrdersDetailsService ordersDetailsService) {
        this.customersService = customersService;
        this.modelMapper = modelMapper;
        this.customerValidator = customerValidator;
        this.ordersService = ordersService;
        this.ordersDetailsService = ordersDetailsService;
    }

    // get all customers
    @GetMapping()
    public List<CustomerDTO> getCustomers() {
        return customersService.findAll().stream().map(this::convertToCustomerDTO).collect(Collectors.toList());
    }

    // add one customer
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid CustomerDTO customerDTO,
                                             BindingResult bindingResult) {
        customerValidator.validate(convertToCustomer(customerDTO), bindingResult);
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

            throw new CustomerNotCreatedException(errorMsg.toString());
        }

        customersService.add(convertToCustomer(customerDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // need to rethink
    /*@PostMapping("/{id}/createOrder")
    public ResponseEntity<HttpStatus> createOrder(@PathVariable("id") int id,
                                             @RequestBody ObjectNode objectNode,
                                             BindingResult bindingResult) {

        Order order = new Order(objectNode.get("shipAddress").asText(),
                                objectNode.get("shipCity").asText(),
                                objectNode.get("shipPostalCode").asText(),
                                objectNode.get("shipCountry").asText());
        OrderDetails orderDetails = new OrderDetails(objectNode.get("quantity").asInt(),
                                                     objectNode.get("discount").asDouble());

        Optional<Customer> customer = customersService.findById(id);

        if (customer.isPresent()) {
            order.getOrderDetails().add(orderDetails);
            List<Order> orders = customer.get().getOrder();
            orders.add(order);
            order.setCustomer(customer.get());
            ordersService.add(order);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        throw new CustomerNotFoundException("Not found.");
    }*/

    // get orders by id
    @GetMapping("/{id}")
    public List<OrderDTO> getOrders(@PathVariable("id") int id) {
        Optional<Customer> customer = customersService.findById(id);
        if (customer.isPresent()) {
            List<Order> orders = customer.get().getOrder();
            List<OrderDetails> orderDetails = orders.get(1).getOrderDetails();
            for(OrderDetails od : orderDetails) {
                System.out.println(od);
            }
            if(!orders.isEmpty()) {
                return customersService.findOrdersByCustomerId(id).stream().map(this::convertToOrderDTO).collect(Collectors.toList());
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
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<CustomerErrorsResponse> handlerException(CustomerNotFoundException e) {
        CustomerErrorsResponse response = new CustomerErrorsResponse(
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

    private Customer convertToCustomer(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }

    private CustomerDTO convertToCustomerDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }
}
