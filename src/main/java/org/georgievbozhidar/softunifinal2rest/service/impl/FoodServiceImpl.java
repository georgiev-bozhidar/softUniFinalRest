package org.georgievbozhidar.softunifinal2rest.service.impl;

import jakarta.transaction.Transactional;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateFoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.FoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.update.UpdateFoodDTO;
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
    @Transactional
    public FoodDTO createFood(CreateFoodDTO createFoodDTO) {
        Food food = modelMapper.map(createFoodDTO, Food.class);
        Food createdFood = foodRepository.save(food);
        return modelMapper.map(createdFood, FoodDTO.class);
    }

    @Override
    @Transactional
    public FoodDTO updateFood(Long id, UpdateFoodDTO updateFoodDTO){
        Food food = this.findById(id);
        if (updateFoodDTO.getName() != null) food.setName(updateFoodDTO.getName());
        if (updateFoodDTO.getDescription() != null) food.setDescription(updateFoodDTO.getDescription());
        if (updateFoodDTO.getIngredients() != null) food.setIngredients(updateFoodDTO.getIngredients());

        FoodDTO foodDTO = modelMapper.map(foodRepository.save(food), FoodDTO.class);
        return foodDTO;
    }

    @Override
    @Transactional
    public void deleteFood(Long id) {
        foodRepository.delete(this.findById(id));
    }
}
