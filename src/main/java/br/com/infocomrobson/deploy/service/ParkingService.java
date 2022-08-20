package br.com.infocomrobson.deploy.service;

import br.com.infocomrobson.deploy.model.Parking;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {
    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        String id = getUUID();
        Parking parking = new Parking(id, "DMS-111", "SC", "CELTA", "PRETO");
        parkingMap.put(id, parking);
    }

    public List<Parking> findAll(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }

//    public List<Parking> findAll(){
//        var parking = new Parking();
//        parking.setColor("black");
//        parking.setLicense("MSS-1111");
//        parking.setModel("Vm Gol");
//        parking.setState("SP");
//        return Arrays.asList(parking);
//    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
