package pe.edu.upc.ejemplo.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class BudgeTrip {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
 private int idBudgeTrip;
 @Column(name = "numBudget", length = 50,nullable = false)
private int numBudget;
 @Column(name = "numdias", length = 50,nullable = false)
 private int numdias;
 @Column(name = "numpeople", length = 50,nullable = false)
 private int numpeople;
     @ManyToOne
	@JoinColumn(name = "idTypeTrip")
	private TypeTrip typeTrip;
	public BudgeTrip() {
		super();
		//TODO Auto-generated constructor stub
	}
	public BudgeTrip(int idBudgeTrip, int numBudget, int numdias, int numpeople, TypeTrip typeTrip) {
		super();
		this.idBudgeTrip = idBudgeTrip;
		this.numBudget = numBudget;
		this.numdias = numdias;
		this.numpeople = numpeople;
		this.typeTrip = typeTrip;
	}
	public int getIdBudgeTrip() {
		return idBudgeTrip;
	}
	public void setIdBudgeTrip(int idBudgeTrip) {
		this.idBudgeTrip = idBudgeTrip;
	}
	public int getNumBudget() {
		return numBudget;
	}
	public void setNumBudget(int numBudget) {
		this.numBudget = numBudget;
	}
	public int getNumdias() {
		return numdias;
	}
	public void setNumdias(int numdias) {
		this.numdias = numdias;
	}
	public int getNumpeople() {
		return numpeople;
	}
	public void setNumpeople(int numpeople) {
		this.numpeople = numpeople;
	}
	public TypeTrip getTypeTrip() {
		return typeTrip;
	}
	public void setTypeTrip(TypeTrip typeTrip) {
		this.typeTrip = typeTrip;
	}
     
     
}
