package ru.kapion.carservice.model;

import lombok.Getter;
import lombok.Setter;
import ru.kapion.carservice.utils.DicHelper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Setter
@Getter
@Entity
public class Car implements Serializable, Comparable<Car> {
    public Car() {
        this.creationTimestamp = System.currentTimeMillis();
        this.owner = new Owner();
    }

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

    @Override
    public int compareTo(Car that) {
        return Long.compare(this.creationTimestamp, that.creationTimestamp);
    }


    public String getEngineTypeName() {
         return DicHelper.getEngineTypes().get(engineType);
    }

    @OneToMany(mappedBy = "car",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Repair> repairs = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    //указываем ownerId только для того, чтобы поле в БД CAR.owner_id стало @NotNull
    @JoinColumn(name = "ownerId", nullable = false)
    private Owner owner;

}
