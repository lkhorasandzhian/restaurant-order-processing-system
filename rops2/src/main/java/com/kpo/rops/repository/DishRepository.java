package com.kpo.rops.repository;


import com.kpo.rops.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Special repository for working with 'DISH' database.
 */
@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
}
