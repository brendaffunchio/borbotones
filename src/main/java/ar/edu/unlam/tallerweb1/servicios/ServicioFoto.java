package ar.edu.unlam.tallerweb1.servicios;

import java.io.IOException;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unlam.tallerweb1.modelo.FotoInexistenteException;

public interface ServicioFoto {

	void validarFoto(MultipartFile foto) throws FotoInexistenteException;

	public void guardarFotoInmueble(MultipartFile foto) throws FileUploadException, IOException;

	public void guardarFotoTorneo(MultipartFile foto) throws FileUploadException, IOException;

	public void setFoto(Object objeto, String fotoNombre);

}
