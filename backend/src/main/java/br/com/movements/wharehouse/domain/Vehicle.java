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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"plaqueOne","plaqueTwo"},name = "vehicle_uk")})
public class Vehicle implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Getter @Setter private Long id;
	
	@Column(nullable = false, length=7)
	@Getter @Setter private String plaqueOne;
	
	@Column(nullable = false, length=7,columnDefinition = "varchar(8) default ''")	
	@Getter @Setter private String plaqueTwo;
		
	@Column(nullable = false, columnDefinition = "tinyint(1) default 1")
	@Getter @Setter private Boolean active;
}
