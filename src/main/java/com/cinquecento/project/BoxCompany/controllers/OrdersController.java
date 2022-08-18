package com.cinquecento.project.BoxCompany.controllers;



import com.cinquecento.project.BoxCompany.dto.BoxDTO;
import com.cinquecento.project.BoxCompany.dto.OrderDetailsDTO;
import com.cinquecento.project.BoxCompany.models.Box;
import com.cinquecento.project.BoxCompany.models.OrderDetails;
import com.cinquecento.project.BoxCompany.services.BoxesService;
import com.cinquecento.project.BoxCompany.services.OrdersDetailsService;
import com.cinquecento.project.BoxCompany.util.ErrorMessage;
import com.cinquecento.project.BoxCompany.util.exceptions.OrderNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.cinquecento.project.BoxCompany.dto.OrderDTO;
import com.cinquecento.project.BoxCompany.models.Order;
import com.cinquecento.project.BoxCompany.services.OrdersService;
import com.cinquecento.project.BoxCompany.util.responces.OrderErrorsResponse;
import com.cinquecento.project.BoxCompany.util.exceptions.OrderNotCreatedException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final ModelMapper modelMapper;
    private final BoxesService boxesService;
    private final OrdersDetailsService ordersDetailsService;

    @Autowired
    public OrdersController(OrdersService ordersService, ModelMapper modelMapper, BoxesService boxesService, OrdersDetailsService ordersDetailsService) {
        this.ordersService = ordersService;
        this.modelMapper = modelMapper;
        this.boxesService = boxesService;
        this.ordersDetailsService = ordersDetailsService;
    }

    @GetMapping()
    public List<OrderDTO> getOrders() {
        return ordersService
                .findAll()
                .stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/createOrder")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid OrderDTO orderDTO,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new OrderNotCreatedException(ErrorMessage.errorMessage(bindingResult));
        }

        ordersService.add(convertToOrders(orderDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{id}/addDetails")
    public ResponseEntity<HttpStatus> addDetails(@PathVariable("id") int id,
                                                 @RequestBody
                                                 @Valid
                                                 List<OrderDetailsDTO> orderDetailsDTO) {

        Optional<Order> order = ordersService.findById(id);

        if(order.isPresent()) {
           orderDetailsDTO.forEach(od -> {

                Box box = boxesService.findById(od.getBoxId()).get();
                od.setBox(convertToBoxDTO(box));
                box.getOrderDetails().add(convertToOrderDetails(od));

                od.setBoxPrice(box.getBoxPrice());
                od.setTotal(box.getBoxPrice() * od.getQuantity() * (1-od.getDiscount()));

            });
            List<OrderDetails> orderDetails = orderDetailsDTO
                    .stream()
                    .map(this::convertToOrderDetails)
                    .collect(Collectors.toList());

           orderDetails.forEach(od -> {
                   od.setOrder(order.get());
            });

            ordersDetailsService.add(orderDetails);
            return ResponseEntity.ok(HttpStatus.OK);
        }

        throw new OrderNotFoundException("Order not found.");
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

    private BoxDTO convertToBoxDTO(Box box) {
        return modelMapper.map(box, BoxDTO.class);
    }
    private OrderDetails convertToOrderDetails(OrderDetailsDTO orderDetailsDTO) {
        return modelMapper.map(orderDetailsDTO, OrderDetails.class);
    }
    private Order convertToOrders(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }

    private OrderDTO convertToOrderDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }
}
