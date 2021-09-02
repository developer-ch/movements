package br.com.movements.wharehouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.movements.wharehouse.domain.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
	
}
