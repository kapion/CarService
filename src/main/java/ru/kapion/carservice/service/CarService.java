package ru.kapion.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kapion.carservice.dao.CarRepository;
import ru.kapion.carservice.model.Car;

import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class CarService {
    @Autowired
    private CarRepository repository;

    public void save(Car car) {
        repository.save(car);
    }
    public void delete(Integer id) {
        repository.delete(getById(id));
    }

    public List<Car> getAll() {
        return StreamSupport
                .stream(
                        Spliterators.spliteratorUnknownSize(repository.findAll().iterator(), Spliterator.NONNULL),
                        false)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public Car getById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
