package com.example.demo.controller;

import com.example.demo.model.Inventory;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.Orders;
import com.example.demo.service.InventoryService;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public String getCart(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        List<OrderDetail> orderDetails = new ArrayList<>();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        //Map<Integer, Inventory> inventoryMap = inventoryService.findAll().stream().collect( Collectors.toMap( Inventory::getId, inventory -> inventory ));
        Orders order = orderService.getOrderByUserName( username );
        if ( order != null ) {
            orderDetails = orderDetailService.getByOrderId(order.getId());
            model.addAttribute("orderId", order.getId() );
        }
        // order.setTotalAmount( calculateTotal( orderDetails ) );
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("totalAmount", calculateTotal( orderDetails ));
        return "shopping-cart";
    }


    @GetMapping("/delete/{id}")
    public String deleteCartItem(@PathVariable int id ){

        orderDetailService.deleteById( id );
        return "redirect:/shopping-cart";
    }

    @GetMapping("/checkout/{id}")
    public String checkoutCart( @PathVariable int id, Model model ){
        orderDetailService.deleteByOrderId( id );
        orderService.deleteById( id );
        model.addAttribute("orderNo", id );
        return "order-delivered";
    }


    public float calculateTotal(List<OrderDetail> orderDetails ){
        float total_amount = 0.0f;
        for ( OrderDetail orderDetail : orderDetails ){
            total_amount += orderDetail.getAmount();
        }
        return total_amount;

    }





}
