package br.com.infocomrobson.deploy.repository;

import br.com.infocomrobson.deploy.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
}
