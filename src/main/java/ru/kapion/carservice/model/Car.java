package ru.kapion.carservice.model;

import lombok.Getter;
import lombok.Setter;
import ru.kapion.carservice.utils.DicHelper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@Setter
@Getter
@Entity
public class Car implements Serializable, Comparable<Car> {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String carModel;
    @Column
    private Integer year;
    @Column
    private String engineType;

    @Column(length = 1000000)
    @Lob
    private String content;
    @Column
    private long creationTimestamp;

    public Car() {
        this.creationTimestamp = System.currentTimeMillis();
    }

    @Override
    public int compareTo(Car that) {
        return Long.compare(this.creationTimestamp, that.creationTimestamp);
    }


    public String getEngineTypeName() {
         return DicHelper.getEngineTypes().get(engineType);
    }

}
