package ru.kapion.carservice.service;

import org.springframework.stereotype.Service;
import ru.kapion.carservice.dao.specific.CarRepository;
import ru.kapion.carservice.dao.specific.OwnerRepository;
import ru.kapion.carservice.dao.specific.RepairRepository;

import java.util.stream.Collectors;


@Service
public class MainService {

    private CarRepository repositoryCar;
    private RepairRepository repositoryRepair;
    private OwnerRepository repositoryOwner;

    public MainService(CarRepository repositoryCar, RepairRepository repositoryRepair, OwnerRepository repositoryOwner) {
        this.repositoryCar = repositoryCar;
        this.repositoryRepair = repositoryRepair;
        this.repositoryOwner = repositoryOwner;
    }

    public Long getCountRepairs() {
        return repositoryRepair.count();
    }
    public Long getCountRepairsComplete() {
        return repositoryRepair.findAllByOrderByIdDesc().stream().filter(r->r.getActive()).collect(Collectors.counting());
    }
    public Long getCountCars() {
        return repositoryCar.count();
    }
    public Long getCountOwners() {
        return repositoryOwner.count();
    }
}
