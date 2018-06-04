package ru.kapion.carservice.service;

import org.springframework.stereotype.Service;
import ru.kapion.carservice.dao.specific.RepairRepository;
import ru.kapion.carservice.model.Repair;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


@Service
public class RepairService {

    private RepairRepository repository;

    public RepairService(RepairRepository repository) {
        this.repository = repository;
    }

    public void save(Repair repair) {
        repository.save(repair);
    }
    public void delete(Integer id) {
        repository.delete(getById(id));
    }

    public List<Repair> getAll() {
        return repository.findAllByOrderByIdDesc();
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
        return getAll().stream()
                .filter(repair -> repair.getDate().equals(localDate))
                .anyMatch(repair -> repair.getTime().equals(localTime));
     }
}
