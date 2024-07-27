package org.georgievbozhidar.softunifinal2rest.service.impl;

import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateDrinkDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.DrinkDTO;
import org.georgievbozhidar.softunifinal2rest.entity.enums.DrinkType;
import org.georgievbozhidar.softunifinal2rest.entity.model.Drink;
import org.georgievbozhidar.softunifinal2rest.exception.DrinkNotFoundException;
import org.georgievbozhidar.softunifinal2rest.repository.DrinkRepository;
import org.georgievbozhidar.softunifinal2rest.service.DrinkService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DrinkServiceImpl implements DrinkService {
    private final ModelMapper modelMapper;
    private final DrinkRepository drinkRepository;

    public DrinkServiceImpl(ModelMapper modelMapper, DrinkRepository drinkRepository) {
        this.modelMapper = modelMapper;
        this.drinkRepository = drinkRepository;
    }

    @Override
    public Drink findById(Long id) {
        Optional<Drink> optDrink = drinkRepository.findById(id);
        if (optDrink.isEmpty()) {
            throw new DrinkNotFoundException();
        }

        return optDrink.get();
    }

    @Override
    public Drink findByName(String name) {
        Optional<Drink> optDrink = drinkRepository.findByName(name);
        if (optDrink.isEmpty()) {
            throw new DrinkNotFoundException();
        }

        return optDrink.get();
    }

    @Override
    public DrinkDTO getById(Long id) {
        return modelMapper.map(this.findById(id), DrinkDTO.class);
    }

    @Override
    public DrinkDTO getByName(String name) {
        return modelMapper.map(this.findByName(name), DrinkDTO.class);
    }

    @Override
    public Set<DrinkDTO> getAllByType(DrinkType drinkType) {
        Set<Drink> drinks = Set.copyOf(drinkRepository.findAllByDrinkType(drinkType));
        Set<DrinkDTO> drinkDTOs = new HashSet<>();

        for (Drink drink : drinks) {
            drinkDTOs.add(modelMapper.map(drink, DrinkDTO.class));
        }

        return drinkDTOs;
    }

    @Override
    public Set<DrinkDTO> getAll() {
        Set<Drink> drinks = Set.copyOf(drinkRepository.findAll());
        Set<DrinkDTO> drinkDTOs = new HashSet<>();

        for (Drink drink : drinks) {
            drinkDTOs.add(modelMapper.map(drink, DrinkDTO.class));
        }

        return drinkDTOs;
    }

    @Override
    public DrinkDTO createDrink(CreateDrinkDTO createDrinkDTO) {
        Drink drink = modelMapper.map(createDrinkDTO, Drink.class);
        drinkRepository.save(drink);
        return modelMapper.map(this.getByName(drink.getName()), DrinkDTO.class);
    }

//    @Override
//    public DrinkDTO updateDrink(UpdateDrinkDTO updateDrinkDTO) {
//        return null;
//    }

    @Override
    public void deleteDrink(Long id) {
        drinkRepository.delete(this.findById(id));
    }
}
