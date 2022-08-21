package br.com.infocomrobson.deploy.service;

import br.com.infocomrobson.deploy.dto.CalculaValor;
import br.com.infocomrobson.deploy.exception.ParkingNotFoundException;
import br.com.infocomrobson.deploy.model.Parking;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {
    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        String id = getUUID();
        String id1 = getUUID();
        Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
        parking.setEntryDate(LocalDateTime.now());
        Parking parking1 = new Parking(id1, "WAS-1234", "SP", "IDEA", "VERMELHO");
        parking1.setEntryDate(LocalDateTime.now());
        parkingMap.put(id, parking);
        parkingMap.put(id1, parking1);
    }

    public List<Parking> findAll(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }

//    public void create(Parking parking){
//        parkingMap.put(getUUID(), parking);
//    }

    public Parking create(Parking parking){
        parking.setId(getUUID());
        parking.setEntryDate(LocalDateTime.now());
        parkingMap.put(parking.getId(), parking);
        return parking;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if(parking == null){
            throw new ParkingNotFoundException(id);
        }
        return parking;
    }

    public void delete(String id) {
        findById(id); // checa se existe para poder apagar
        parkingMap.remove(id);
    }

    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        if(parkingCreate.getColor() != null) parking.setColor(parkingCreate.getColor());
        if(parkingCreate.getState() != null) parking.setState(parkingCreate.getState());
        if(parkingCreate.getModel() != null) parking.setModel(parkingCreate.getModel());
        if(parkingCreate.getLicense() != null) parking.setLicense(parkingCreate.getLicense());
        parkingMap.replace(id,parking);
        return parking;
    }

    public Parking exit(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        double valor = CalculaValor.calcular(parking.getEntryDate(), parking.getExitDate());
        parking.setBill(valor);
        update(id, parking);
        return parking;
    }
}
