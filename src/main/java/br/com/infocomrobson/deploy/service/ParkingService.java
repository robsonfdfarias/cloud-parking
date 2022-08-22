package br.com.infocomrobson.deploy.service;

import br.com.infocomrobson.deploy.dto.CalculaValor;
import br.com.infocomrobson.deploy.exception.ParkingNotFoundException;
import br.com.infocomrobson.deploy.model.Parking;
import br.com.infocomrobson.deploy.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository repository;

    @Transactional(readOnly=true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll(){
        return repository.findAll();
    }

    @Transactional(readOnly=true)
    public Parking findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ParkingNotFoundException(id));
    }

    @Transactional
    public Parking create(Parking parking){
        parking.setEntryDate(LocalDateTime.now());
        return repository.save(parking);
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional
    public void delete(Long id) {
        Parking parking = findById(id);// checa se existe para poder apagar
        repository.delete(parking);
    }

    @Transactional
    public Parking update(Long id, Parking parkingCreate) {
        Parking parking = findById(id);
        if(parkingCreate.getColor() != null) parking.setColor(parkingCreate.getColor());
        if(parkingCreate.getState() != null) parking.setState(parkingCreate.getState());
        if(parkingCreate.getModel() != null) parking.setModel(parkingCreate.getModel());
        if(parkingCreate.getLicense() != null) parking.setLicense(parkingCreate.getLicense());
        return repository.save(parking);
    }

    @Transactional
    public Parking exit(Long id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        double valor = CalculaValor.calcular(parking.getEntryDate(), parking.getExitDate());
        parking.setBill(valor);
        update(id, parking);
        return parking;
    }
}
