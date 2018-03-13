package ru.kapion.carservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Owner implements Serializable, Comparable<Owner>  {

    public Owner() {
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String note;

    @Override
    public int compareTo(Owner owner) {
        return name.compareTo(owner.getName());
    }

    @OneToMany(mappedBy = "owner",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> ownerCars = new ArrayList<>();
}
