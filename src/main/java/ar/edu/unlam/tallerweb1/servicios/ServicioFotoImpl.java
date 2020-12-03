package ar.edu.unlam.tallerweb1.servicios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.transaction.Transactional;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unlam.tallerweb1.modelo.FotoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

@Service
@Transactional
public class ServicioFotoImpl implements ServicioFoto {
	
	
	
	@Override
	public void validarFoto(MultipartFile foto) throws FotoInexistenteException {
		
		if (foto.isEmpty()) {

			throw new FotoInexistenteException();

		}

	}

	@Override
	public void guardarFotoInmueble(MultipartFile foto) throws FileUploadException, IOException {


		if (!foto.isEmpty()) {
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
	public void guardarFotoTorneo(MultipartFile foto) throws FileUploadException, IOException {


		if (!foto.isEmpty()) {
			String ruta = "C://Producto//torneos";

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
	public void setFoto(Object objeto, String fotoNombre) {
		
		if(objeto instanceof Inmueble) {
			
			((Inmueble) objeto).setFoto(fotoNombre);
			
		}
		
		if(objeto instanceof Torneo) {
			
			((Torneo) objeto).setFoto(fotoNombre);
			
		}
		
		
		
		
	}

}
