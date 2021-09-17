package br.com.movements.wharehouse.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.movements.dto.VehicleDTO;
import br.com.movements.wharehouse.domain.Vehicle;
import br.com.movements.wharehouse.repositories.VehicleRepository;
import br.com.movements.wharehouse.services.exceptions.DataIntegrityException;
import br.com.movements.wharehouse.services.exceptions.ObjectNotFoundException;

@Service
public class VehicleService{
	@Autowired
	private VehicleRepository repository;
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<VehicleDTO> findAll(Pageable pageable){
		Page<Vehicle> pageVehicles = repository.findAll(pageable);
		Page<VehicleDTO> pageVehiclesDTO = pageVehicles.map(obj -> (new VehicleDTO(obj)));
		return pageVehiclesDTO;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<VehicleDTO> findAll(){
		List<Vehicle> listVehicles = repository.findAll();
		List<VehicleDTO> listVehiclesDTO = listVehicles.stream().map(obj -> (new VehicleDTO(obj))).collect(Collectors.toList());
		return listVehiclesDTO;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Vehicle find(Long id) {
		Optional<Vehicle> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + Vehicle.class.getName()));
	}
	
	@Transactional
	public Vehicle create(Vehicle obj) {
		obj.setId(null);
		obj.setActive(true);	
		return repository.save(obj);
	}
	
	public Vehicle fromDTO(VehicleDTO objDTO) {
		return new Vehicle(objDTO.getId(), objDTO.getPlaqueOne(), objDTO.getPlaqueTwo(), objDTO.getActive());
	}
	
	@Transactional
	public Vehicle update(Vehicle obj) {
		Vehicle result = find(obj.getId());
		System.out.println(obj.getPlaqueTwo().equals(null));
		if(obj.getPlaqueTwo().equals(null)) {
			obj.setPlaqueTwo(result.getPlaqueTwo());
		}
		if(obj.getActive().equals(null)) {
			obj.setActive(result.getActive());
		}
		return repository.save(obj);
	}
	
	@Transactional
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
