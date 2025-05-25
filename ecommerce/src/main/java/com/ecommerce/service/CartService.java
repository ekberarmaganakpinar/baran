package com.ecommerce.service;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartService {


    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    
    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }
    
    public Cart addProductToCart(Long cartId,Product product) {
        Cart cart = getCart(cartId);
        if(cart == null){
            return null;
        }
        List<Product> products = cart.getProducts();
        products.add(product);
        cart.setProducts(products);
        Product oldProduct = productRepository.findById(product.getId()).orElse(null);
        if(oldProduct == null){
            return null;
        }
        oldProduct.setStock(oldProduct.getStock()-1);
        productRepository.save(oldProduct);
        return cartRepository.save(cart);
    }
    
    public Cart removeProductFromCart(Long cartId, Long productId) {
        Cart cart = getCart(cartId);
        if (cart == null) {
            return null;
        }
        List<Product> products = cart.getProducts();
        Integer index = getProductIndex(productId, products);
        if(index == null){
            return null;
        }
        products.remove(index.intValue());
        if(products.isEmpty()){
            cartRepository.delete(cart);
            return null;
        }
        cart.setProducts(products);

        Product oldProduct = productRepository.findById(productId).orElse(null);
        if(oldProduct == null){
            return null;
        }
        oldProduct.setStock(oldProduct.getStock()+1);
        productRepository.save(oldProduct);
        return cartRepository.save(cart);
    }

    private static Integer getProductIndex(Long productId, List<Product> products) {
        for(int i = 0; i< products.size(); i++){
            if(Objects.equals(productId, products.get(i).getId())){
                return i;
            }
        }
        return null;
    }

    public Cart updateCart(Cart cart) {
        Cart oldCart = getCart(cart.getId());
        if(Objects.equals(oldCart,cart)){
            return null;
        }
        double total =  updateCartTotal(cart);
        cart.setTotalPrice(total);
        return cartRepository.save(cart);
    }

    private double updateCartTotal(Cart cart) {
        double total = 0.0;
        for (Product product : cart.getProducts()){
           total = total + product.getPrice();
        }
        return total;
    }

    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
}
