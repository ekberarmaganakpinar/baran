package com.ecommerce.service;


import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Ürün getirme
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Ürün oluşturma
    public Product createProduct(Product product) {
        //Burada orderService yazılırken productHistorye bir kayıt atılacak bu tutulan productların ve customerın idsi ve ürünün priceı yazılacak
        //ProductHistoryController yazılırken kullanıcıdan customer ve productın id si alınacak sonrasında ProductHistoryService üzerinde ProductHistory nesnesi çekilecek sonrasındanda gidip Product Service üzerinden productı çekip yenibir nesne döneceksin
        return productRepository.save(product);
    }

    // Ürün güncelleme
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setStock(productDetails.getStock());
            product.setDescription(productDetails.getDescription());
            return productRepository.save(product);
        }
        return null;
    }

    // Ürün silme
    public boolean deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            productRepository.delete(product);
            return true;
        }
        return false;
    }

    // Tüm ürünleri getirme
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
