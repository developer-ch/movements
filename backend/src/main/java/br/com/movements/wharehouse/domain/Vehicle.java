package br.com.movements.wharehouse.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Data
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"plaqueOne","plaqueTwo"},name = "vehicle_uk")})
public class Vehicle implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false, length=7)
	private String plaqueOne;
	
	@Column(nullable = false, length=7, columnDefinition = "varchar(7) default ''")	
	private String plaqueTwo;
		
	@Column(nullable = false, columnDefinition = "tinyint(1) default 1")
	private Boolean active;
}
