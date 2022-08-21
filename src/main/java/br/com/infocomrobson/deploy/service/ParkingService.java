package br.com.infocomrobson.deploy.service;

import br.com.infocomrobson.deploy.model.Parking;
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
        Parking parking1 = new Parking(id1, "WAS-1234", "SP", "IDEA", "VERMELHO");
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
        return parkingMap.get(id);
    }
}
