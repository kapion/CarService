package ru.kapion.carservice.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kapion.carservice.model.Repair;

@Repository
public interface RepairRepository extends CrudRepository<Repair,Integer>{

}
