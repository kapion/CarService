package ru.kapion.carservice.dao.specific;

import org.springframework.stereotype.Repository;
import ru.kapion.carservice.dao.CommonRepository;
import ru.kapion.carservice.model.Repair;

@Repository
public interface RepairRepository extends CommonRepository<Repair,Integer> {
}
