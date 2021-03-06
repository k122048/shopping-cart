package com.example.demo.restcontroller;

import com.example.demo.model.Inventory;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.Orders;
import com.example.demo.requestbody.InventoryAddRequest;
import com.example.demo.service.InventoryService;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/shopping-cart")
public class ShoppingCartRestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/{id}")
    public Orders getCartOrders(@PathVariable int id ){
        Orders orders = orderService.getOrderById( id );
        return orders;
    }


    @PostMapping
    public String createOrder( @RequestBody InventoryAddRequest inventoryAddRequest ){
        OrderDetail orderDetail = new OrderDetail();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Inventory inventory = inventoryService.findInventoryById( inventoryAddRequest.getInventoryId() );
        if( inventoryAddRequest.getQuantity() <= 0 ){
            return "Error : Quantity should be greater than 0";
        }
        if( inventory.getQuantity() < inventoryAddRequest.getQuantity() ){
            return "Error : "+ inventory.getTitle()+" Inventory out of stock, only "+inventory.getQuantity()+" left";
        }
        inventory.setQuantity(  inventory.getQuantity() - inventoryAddRequest.getQuantity() );


        Orders orders = orderService.getOrderByUserName( username );
        if ( orders == null ){
            orders = createNewOrder( username, inventoryAddRequest.getQuantity() );
        }else{
            orders.setUserName( username  );
            orders.setTotalAmount( inventoryAddRequest.getQuantity() );
            orderDetail = orderDetailService.findByOrderIdAndInventoryId(orders.getId(),inventory.getId());
        }

        if( orderDetail != null ){
            orderDetail.setQuantity( orderDetail.getQuantity() + inventoryAddRequest.getQuantity() );
            orderDetail.setAmount( inventory.getPrice() * orderDetail.getQuantity()  );
        }else{
            orderDetail = new OrderDetail();
            orderDetail.setQuantity(  inventoryAddRequest.getQuantity() );
            orderDetail.setAmount( inventory.getPrice() * inventoryAddRequest.getQuantity() );
        }
        inventoryService.save( inventory );
        orderDetail.setInventory( inventory );
        orderDetail.setOrders( orders );
        orderDetailService.saveOrderDetail( orderDetail );

        orderService.saveOrder(orders);
        return "Success: "+inventory.getTitle()+" added successfully to the cart";
    }



    public Orders createNewOrder(String userName , float quantity){
        Orders orders = new Orders();
        orders.setUserName( userName );
        orders.setTotalAmount( quantity );
        return orders;
    }

}
