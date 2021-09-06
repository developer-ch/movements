package br.com.movements.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.movements.wharehouse.domain.Vehicle;
import jdk.jfr.BooleanFlag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class VehicleDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Getter @Setter private Long id;
	
	@Size(min=7,max=7, message = "A Placa deve conter 7 caracteres.")
	@Pattern(regexp = "^[a-zA-Z]{3}[0-9][A-Za-z0-9][0-9]{2}$",message = "A Placa deve seguir o padrão CCC0C00 ou CCC0000." )
	@Getter @Setter private String plaqueOne;
	
	@Size(max = 7, message = "A Placa deve conter no maximo 7 caracteres.")
	@Getter @Setter private String plaqueTwo;
	
	@BooleanFlag
	@Getter @Setter private Boolean active;
	
	public VehicleDTO(Vehicle vehicle) {
		this.id = vehicle.getId();
		this.plaqueOne = vehicle.getPlaqueOne();
		this.plaqueTwo = vehicle.getPlaqueTwo();
		this.active = vehicle.getActive();
	}
}
