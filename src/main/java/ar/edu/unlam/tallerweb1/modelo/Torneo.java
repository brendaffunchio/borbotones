package ar.edu.unlam.tallerweb1.modelo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Torneo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String juego;
	private Double precio;
	private String fecha;
	private String horario;
	private String categoria;
	private Integer cupo;
	private Integer inscriptos;
	private Boolean estadoCompleto;

	@ManyToOne(optional = false)
	private Usuario usuario;
	
	@ManyToMany(mappedBy = "torneos")
	private List<Usuario> participantes = new LinkedList<Usuario>();

	@OneToOne(mappedBy="torneo")
	private Inmueble inmueble;
	
	public Inmueble getInmueble() {
		return inmueble;
	}

	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}

	
	public List<Usuario> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Usuario> participantes) {
		this.participantes = participantes;
	}

	public Integer getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(Integer inscriptos) {
		this.inscriptos = inscriptos;
	}

	public Boolean getEstadoCompleto() {
		return estadoCompleto;
	}

	public void setEstadoCompleto(Boolean estadoCompleto) {
		this.estadoCompleto = estadoCompleto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Integer getLimiteCupo() {
		return cupo;
	}

	public void setLimiteCupo(Integer limiteCupo) {
		this.cupo = limiteCupo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCupo() {
		return cupo;
	}

	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}

	public String getJuego() {
		return juego;
	}

	public void setJuego(String juego) {
		this.juego = juego;
	}
	
	public Usuario getInquilino() {
		return usuario;
	}

}
