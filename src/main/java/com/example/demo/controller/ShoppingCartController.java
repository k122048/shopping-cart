package com.example.demo.controller;

import com.example.demo.model.Inventory;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.Orders;
import com.example.demo.service.InventoryService;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import org.apache.commons.math3.util.Precision;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
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
            calculatePerItemDiscount( orderDetails );
            model.addAttribute("orderId", order.getId() );

        }

        // order.setTotalAmount( calculateTotal( orderDetails ) );
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("totalAmount", calculateTotal( orderDetails ));
        model.addAttribute("totalDiscount", calculateTotalDiscount( orderDetails ));
        return "shopping-cart";
    }


    @GetMapping("/delete/{id}")
    public String deleteCartItem(@PathVariable int id ){
        OrderDetail orderDetail = orderDetailService.findById( id );
        Inventory inventory = inventoryService.findInventoryById( orderDetail.getInventory().getId() );
        inventory.setQuantity( orderDetail.getQuantity() + inventory.getQuantity() );
        inventoryService.save( inventory );
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

    public float calculateTotalDiscount(List<OrderDetail> orderDetails ){
        float total_amount = 0.0f;
        for ( OrderDetail orderDetail : orderDetails ){
            total_amount += orderDetail.getDiscount();
        }
        return total_amount;
    }

    public void calculatePerItemDiscount(List<OrderDetail> orderDetails){
        DecimalFormat decimalFormat = new DecimalFormat(".#");
        for ( OrderDetail orderDetail : orderDetails){
            double discount = 0.0f;
            int tempQuantity = 0;
            int remaining = 0;
            if ( orderDetail.getInventory().getOffer() != null ) {

                if (orderDetail.getInventory().getOffer().equals("BUY 2 GET 1 FREE")) {
                    if (orderDetail.getQuantity() > 2) {
                        tempQuantity = Math.abs((int) Math.floor(orderDetail.getQuantity() / 3));
                        remaining = (int) orderDetail.getQuantity() % 3;
                        discount = Precision.round((tempQuantity * 3 * orderDetail.getInventory().getPrice()) * 0.333f,1 );
                        orderDetail.setDiscount((float)discount);
                        orderDetail.setAmount(((tempQuantity * 3 * orderDetail.getInventory().getPrice()) - orderDetail.getDiscount()) + (orderDetail.getInventory().getPrice() * remaining));
                    }
                }
                if (orderDetail.getInventory().getOffer().equals("50%OFF NEXT")) {
                    if (orderDetail.getQuantity() > 1) {
                        tempQuantity = Math.abs((int) Math.floor(orderDetail.getQuantity() / 2));
                        remaining = (int) orderDetail.getQuantity() % 2;
                        discount = Precision.round((tempQuantity * 2 * orderDetail.getInventory().getPrice()) * 0.25f,1);
                        orderDetail.setDiscount((float)discount);
                        orderDetail.setAmount(((tempQuantity * 2 * orderDetail.getInventory().getPrice()) - orderDetail.getDiscount()) + (orderDetail.getInventory().getPrice() * remaining));
                    }
                }
            }
        }

    }





}
