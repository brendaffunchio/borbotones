package ar.edu.unlam.tallerweb1.modelo;

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

import javax.persistence.OneToOne;

import org.springframework.context.annotation.Scope;


// Clase que modela el concepto de Usuario, la anotacion @Entity le avisa a hibernate que esta clase es persistible
// el paquete ar.edu.unlam.tallerweb1.modelo esta indicado en el archivo hibernateCOntext.xml para que hibernate
// busque entities en él
@Scope("session")
@Entity
public class Usuario implements Comparable<Usuario> {

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
	
	@ManyToMany(fetch = FetchType.LAZY)
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
		return this.torneosParticipa;
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

	@Override
	public int compareTo(Usuario usuario) {
		
		this.email.compareTo(usuario.getEmail());  
		return this.email.compareTo(usuario.getEmail()); 
		
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	


}
