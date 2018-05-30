package ru.kapion.carservice.dao.specific;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kapion.carservice.dao.CommonRepository;
import ru.kapion.carservice.model.Owner;

import java.util.List;


@Repository
public interface OwnerRepository extends CommonRepository<Owner, Integer> {

    @Query("select o from Owner o where o.name = ?1 and o.phone = ?2")
    List<Owner> findByNameAndPhone(String name, String phone);


    @Query("select o from Owner o where o.phone = ?1")
    List<Owner> findByUniquePhone(String phone);
}


