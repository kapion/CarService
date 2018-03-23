package ru.kapion.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kapion.carservice.dao.specific.CarRepository;
import ru.kapion.carservice.model.Car;
import ru.kapion.carservice.model.Owner;
import ru.kapion.carservice.model.Repair;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CarService {
    @Autowired
    private CarRepository repository;

    public void save(Car car) {
        repository.save(car);
    }
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<Car> getAll() {
        return repository.getAll();
    }

    public Car getById(Integer id) {
        return repository.findById(id).orElse(new Car());
    }

    public List<Repair> getRepairs(Integer id) {
        return repository.findById(id).orElse(new Car()).getRepairs()
                .stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    public Owner getOwner(Integer id) {
        return getById(id).getOwner();
    }
}
