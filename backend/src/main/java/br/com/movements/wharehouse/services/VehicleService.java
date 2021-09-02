package br.com.movements.wharehouse.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.movements.exceptions.ObjectNotFoundException;
import br.com.movements.wharehouse.domain.Vehicle;
import br.com.movements.wharehouse.repositories.VehicleRepository;

@Service
public class VehicleService{
	@Autowired
	private VehicleRepository repository;
	
	public Page<Vehicle> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public Vehicle findOne(Long id) {
		Optional<Vehicle> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + Vehicle.class.getName()));
	}
}
