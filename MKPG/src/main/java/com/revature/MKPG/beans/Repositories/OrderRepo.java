package com.revature.MKPG.beans.Repositories;

import com.revature.MKPG.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepo extends JpaRepository<Order, Integer> {
}
