package ru.kapion.carservice.model.report;

import lombok.Getter;
import lombok.Setter;
import ru.kapion.carservice.model.Car;
import ru.kapion.carservice.model.Owner;
import ru.kapion.carservice.model.Repair;

import java.math.BigDecimal;

/*
* Сводный отчет по ремонтам
* */
@Setter
@Getter
public class SummaryReportModel {

   private Integer repoId;
   private Repair repair;
   private Car car;
   private Owner owner;
//   private Integer repairId;
//   private String service;  // название ремонта
//   private Integer carId;
//   private String carModel; //авто
//   private Integer ownerId;
//   private String name;     //имя клиента
//   private BigDecimal cost;
//   private boolean active;


}
