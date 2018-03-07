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
public class MainService {
    @Autowired
    private CarRepository repository;


    public Long getCountCars() {
        return repository.count();
    }

}
