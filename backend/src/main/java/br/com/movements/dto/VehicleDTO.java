package br.com.movements.dto;

import java.io.Serializable;

import br.com.movements.wharehouse.domain.Vehicle;
import lombok.Getter;
import lombok.Setter;

public class VehicleDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Getter @Setter private Long id;
	
	@Getter @Setter private String plaqueOne;
	
	@Getter @Setter private String plaqueTwo;
		
	@Getter @Setter private Boolean active;
	
	public VehicleDTO(Vehicle vehicle) {
		this.id = vehicle.getId();
		this.plaqueOne = vehicle.getPlaqueOne();
		this.plaqueTwo = vehicle.getPlaqueTwo();
		this.active = vehicle.getActive();
	}
}
