package ar.edu.unlam.tallerweb1.modelo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.Session;


// Clase que modela el concepto de Usuario, la anotacion @Entity le avisa a hibernate que esta clase es persistible
// el paquete ar.edu.unlam.tallerweb1.modelo esta indicado en el archivo hibernateCOntext.xml para que hibernate
// busque entities en él
@Entity
public class Usuario {

	// La anotacion id indica que este atributo es el utilizado como clave primaria de la entity, se indica que el valor es autogenerado.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// para el resto de los atributo no se usan anotaciones entonces se usa el default de hibernate: la columna se llama igual que
	// el atributo, la misma admite nulos, y el tipo de dato se deduce del tipo de dato de java.
	@Column(unique = true)
	private String email;
	
	private String nombre;
	private String apellido;
	private String password;
	private String rol;
	@Column(nullable = false)
	private Integer torGanados;
	

	@OneToOne
	private Direccion direccion;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "participa")
	private Set <Torneo> torneosParticipa = new TreeSet<Torneo>();
	
	
	public void participarEnTorneo(Torneo torneo) {
		
		this.torneosParticipa.add(torneo);
	}
	
	public void desuscribirseDeTorneo(Torneo torneo) {
		if (this.torneosParticipa.contains(torneo)) {
			
			this.torneosParticipa.remove(torneo);
		}
		
	}
	
	public Set<Torneo> getTorneosParticipa() {
		return torneosParticipa;
	}
	public void setTorneosParticipa(Set<Torneo> torneosParticipa) {
		this.torneosParticipa = torneosParticipa;
	}

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public Integer getTorGanados() {
		return torGanados;
	}
	public void setTorGanados(Integer torGanados) {
		this.torGanados = torGanados;
	}


}
