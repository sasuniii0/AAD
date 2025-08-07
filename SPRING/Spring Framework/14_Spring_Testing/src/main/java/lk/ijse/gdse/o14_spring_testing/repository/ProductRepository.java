package lk.ijse.gdse.o14_spring_testing.repository;

import lk.ijse.gdse.o14_spring_testing.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductById(Long id);
}
