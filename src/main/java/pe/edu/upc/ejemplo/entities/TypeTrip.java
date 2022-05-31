package pe.edu.upc.ejemplo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "typetrip")
public class TypeTrip {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idTypeTrip;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;

	public TypeTrip() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeTrip(int idTypeTrip, String name) {
		super();
		this.idTypeTrip = idTypeTrip;
		this.name = name;
	}

	public int getIdTypeTrip() {
		return idTypeTrip;
	}

	public void setIdTypeTrip(int idTypeTrip) {
		this.idTypeTrip = idTypeTrip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
