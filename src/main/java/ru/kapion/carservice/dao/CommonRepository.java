package ru.kapion.carservice.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@NoRepositoryBean
public interface CommonRepository<T,ID> extends JpaRepository<T,ID> {

    //все списки в порядке убывания
    List<T> findAllByOrderByIdDesc();
    /*
    default List<T> getAll (){
      return this.findAll() == null ? null :
        this.findAll().stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }*/
}
