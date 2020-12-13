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
	public void guardarFoto(Object objeto, MultipartFile foto)
			throws FotoInexistenteException, FileUploadException, IOException {
		if (!foto.isEmpty()) {
			
			if (objeto instanceof Inmueble) {

				String ruta = "C://Producto//inmuebles";
				byte[] bytes;
				bytes = foto.getBytes();
				Path rutaAbsoluta = Paths.get(ruta + "//" + foto.getOriginalFilename());
				Files.write(rutaAbsoluta, bytes);
				((Inmueble) objeto).setFoto(foto.getOriginalFilename());

			}

			if (objeto instanceof Torneo) {

				String ruta = "C://Producto//torneos";
				byte[] bytes;
				bytes = foto.getBytes();
				Path rutaAbsoluta = Paths.get(ruta + "//" + foto.getOriginalFilename());
				Files.write(rutaAbsoluta, bytes);
				((Torneo) objeto).setFoto(foto.getOriginalFilename());
			}

		}

		
		else if (foto.isEmpty()) {

			throw new FotoInexistenteException();

		}
		
		
		else {

			throw new FileUploadException("Fallo en la subida de la foto");
		}
		
	}

	




	
	

}
