package ru.kapion.carservice.dao.specific;

import org.springframework.stereotype.Repository;
import ru.kapion.carservice.dao.CommonRepository;
import ru.kapion.carservice.model.Owner;


@Repository
public interface OwnerRepository extends CommonRepository<Owner, Integer> {
}
