package lk.ijse.gdse.o14_spring_testing.service;


import lk.ijse.gdse.o14_spring_testing.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProduct(Long id);
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
}
