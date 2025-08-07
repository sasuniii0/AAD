package lk.ijse.gdse.o14_spring_testing.service.impl;

import lk.ijse.gdse.o14_spring_testing.entity.Product;
import lk.ijse.gdse.o14_spring_testing.repository.ProductRepository;
import lk.ijse.gdse.o14_spring_testing.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Product not found with id: " + id));
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product oldProduct = productRepository.getProductById(product.getId());
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setQuantity(product.getQuantity());
        return productRepository.save(oldProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product delProduct = productRepository.getProductById(id);
        productRepository.delete(delProduct);
    }
}
