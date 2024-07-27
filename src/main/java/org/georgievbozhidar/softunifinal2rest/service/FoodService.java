package org.georgievbozhidar.softunifinal2rest.service;

import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateFoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.FoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Food;

public interface FoodService {
    Food findById(Long id);
    Food findByName(String name);
    FoodDTO getById(Long id);
    FoodDTO getByName(String name);

    FoodDTO createFood(CreateFoodDTO createFoodDTO);

//    FoodDTO updateFood(UpdateFoodDTO updateFoodDTO);

    void deleteFood(Long id);

}
