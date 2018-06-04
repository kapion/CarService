package ru.kapion.carservice.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kapion.carservice.dao.specific.CarRepository;
import ru.kapion.carservice.dao.specific.OwnerRepository;
import ru.kapion.carservice.dao.specific.RepairRepository;
import ru.kapion.carservice.model.Car;
import ru.kapion.carservice.model.Owner;
import ru.kapion.carservice.model.Repair;
import ru.kapion.carservice.model.report.SummaryReportModel;
import ru.kapion.carservice.service.CarService;
import ru.kapion.carservice.service.OwnerService;
import ru.kapion.carservice.service.RepairService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    private RepairService repairService;
    private CarService    carService;
    private OwnerService  ownerService;

    public ReportService(RepairService repairService, CarService carService, OwnerService ownerService) {
        this.repairService = repairService;
        this.carService = carService;
        this.ownerService = ownerService;
    }

    public List<SummaryReportModel> getReport(){
        List<SummaryReportModel> reportModels = new ArrayList<>();
        Optional<List<Repair>> repairsOp = Optional.ofNullable(repairService.getAll());
        repairsOp.ifPresent(repairs -> {
            repairs.forEach(repair -> {
                SummaryReportModel reportModel = new SummaryReportModel();
                reportModel.setRepair(repair);
                reportModel.setCar(repair.getCar());
                reportModel.setOwner(repair.getCar().getOwner());
                reportModels.add(reportModel);
            });
        });
        return reportModels;
    }

    public SummaryReportModel getReportByClient(Integer ownerId){
        SummaryReportModel reportModel = new SummaryReportModel();
        Optional<Owner> ownerOp = Optional.of(ownerService.getById(ownerId));
        ownerOp.ifPresent(owner -> {
            reportModel.setOwner(owner);
            Optional<List<Car>> carsOp = Optional.ofNullable(owner.getOwnerCars());
            carsOp.ifPresent(cars -> cars.forEach(car -> {
                reportModel.setCar(car);
                Optional<List<Repair>> repairsOp = Optional.ofNullable(car.getRepairs());
                repairsOp.ifPresent(repairs -> { repairs.forEach(repair -> {
                    reportModel.setRepair(repair);
                 });
                });
            }));
        });


       return reportModel;
    }

}


