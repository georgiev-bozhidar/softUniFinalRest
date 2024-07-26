package org.georgievbozhidar.softunifinal2rest.service;

import org.georgievbozhidar.softunifinal2rest.entity.dto.CreateFoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.FoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Food;

public interface FoodService {
    FoodDTO createFood(CreateFoodDTO createFoodDTO);
    void deleteFoodById(Long id);

    FoodDTO getFoodById(Long id);
}
