package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Inmueble {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String provincia;
	private String localidad;
	private String direccion;
	private String foto;
	private Double precio;
	private Boolean disponible;
	
	@ManyToOne
	private Usuario inquilino;
	
	@OneToOne
	private Torneo torneo;
	
	@OneToOne
	private Direccion direccion1;
	
	
	public Direccion getDireccion1() {
		return direccion1;
	}
	public void setDireccion1(Direccion direccion1) {
		this.direccion1 = direccion1;
	}
	public Torneo getTorneo() {
		return torneo;
	}
	public Usuario getInquilino() {
		return inquilino;
	}
	public void setInquilino(Usuario inquilino) {
		this.inquilino = inquilino;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}
	public Boolean getDisponible() {
		return disponible;
	}
	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}
	
	
}
