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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.criteria.Join;

import org.hibernate.annotations.Fetch;

@Entity
public class Torneo implements Comparable<Torneo>{

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
	private Double distanciaConUsuario;

	@Column(nullable = false)
	private Integer inscriptos;

	@Column(nullable = false)
	private Boolean estadoCompleto;

	@ManyToOne
	private Usuario creador;

	@ManyToOne
	private Usuario ganador;

	@ManyToMany(mappedBy = "torneosParticipa", cascade = CascadeType.ALL) 
	private Set<Usuario> participantes = new TreeSet<Usuario>();

	@ManyToOne
	private Inmueble inmuebleDelTorneo;

	
	public void agregarParticipante(Usuario participante) {
				
		this.participantes.add(participante);
		this.inscriptos++;
				
	}
	
	public void eliminarParticipante(Usuario participante) {
		
			this.participantes.remove(participante);
			this.inscriptos--;
		
	}
	
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

	public Double getDistanciaConUsuario() {
		return distanciaConUsuario;
	}

	public void setDistanciaConUsuario(Double distancia) {
		this.distanciaConUsuario = distancia;
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

	public Usuario getGanador() {
		return ganador;
	}

	public void setGanador(Usuario ganador) {
		this.ganador = ganador;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public void setCreador(Usuario creador) {
		this.creador = creador;
	}

	@Override
	public int compareTo(Torneo torneo) {
		this.juego.compareTo(torneo.getJuego());  
		return this.juego.compareTo(torneo.getJuego()); 
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((juego == null) ? 0 : juego.hashCode());
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
		Torneo other = (Torneo) obj;
		if (juego == null) {
			if (other.juego != null)
				return false;
		} else if (!juego.equals(other.juego))
			return false;
		return true;
	}



}
