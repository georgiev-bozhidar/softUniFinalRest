package org.georgievbozhidar.softunifinal2rest.service.impl;

import org.georgievbozhidar.softunifinal2rest.entity.dto.CreateDrinkDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.DrinkDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Drink;
import org.georgievbozhidar.softunifinal2rest.exception.DrinkNotFoundException;
import org.georgievbozhidar.softunifinal2rest.repository.DrinkRepository;
import org.georgievbozhidar.softunifinal2rest.service.DrinkService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DrinkServiceImpl implements DrinkService {
    private final ModelMapper modelMapper;
    private final DrinkRepository drinkRepository;

    public DrinkServiceImpl(ModelMapper modelMapper, DrinkRepository drinkRepository) {
        this.modelMapper = modelMapper;
        this.drinkRepository = drinkRepository;
    }

    @Override
    public DrinkDTO createDrink(CreateDrinkDTO createDrinkDTO) {
        Drink drink = modelMapper.map(createDrinkDTO, Drink.class);
        drinkRepository.save(drink);
        return modelMapper.map(drink, DrinkDTO.class);
    }

    @Override
    public void deleteDrinkById(Long id) {
        Drink drink = modelMapper.map(this.getDrinkById(id), Drink.class);
        drinkRepository.delete(drink);
    }

    @Override
    public DrinkDTO getDrinkById(Long id) {
        Optional<Drink> optDrink = drinkRepository.findById(id);
        if (optDrink.isEmpty()){
            throw new DrinkNotFoundException();
        }

        return modelMapper.map(optDrink.get(), DrinkDTO.class);
    }
}
