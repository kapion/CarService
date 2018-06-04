package ru.kapion.carservice.service;

import org.springframework.stereotype.Service;
import ru.kapion.carservice.dao.specific.OwnerRepository;
import ru.kapion.carservice.model.Owner;
import ru.kapion.carservice.utils.IntegerHelper;

import java.util.List;
import java.util.Random;


@Service
public class OwnerService{

    private OwnerRepository repository;

    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }

    public List<Owner> getAll(){
        return repository.findAllByOrderByIdDesc();
    }

    public Integer save(Owner owner) {
       //очищаем посторонние символы в номере
       owner.setPhone(IntegerHelper.delNoDigit(owner.getPhone()));
       repository.save(owner);
       Owner savedOwner = repository.findByPhone(owner.getPhone()).stream().findFirst().get();
       return savedOwner.getId();
    }
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<Owner> getAll(boolean isCreate) {
        List<Owner> all = getAll();
        if (all != null && all.size() == 0 && isCreate) {
            all.add(createVirtual());
        }
        return all;
    }
    public Owner getById(Integer id) {
         return id != null ? repository.findById(id).get() : null;
    }

    public Owner createVirtual() {
        Owner newClient = new Owner();
        newClient.setName("Виртуальный клиент");

        Random random = new Random();
        newClient.setPhone("79"+random.nextInt(999999999));
        newClient.setNote("Клиент, добавленный вместе с первым автомобилем, данные которого требуется изменить после создания");
        return newClient;
    }


    public boolean isExist(Owner owner) {
        return repository.findByPhone(owner.getPhone()).size() > 0;
    }


    //public List<Car> getCars(Integer id) {  return repository.findById(id).orElse(new Owner()).getRepairs();   }
}
