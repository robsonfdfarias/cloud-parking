package br.com.infocomrobson.deploy.controller;

import br.com.infocomrobson.deploy.controller.mapper.ParkingMapper;
import br.com.infocomrobson.deploy.dto.ParkingCreateDTO;
import br.com.infocomrobson.deploy.dto.ParkingDTO;
import br.com.infocomrobson.deploy.model.Parking;
import br.com.infocomrobson.deploy.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parking")
@EnableWebMvc
@Api(tags = "Controles da API")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping("/")
    @ApiOperation("Procurar todos os parkings")
    public ResponseEntity<List<ParkingDTO>> findAll(){
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @ApiOperation("Procurar um parking específico")
    public ResponseEntity<ParkingDTO> findById(@PathVariable Long id){
        Parking parking = parkingService.findById(id);

        ParkingDTO result = parkingMapper.parkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    @ApiOperation("Criar um parking")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO parkingCreateDTO){
        Parking parkingCreate = parkingMapper.parkingCreateDtoToParking(parkingCreateDTO);
        Parking parking = parkingService.create(parkingCreate);
        ParkingDTO result = parkingMapper.parkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Deletar um parking")
    public ResponseEntity delete(@PathVariable Long id){
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @ApiOperation("Atualizar um parking")
    public ResponseEntity<ParkingDTO> update(@PathVariable Long id, @RequestBody ParkingCreateDTO parkingCreateDTO){
        Parking parkingCreate = parkingMapper.parkingCreateDtoToParking(parkingCreateDTO);
        Parking parking = parkingService.update(id, parkingCreate);
        ParkingDTO result = parkingMapper.parkingDTO(parking);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/exit/{id}")
    @ApiOperation("Calcular valor para saída do carro")
    public ResponseEntity<ParkingDTO> exit(@PathVariable Long id){
        Parking parking = parkingService.exit(id);
        ParkingDTO result = parkingMapper.parkingDTO(parking);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
