package pe.edu.upc.ejemplo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idUsuario;
	
	@Column(name = "fullName", nullable = false, length = 100)
	private String fullName;
	
	@Column(name = "email", nullable = false, length = 100)
	private String email;
	
	@Column(name = "displayName", nullable = false, length = 50)
	private String displayName;
	
	@Column(name="numPhone", nullable = false, length = 9)
	private String numPhone;
	
	@Column(name="nationality", nullable = false, length = 50)
	private String nationality;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(int idUsuario, String fullName, String email, String displayName, String numPhone,
			String nationality) {
		super();
		this.idUsuario = idUsuario;
		this.fullName = fullName;
		this.email = email;
		this.displayName = displayName;
		this.numPhone = numPhone;
		this.nationality = nationality;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getNumPhone() {
		return numPhone;
	}

	public void setNumPhone(String numPhone) {
		this.numPhone = numPhone;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
}
