package br.com.movements.wharehouse.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.movements.wharehouse.domain.Vehicle;
import br.com.movements.wharehouse.services.VehicleService;

@RestController
@RequestMapping(value="/vehicles")
public class VehicleResource {
	@Autowired
	private VehicleService service;
	
	@GetMapping
	public ResponseEntity<?> findAll(@PageableDefault Pageable pageable){
		Page<Vehicle> vehicles = service.findAll(pageable);
		return ResponseEntity.ok(vehicles);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		Vehicle vehicle = service.findOne(id);
		return ResponseEntity.ok().body(vehicle) ;
	}

}