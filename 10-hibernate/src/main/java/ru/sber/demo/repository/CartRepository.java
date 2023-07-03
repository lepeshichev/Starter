package ru.sber.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.demo.entity.Cart;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findCartByProduct_IdAndClient_Id(long productId, long userId);

    void deleteCartByClient_Id(long userId);
    List<Cart> findCartByClient_Id(long userId);
    int countCartsByClient_Id(long userId);
    int countCartsByProduct_Id(long productId);

}
