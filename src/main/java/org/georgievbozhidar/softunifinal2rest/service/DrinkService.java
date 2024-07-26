package org.georgievbozhidar.softunifinal2rest.service;

import org.georgievbozhidar.softunifinal2rest.entity.dto.CreateDrinkDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.DrinkDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Drink;

public interface DrinkService {
    DrinkDTO createDrink(CreateDrinkDTO createDrinkDTO);
    void deleteDrinkById(Long id);

    DrinkDTO getDrinkById(Long id);
}
