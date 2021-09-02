package br.com.movements;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.movements.wharehouse.domain.Vehicle;
import br.com.movements.wharehouse.repositories.VehicleRepository;

@SpringBootApplication
public class MovementsApplication implements CommandLineRunner{
	@Autowired
	private VehicleRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(MovementsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Vehicle vehicle = new Vehicle(null,"CCC9C99", "CCC0C00",true);
		Vehicle vehicle2 = new Vehicle(null,"CCC9C97", "CCC0C00",true);
		
		repository.saveAll(Arrays.asList(vehicle,vehicle2));
	}

}
