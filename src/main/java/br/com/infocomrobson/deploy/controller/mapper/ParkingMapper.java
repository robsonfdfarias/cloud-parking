package br.com.infocomrobson.deploy.controller.mapper;

import br.com.infocomrobson.deploy.dto.ParkingCreateDTO;
import br.com.infocomrobson.deploy.dto.ParkingDTO;
import br.com.infocomrobson.deploy.model.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingDTO parkingDTO(Parking parking){
        return MODEL_MAPPER.map(parking, ParkingDTO.class);
    }

    public Parking parkingCreateDtoToParking(ParkingCreateDTO parkingCreateDTO){
        return MODEL_MAPPER.map(parkingCreateDTO, Parking.class);
    }

    public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
        return parkingList.stream().map(this::parkingDTO).collect(Collectors.toList());
    }
}
