package es.actividad.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.actividad.modelo.Books;
import es.actividad.modelo.dao.DaoBooks;


// Aqui realizamos el CRUD contra la entidad Books. La bbdd de libros está simulada en memoria
// en la clase DaoBooks

@RestController
public class BooksController {

	// Realizamos inyección de dependencias del objeto DaoBooks mediante @Autowired
	// para darlo de alta en el contexto de Spring. 
	
	@Autowired
	private DaoBooks daoBooks;
	
	/*
	 * En este método se da de alta un libro usando el método Post. Se produce un JSON
	 * La URL para acceder a este método seria: "http://localhost:8080/books" 
	 */
	
	// añadir libro / DAR DE ALTA
		@PostMapping (path="books", consumes = MediaType.APPLICATION_JSON_VALUE, 
				produces=MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<Books> addBook (@RequestBody Books b) {
			System.out.println("Añadir libro: objeto libro: " + b);
			daoBooks.add(b);
			return new ResponseEntity<Books>(b, HttpStatus.CREATED); // 201 Created
		}
	
	// Borrar libro por ID / DAR DE BAJA
	
		@DeleteMapping (path="books/{id}")
		public ResponseEntity<Books> deleteBooks (@PathVariable("id") int id) {
			System.out.println("ID a borrar: " + id);
			Books bDel = daoBooks.delete(id);
			if(bDel != null) {
				return new ResponseEntity<Books> (bDel, HttpStatus.OK); // 200 OK
			} else {
				return new ResponseEntity<Books> (HttpStatus.NOT_FOUND); //404 NOT FOUND
			}
		}
	
		// Modificar libro por ID
		
		@PutMapping (path="books/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Books> modifyBooks (@PathVariable("id") int id, @RequestBody Books b) {
			System.out.println("ID a modificar: " + id);
			b.setId(id);
			Books bUpdate = daoBooks.update(b);
			
			if(bUpdate != null) {
				return new ResponseEntity<Books>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Books>(HttpStatus.NOT_FOUND);
			}
		}
	
		// Obtener libros por id
		
		@GetMapping(path="books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<Books> getBooks(@PathVariable("id") int id) {
				System.out.println("Buscando libro con id: " + id);
				Books bGet = daoBooks.get(id);
				
				if(bGet != null) {
					return new ResponseEntity<Books>(bGet, HttpStatus.OK); // 200 OK
				} else {
					return new ResponseEntity<Books>(HttpStatus.NOT_FOUND); // 404 NF
				}
			}
		
		// Listar libros
		
		@GetMapping (path="books", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Books>> toListBooks (@RequestParam(name="title", 
			required= false) String title) {
			List<Books> listBooks = null;
			
			// HAY QUE DARLE UNA VUELTA A ESTE MÉTODO
			if(title == null) {
				System.out.println("Mostrando listado de libros: ");
				listBooks = daoBooks.List();
			} else {
				System.out.println("Listando libros por titulo: " + title);
				listBooks = daoBooks.List();
			}
			// METODO MIRAR !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
			return new ResponseEntity<List<Books>>(listBooks, HttpStatus.OK); // 200 OK
		}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	

