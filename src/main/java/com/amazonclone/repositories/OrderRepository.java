package com.amazonclone.repositories;

import com.amazonclone.models.Order;
import com.amazonclone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
