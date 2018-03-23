package ru.kapion.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kapion.carservice.dao.specific.CarRepository;
import ru.kapion.carservice.dao.specific.OwnerRepository;
import ru.kapion.carservice.dao.specific.RepairRepository;

import java.util.Collections;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class MainService {
    @Autowired
    private CarRepository repositoryCar;
    @Autowired
    private RepairRepository repositoryRepair;
    @Autowired
    private OwnerRepository repositoryOwner;

    public Long getCountRepairs() {
        return repositoryRepair.count();
    }
    public Long getCountRepairsComplete() {
        return repositoryRepair.getAll().stream().filter(r->r.getActive()).collect(Collectors.counting());
    }
    public Long getCountCars() {
        return repositoryCar.count();
    }
    public Long getCountOwners() {
        return repositoryOwner.count();
    }
}
