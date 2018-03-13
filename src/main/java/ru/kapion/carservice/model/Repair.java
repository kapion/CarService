package ru.kapion.carservice.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken.Optional;

@Setter
@Getter
@Entity
public class Repair implements Serializable, Comparable<Repair> {

    public Repair() {
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String service;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;

    @Column(length = 1000000)
    @Lob
    private String description;

    @Column
    private BigDecimal cost;

    @ManyToOne(fetch = FetchType.LAZY)
    //указываем carId только для того, чтобы поле в БД REPAIR.car_id стало @NotNull
    @JoinColumn(name = "carId", nullable = false)
    private Car car;

    @Override
    public int compareTo(Repair that) {
       // return Integer.compare(car.carId, that.carId);
        return date.compareTo(that.getDate());
    }


}
