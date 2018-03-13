package ru.kapion.carservice.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken.Optional;

@Setter
@Getter
@Entity
public class Repair implements Serializable, Comparable<Repair> {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Integer carId;

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



    private Car currentCar;

    public Car getCurrentCar() {
        return currentCar;
    }

    @ManyToOne
    @JoinColumn(name = "car_id",
            foreignKey = @ForeignKey(name = "CAR_ID_FK")
    )
    public void setCurrentCar(Car currentCar) {
        this.currentCar = currentCar;
    }

    @Override
    public int compareTo(Repair that) {
        return Integer.compare(this.carId, that.carId);
    }


}
