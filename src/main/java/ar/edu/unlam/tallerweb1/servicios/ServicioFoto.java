package ar.edu.unlam.tallerweb1.servicios;

import java.io.IOException;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unlam.tallerweb1.modelo.FotoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;

public interface ServicioFoto {
	
	public void guardarFoto(Object objeto, MultipartFile foto) throws FotoInexistenteException, FileUploadException, IOException;




}
