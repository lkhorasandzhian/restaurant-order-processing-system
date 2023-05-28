package com.kpo.rops.controllers;

import com.kpo.rops.models.Dish;
import com.kpo.rops.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for menu information.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @Autowired
    DishRepository dishRepository;

    /**
     * GET-request for info.
     * @return HTTP-status of user's request.
     */
    @GetMapping
    public ResponseEntity<?> getMenuInfo() {
        StringBuilder msgInfo = new StringBuilder("Menu info:\n");

        List<Dish> menu = dishRepository.findAll();

        for (var dish : menu) {
            msgInfo.append("Id: ").append(dish.getId()).append("\n");
            msgInfo.append("Name: ").append(dish.getName()).append("\n");
            msgInfo.append("Description: ").append(dish.getDescription()).append("\n");
            msgInfo.append("Price: ").append(dish.getPrice().toString()).append("\n");
            msgInfo.append("Quantity: ").append(dish.getQuantity().toString()).append("\n");
            msgInfo.append("\n\n");
        }

        return ResponseEntity.ok(msgInfo);
    }
}
