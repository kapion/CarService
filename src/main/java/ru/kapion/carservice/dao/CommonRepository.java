package ru.kapion.carservice.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@NoRepositoryBean
public interface CommonRepository<T,ID> extends JpaRepository<T,ID> {

    default List<T> getAll (){
      return this.findAll() == null ? null :
        StreamSupport
                .stream(
                        Spliterators.spliteratorUnknownSize(this.findAll().iterator(), Spliterator.NONNULL),
                        false)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }


}
