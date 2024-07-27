package org.georgievbozhidar.softunifinal2rest.service.impl;

import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateFoodDTO;
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
    public Food findById(Long id) {
        Optional<Food> optFood = foodRepository.findById(id);
        if (optFood.isEmpty()){
            throw new FoodNotFoundException();
        }

        return optFood.get();
    }

    @Override
    public Food findByName(String name) {
        Optional<Food> optFood = foodRepository.findByName(name);
        if (optFood.isEmpty()){
            throw new FoodNotFoundException();
        }

        return optFood.get();
    }

    @Override
    public FoodDTO getById(Long id) {
        return modelMapper.map(this.findById(id), FoodDTO.class);
    }

    @Override
    public FoodDTO getByName(String name) {
        return modelMapper.map(this.findByName(name), FoodDTO.class);
    }

    @Override
    public FoodDTO createFood(CreateFoodDTO createFoodDTO) {
        Food food = modelMapper.map(createFoodDTO, Food.class);
        foodRepository.save(food);
        return modelMapper.map(this.getByName(food.getName()), FoodDTO.class);
    }

//    @Override
//    public FoodDTO updateFood(UpdateFoodDTO updateFoodDTO){
//
//    }

    @Override
    public void deleteFood(Long id) {
        foodRepository.delete(this.findById(id));
    }
}
