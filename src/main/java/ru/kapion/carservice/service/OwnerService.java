package ru.kapion.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kapion.carservice.dao.specific.OwnerRepository;
import ru.kapion.carservice.model.Owner;

import java.util.List;
import java.util.Random;


@Service
public class OwnerService{
    @Autowired
    private OwnerRepository repository;

    public Integer save(Owner owner) {
       repository.save(owner);
       Owner savedOwner = repository.findByUniquePhone(owner.getPhone()).stream().findFirst().get();
       return savedOwner.getId();
    }
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<Owner> getAll(boolean isCreate) {
        List<Owner> all = repository.getAll();
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
        newClient.setPhone("9"+random.nextInt(99999999));
        newClient.setNote("Клиент, добавленный вместе с первым автомобилем, данные которого требуется изменить после создания");
        return newClient;
    }


    public boolean isExist(Owner owner) {
        return repository.findByUniquePhone(owner.getPhone()).size() > 0;
    }


    //public List<Car> getCars(Integer id) {  return repository.findById(id).orElse(new Owner()).getRepairs();   }
}
