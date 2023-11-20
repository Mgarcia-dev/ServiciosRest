package es.actividad.cliente;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import es.actividad.entidad.Books;
import es.actividad.servicio.BooksProxyService;

@SpringBootApplication
public class Ae2ServicioRestClienteApplication implements CommandLineRunner {

	@Autowired
	private BooksProxyService bps;
	
	@Autowired
	private ApplicationContext aContext;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("Cliente => Cargado el contexto de Spring");
		SpringApplication.run(Ae2ServicioRestClienteApplication.class, args);
	}

	// EL SCANNER NO FUNCIONA 
	
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("****** MENÚ CLIENTE ******");
		
		menuP();
		
		int option = 0;
		switch(option) {
		
		case 1:  // Dar de alta
			addBook();
			run();
			break;
			
		case 2: // Dar de baja
			delBook();
			run();

			break;
			
		case 3: // Modificar
			modBook();
			run();
			break;
			
		case 4:  // Obtener
			getBooks();
			run();
			break;
			
		case 5: // Listar
			toListBooks();
			run();
			break;
			
		case 6: // Salir
			stopApplication();
			break;
			
		default: 
			System.out.println("Opción no válida");
			run();
		
		}
	}

	public void menuP() {
		System.out.println("****** MENÚ ******");
		System.out.println("1.- Dar de alta un libro ");
		System.out.println("2.- Dar de baja un libro por ID ");
		System.out.println("3.- Modificar un libro por ID");
		System.out.println("4.- Obtener un libro por ID");
		System.out.println("5.- Listar todos los libros ");
		System.out.println("6.- Salir");
	}
	
	public void addBook() {
		Books book = new Books();
		Scanner sc = null;
		System.out.println("Dar de alta un libro => Introduzca los siguientes datos:");
		
		System.out.println("Título: ");
		String title = sc.nextLine();
		book.setTitle(title);
		
		System.out.println("Editorial");
		String editorial = sc.nextLine();
		book.setEditorial(editorial);
		
		System.out.println("Nota: ");
		int note = sc.nextInt();
		book.setNote(note);
		
		Books addBook = bps.add(book);
		System.out.println("Libro dado de alta " + addBook);
	}
	
	public void delBook() {
		Books book = new Books();
		Scanner sc = null;
		
		System.out.println(" Dar de baja un libro => Introduce el ID del libro");
		int id = sc.nextInt();
		
		boolean deleted = bps.delete(id);
	}
	
	public void getBooks() {
		Books book = new Books();
		
		System.out.println("Obtener libro por ID => Introduce el ID del libro: ");
		Scanner sc = null;
		int id = sc.nextInt();
		book = bps.get(book.getId());
		
	}
	
	public void modBook () {
		Books modifyBook = new Books();  
		System.out.println("Modificar libro => Introduce el ID del libro:");
		
		
		
		
		
	}
	
	
	public void toListBooks() {
		System.out.println("Listar libros");
		List<Books> bookList = bps.toList(null);
		bookList.forEach((v) -> System.out.println(v));
	}
	
	public void stopApplication() {
		SpringApplication.exit(aContext, () -> 0);
	}
	
	
	
}
