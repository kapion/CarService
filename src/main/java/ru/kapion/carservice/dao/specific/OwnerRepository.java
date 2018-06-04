package ru.kapion.carservice.dao.specific;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kapion.carservice.dao.CommonRepository;
import ru.kapion.carservice.model.Owner;

import java.util.List;


@Repository
public interface OwnerRepository extends CommonRepository<Owner, Integer> {

    //@Query("select o from Owner o where o.phone = ?1")
    //Вместо Query - волшебные выражения
    List<Owner> findByPhone(String phone);
}


