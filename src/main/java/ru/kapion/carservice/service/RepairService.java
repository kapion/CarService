package ru.kapion.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kapion.carservice.dao.CarRepository;
import ru.kapion.carservice.dao.RepairRepository;
import ru.kapion.carservice.model.Car;
import ru.kapion.carservice.model.Repair;

import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class RepairService {
    @Autowired
    private RepairRepository repository;

    public void save(Repair repair) {
        repository.save(repair);
    }
    public void delete(Integer id) {
        repository.delete(getById(id));
    }

    public List<Repair> getAll() {
        return StreamSupport
                .stream(
                        Spliterators.spliteratorUnknownSize(repository.findAll().iterator(), Spliterator.NONNULL),
                        false)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public Repair getById(Integer id) {
        return repository.findById(id).orElse(new Repair());
    }
}
