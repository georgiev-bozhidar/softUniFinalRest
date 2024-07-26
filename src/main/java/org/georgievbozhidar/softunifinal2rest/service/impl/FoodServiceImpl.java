package org.georgievbozhidar.softunifinal2rest.service.impl;

import org.georgievbozhidar.softunifinal2rest.entity.dto.CreateFoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.FoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Food;
import org.georgievbozhidar.softunifinal2rest.exception.FoodNotFoundException;
import org.georgievbozhidar.softunifinal2rest.repository.FoodRepository;
import org.georgievbozhidar.softunifinal2rest.service.FoodService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {
    private final ModelMapper modelMapper;
    private final FoodRepository foodRepository;

    public FoodServiceImpl(ModelMapper modelMapper, FoodRepository foodRepository) {
        this.modelMapper = modelMapper;
        this.foodRepository = foodRepository;
    }

    @Override
    public FoodDTO createFood(CreateFoodDTO createFoodDTO) {
        Food food = modelMapper.map(createFoodDTO, Food.class);
        foodRepository.save(food);
        return modelMapper.map(food, FoodDTO.class);
    }

    @Override
    public void deleteFoodById(Long id) {
        Food food = modelMapper.map(this.getFoodById(id), Food.class);
        foodRepository.delete(food);
    }

    @Override
    public FoodDTO getFoodById(Long id) {
        Optional<Food> optFood = foodRepository.findById(id);
        if (optFood.isEmpty()){
            throw new FoodNotFoundException();
        }

        return modelMapper.map(optFood.get(), FoodDTO.class);

    }
}
