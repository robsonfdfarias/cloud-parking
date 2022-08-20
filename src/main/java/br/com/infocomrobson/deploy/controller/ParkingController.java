package br.com.infocomrobson.deploy.controller;

import br.com.infocomrobson.deploy.controller.mapper.ParkingMapper;
import br.com.infocomrobson.deploy.dto.ParkingDTO;
import br.com.infocomrobson.deploy.model.Parking;
import br.com.infocomrobson.deploy.service.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }


    @GetMapping("/")
    public List<ParkingDTO> findAll(){
//        List<ParkingDTO> parkingDTOList = new ArrayList<>();
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
//        for (Parking parking : parkingList) {
//            ParkingDTO dto = new ParkingDTO();
//            dto.setId(parking.getId());
//            dto.setLicense(parking.getLicense());
//            dto.setModel(parking.getModel());
//            dto.setBill(parking.getBill());
//            dto.setColor(parking.getColor());
//            dto.setEntryDate(parking.getEntryDate());
//            dto.setExitDate(parking.getExitDate());
//            dto.setState(parking.getState());
//            parkingDTOList.add(dto);
//        }
        return result;
    }
}
