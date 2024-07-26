package org.georgievbozhidar.softunifinal2rest.repository;

import org.georgievbozhidar.softunifinal2rest.entity.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByName(String name);
}
