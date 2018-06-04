package ru.kapion.carservice.dao.specific;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kapion.carservice.dao.CommonRepository;
import ru.kapion.carservice.model.Car;
import ru.kapion.carservice.model.Owner;

import java.util.List;

@Repository
public interface CarRepository extends CommonRepository<Car, Integer> {

    //@Query("select c from Car c where c.regNum = ?1")
    //Вместо Query - волшебные выражения :)
    List<Car> findByRegNum(String regNum);
}
