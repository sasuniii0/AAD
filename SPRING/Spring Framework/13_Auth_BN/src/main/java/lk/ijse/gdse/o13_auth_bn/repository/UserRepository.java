package lk.ijse.gdse.o13_auth_bn.repository;

import lk.ijse.gdse.o13_auth_bn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional <User> findByUsername(String username);
}
