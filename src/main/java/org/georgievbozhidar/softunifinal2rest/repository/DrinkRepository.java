package org.georgievbozhidar.softunifinal2rest.repository;

import org.georgievbozhidar.softunifinal2rest.entity.enums.DrinkType;
import org.georgievbozhidar.softunifinal2rest.entity.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Optional<Drink> findByName(String name);
    List<Drink> findAllByDrinkType(DrinkType drinkType);
}
