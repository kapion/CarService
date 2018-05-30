package ru.kapion.carservice.service;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kapion.carservice.dao.specific.RepairRepository;
import ru.kapion.carservice.model.Repair;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
        return repository.getAll();
    }


    //Сумма по столбцу cost
    public BigDecimal getAllSum() {
        List<BigDecimal> costList = new ArrayList<>();
        Function<Repair, BigDecimal> totalMapper = r -> r.getCost().plus();

        BigDecimal result = getAll().stream()
                .map(totalMapper)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return result;
    }

    public Repair getById(Integer id) {
        return repository.findById(id).orElse(new Repair());
    }

    public boolean isExistRepair(LocalDate localDate, LocalTime localTime) {
        return repository.getAll().stream()
                .filter(repair -> repair.getDate().equals(localDate))
                .anyMatch(repair -> repair.getTime().equals(localTime));
     }
}
