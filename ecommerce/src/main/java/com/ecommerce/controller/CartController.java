package com.ecommerce.controller;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Product;
import com.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id) {
        return cartService.getCart(id);
    }

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @PostMapping("/add")
    public Cart addCart(@RequestBody Cart cart) {
        return cartService.addCart(cart);
    }

    @PutMapping("/update")
    public Cart updateCart(@RequestBody Cart cart) {
        return cartService.updateCart(cart);
    }

    @PostMapping("/add/{cartId}")
    public Cart addProductToCart(@PathVariable long cartId ,@RequestBody Product product) {
        return cartService.addProductToCart(cartId,product);
    }

    @DeleteMapping("/remove/{cartId}/{productId}")
    public Cart removeProductFromCart(@PathVariable Long cartId,
                                      @RequestParam Long productId) {
        return cartService.removeProductFromCart(cartId, productId);
    }

}
