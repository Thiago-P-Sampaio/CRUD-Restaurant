package com.example.Restaurant.Controller;

import com.example.Restaurant.DTO.MenuDTO;
import com.example.Restaurant.Model.RestaurantModel;
import com.example.Restaurant.Repository.MenuRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/menu")
public class RestaurantController {

    @Autowired
    MenuRepository repository;

    @GetMapping("/items")
    public ResponseEntity getAll(){
        var getAll = repository.findAll();
        return ResponseEntity.ok(getAll);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable UUID id){
        var getFood = repository.findById(id);
        return ResponseEntity.ok(getFood);
    }

    @PostMapping("/new")
    public ResponseEntity InsetNewFood(@RequestBody MenuDTO info){
        RestaurantModel NewFood = new RestaurantModel(info);
        repository.save(NewFood);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/item/{id}")

    public ResponseEntity UpdtFood(@PathVariable @Valid UUID id, @RequestBody @Valid  MenuDTO info){
        Optional<RestaurantModel>FoundFood = repository.findById(id);
        if (FoundFood.isPresent()){
            RestaurantModel PutFood = FoundFood.get();
            PutFood.setFood(info.Food());
            PutFood.setDescription(info.Description());
            PutFood.setCategory(info.Category());
            PutFood.setPrice(info.Price());
            PutFood.setAvailability(info.Availability());

                repository.save(PutFood);

            return  ResponseEntity.ok().build();

        } else return  ResponseEntity.notFound().build();

    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity DeleteFood(@PathVariable UUID id){
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }  else return ResponseEntity.notFound().build();
    }
}
