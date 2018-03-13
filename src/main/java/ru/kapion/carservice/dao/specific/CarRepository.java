package ru.kapion.carservice.dao.specific;

import org.springframework.stereotype.Repository;
import ru.kapion.carservice.dao.CommonRepository;
import ru.kapion.carservice.model.Car;

@Repository
public interface CarRepository extends CommonRepository<Car, Integer> {
}
