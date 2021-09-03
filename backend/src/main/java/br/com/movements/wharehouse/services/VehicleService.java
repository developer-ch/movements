package br.com.movements.wharehouse.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.movements.dto.VehicleDTO;
import br.com.movements.wharehouse.domain.Vehicle;
import br.com.movements.wharehouse.repositories.VehicleRepository;
import br.com.movements.wharehouse.services.exceptions.DataIntegrityException;
import br.com.movements.wharehouse.services.exceptions.ObjectNotFoundException;

@Service
public class VehicleService{
	@Autowired
	private VehicleRepository repository;
	
	public Page<VehicleDTO> findAll(Pageable pageable){
		Page<Vehicle> pageVehicles = repository.findAll(pageable);
		Page<VehicleDTO> pageVehiclesDTO = pageVehicles.map(obj -> (new VehicleDTO(obj)));
		return pageVehiclesDTO;
	}
	
	public List<VehicleDTO> findAll(){
		List<Vehicle> listVehicles = repository.findAll();
		List<VehicleDTO> listVehiclesDTO = listVehicles.stream().map(obj -> (new VehicleDTO(obj))).collect(Collectors.toList());
		return listVehiclesDTO;
	}
	
	public Vehicle find(Long id) {
		Optional<Vehicle> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + Vehicle.class.getName()));
	}
	
	public Vehicle create(Vehicle obj) {
		obj.setId(null);
		obj.setActive(true);
		return repository.save(obj);
	}
	
	public Vehicle update(Vehicle obj) {
		Vehicle result = find(obj.getId());
		if(obj.getPlaqueTwo() == null) {
			obj.setPlaqueTwo(result.getPlaqueTwo());
		}
		if(obj.getActive() == null) {
			obj.setActive(result.getActive());
		}
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		 find(id);
		 try {
			 repository.deleteById(id);
		 }
		 catch (DataIntegrityViolationException e) {
			 throw new DataIntegrityException("Erro: Não foi possível excluir.");
		}
	}
	
	
}
