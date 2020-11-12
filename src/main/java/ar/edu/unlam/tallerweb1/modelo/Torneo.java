package ar.edu.unlam.tallerweb1.modelo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	private String foto;
	private Integer cupo;

	@Column(nullable = false)
	private Integer inscriptos;

	@Column(nullable = false)
	private Boolean estadoCompleto;

	@ManyToOne
	private Usuario creador;

	@ManyToMany(mappedBy = "torneosParticipa")
	private Set<Usuario> participantes = new TreeSet<Usuario>();

	@ManyToOne
	private Inmueble inmuebleDelTorneo;

	public Inmueble getInmuebleDelTorneo() {
		return inmuebleDelTorneo;
	}

	public void setInmuebleDelTorneo(Inmueble inmuebleDelTorneo) {
		this.inmuebleDelTorneo = inmuebleDelTorneo;
	}

	
	public Set<Usuario> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Set<Usuario> participantes) {
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

	public Usuario getCreador() {
		return creador;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}
