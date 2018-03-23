package ru.kapion.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kapion.carservice.dao.specific.OwnerRepository;
import ru.kapion.carservice.model.Owner;

import java.util.List;


@Service
public class OwnerService{
    @Autowired
    private OwnerRepository repository;

    public void save(Owner owner) {
        repository.save(owner);
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
        return repository.findById(id).orElse(new Owner());
    }

    public Owner createVirtual() {

        Owner newClient = new Owner();
        newClient.setId(1);
        newClient.setName("Виртуальный клиент");
        newClient.setPhone("+79999999999");
        newClient.setNote("Виртуальный клиент, данные которого требуется изменить после создания");
        return newClient;
    }



    //public List<Car> getCars(Integer id) {  return repository.findById(id).orElse(new Owner()).getRepairs();   }
}
