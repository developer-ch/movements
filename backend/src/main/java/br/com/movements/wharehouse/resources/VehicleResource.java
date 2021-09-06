package br.com.movements.wharehouse.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.movements.dto.VehicleDTO;
import br.com.movements.wharehouse.domain.Vehicle;
import br.com.movements.wharehouse.services.VehicleService;

@RestController
@RequestMapping(value="/vehicles")
public class VehicleResource {
	@Autowired
	private VehicleService service;
	
	
	@GetMapping
	public ResponseEntity<List<VehicleDTO>> findAll(){
		List<VehicleDTO> vehicles = service.findAll();
		return ResponseEntity.ok(vehicles);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<VehicleDTO>> findAll(@PageableDefault Pageable pageable){
		Page<VehicleDTO> vehicles = service.findAll(pageable);
		return ResponseEntity.ok(vehicles);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		Vehicle vehicle = service.find(id);
		return ResponseEntity.ok().body(vehicle) ;
	}
	
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody VehicleDTO objDTO){
		Vehicle obj = service.fromDTO(objDTO);
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody VehicleDTO objDTO, @PathVariable Long id){
		objDTO.setId(id);
		Vehicle obj = service.fromDTO(objDTO);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}