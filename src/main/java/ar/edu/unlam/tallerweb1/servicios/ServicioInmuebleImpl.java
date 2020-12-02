package ar.edu.unlam.tallerweb1.servicios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInmueble;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service
@Transactional
public class ServicioInmuebleImpl implements ServicioInmueble {

	private RepositorioInmueble repositorioInmueble;
	private RepositorioUsuario repositorioUsuario;

	@Autowired
	public ServicioInmuebleImpl(RepositorioInmueble repositorioInmueble,
			RepositorioUsuario repositorioUsuario) {

		this.repositorioInmueble = repositorioInmueble;
		this.repositorioUsuario = repositorioUsuario;
	}

	@Override
	public List<Inmueble> mostrarInmuebles() {

		return repositorioInmueble.todosLosInmuebles();
	}

	@Override
	public void guardarInmueble(Inmueble inmueble,Direccion direccion) {

		repositorioInmueble.guardarInmueble(inmueble,direccion);

	}
	
	
	@Override
	public void validarFoto(MultipartFile foto) throws FileNotFoundException {
		
			if (foto.isEmpty())  {
			
			throw new FileNotFoundException("No se seleccionó una foto");

		}
		
	}
	
	
	public void guardarFoto(MultipartFile foto) throws FileUploadException, IOException   {

		
		if(!foto.isEmpty()) {
			String ruta = "C://Producto//inmuebles";

			byte[] bytes;
			bytes = foto.getBytes();
			Path rutaAbsoluta = Paths.get(ruta + "//" + foto.getOriginalFilename());
			Files.write(rutaAbsoluta, bytes);
		}
		
		else {
			
			throw new FileUploadException("Fallo en la subida de la foto");
		}

	}
	
	@Override
	public List<Inmueble> buscarInmueble(Long provinciaId, String nombreCiudad) {

		return repositorioInmueble.buscarInmueble(provinciaId, nombreCiudad);

	}

	@Override

	public Inmueble verDetallesInmueble(Long inmuebleId) {
		
		return repositorioInmueble.consultarInmueblePorId(inmuebleId);
		
		
	}

	@Override
	public void agregarInquilino(Long inmuebleId, Long usuarioId) {
		
		Inmueble inmueble=repositorioInmueble.consultarInmueblePorId(inmuebleId);
		Usuario inquilino = repositorioUsuario.consultarUsuarioPorId(usuarioId);
		
		if (!inmueble.equals(null)&&!inquilino.equals(null)
				&&inmueble.getDisponible().equals(true)){
			inmueble.setInquilino(inquilino);
			inmueble.setDisponible(false);
			repositorioInmueble.modificarInmueble(inmueble);
			
			
		}
		
		
	}


	@Override
	public void setFoto(Inmueble inmueble, String fotoNombre) {
		
		inmueble.setFoto(fotoNombre);
		
	}

	

}
