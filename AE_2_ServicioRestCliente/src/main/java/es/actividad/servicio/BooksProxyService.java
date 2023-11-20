package es.actividad.servicio;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.actividad.entidad.Books;

@Service
public class BooksProxyService {

	// URL del servicio Rest de libros
	public static final String URL = "http://localhost:8080/books/";
	
	//Inyectamos el objeto RestTemplate que nos ayuda a hacer las peticiones GTTP al servicio REST
	private RestTemplate restTemplate;
	
	/**
	 * Método que obtiene un libro del servicio rest a partir de un ID. Si el ID no existe
	 * arrojará una excepción que se captura para hacer print del codigo de respuesta
	 * @param id del libro que queremos obtener
	 * @return Libro que se está buscando. Null si el libro no se encuentra en el servidor
	 * (404 NOT FOUND)
	 */
	
	public Books get(int id) {
		
		try {
			ResponseEntity<Books> re = restTemplate.getForEntity(URL + id, Books.class);
			HttpStatusCode hs=re.getStatusCode();
			
			if(hs == HttpStatus.OK) {
				return re.getBody();
			} else {
				System.out.println("obtener -> Respuesta no contemplada");
				return null;
			}
			
		} catch (HttpClientErrorException e) {
			System.out.println("obtener => No se ha encontrado el libro con id: " + id);
			System.out.println("Código de respuesta: " + e.getStatusCode());
			return null;
		}
	}
	
	/**
	 * Método para dar añadir un libro en el servicio REST
	 * 
	 * @param b Libro que queremos añadir
	 * @return Libro con el ID con el que se ha dado de alta en el servicio REST. Null en caso de que 
	 * no haya podido realizarse la operación
	 */
	
	public Books add(Books b) {
	
		try {
			ResponseEntity<Books> re = restTemplate.postForEntity(URL, b, Books.class);
			System.out.println("Añadir Libro => código de respuesta: " + re.getStatusCode());
			return re.getBody();
		} catch (HttpClientErrorException e) {
			System.out.println("Añadir libro => El libro no se ha dado de alta, id: " + b );
			System.out.println("Añadir libro => Código de respuesta: " + e.getStatusCode());
			return null;
			
		}
	}
	
	/**
	 * Método que modifica un libro en el servicio Rest
	 * @param b Libro que queremos modificar, se harña a partir del id.
	 * @return TRUE si se ha modificado la persona. False en caso contrario
	 */
	
	public boolean modify ( Books b) {
		try {
			restTemplate.put(URL + b.getId(), b, Books.class);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("Modificar libro => El libro no se ha modificado, id: " + b.getId());
			System.out.println("Modificar libro => Código de respuesta: " + e.getStatusCode());
			return false;
		}
	}
	
	/**
	 * Método que borra un libro del servicio Rest
	 * 
	 * @param id del libro que queremos borrar
	 * @return TRUE si el libro se ha borrado. FALSE en caso contrario
	 */
	
	public boolean delete(int id) {
		try {
			restTemplate.delete(URL + id);
			return true;
			
		}catch (HttpClientErrorException e) {
			System.out.println("Borrar libro => El libro no se ha borrado, id : " + id);
			System.out.println("Borrar libro => Código de respuesta: " + e.getStatusCode());
			return false;
		}
	}
	
	public List<Books> toList(String title) {
		
		String queryParams = "";
		if(title != null) {
			queryParams += "?title=" + title;
		}
		
		try {
			ResponseEntity<Books[]> response = 
					restTemplate.getForEntity(URL + queryParams, Books[].class); 
			Books[] arrayBooks = response.getBody();
			return Arrays.asList(arrayBooks);
		} catch (HttpClientErrorException e) {
			System.out.println("Listar libros => Error al obtener la lista de libros");
			System.out.println("Código de respuesta: " + e.getStatusCode());
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
