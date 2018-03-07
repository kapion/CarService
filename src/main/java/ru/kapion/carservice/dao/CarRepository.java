package ru.kapion.carservice.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kapion.carservice.model.Car;


@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {
}
