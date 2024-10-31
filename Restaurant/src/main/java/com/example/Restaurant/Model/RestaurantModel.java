package com.example.Restaurant.Model;

import com.example.Restaurant.DTO.MenuDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table (name = "menu")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantModel {

    @Id @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private String Food;
    private String Description;
    private String Category;
    private float Price;
    private boolean Availability;

    public RestaurantModel(MenuDTO info) {
        this.Food = info.Food();
        this.Description= info.Description();
        this.Category=info.Category();
        this.Price=info.Price();
        this.Availability=info.Availability();
    }
}
