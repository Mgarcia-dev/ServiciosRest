package es.actividad.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.actividad.modelo.Books;




/*	Este objeto se va a encargar de hacer las consultas.
	Aquí también estarán guardados los 5 libros que tenemos
	preestablecidos en memoria */

@Component
public class DaoBooks {
	
	public List<Books> bookList;
	public int counter;
	
	/** Esta clase es el constructor, que crea los libros y los mete en una lista para que puedan 
	 * ser consumidos por el cliente.
	 */
	
	/*
	 * En esta clase también crearemos los métodos del CRUD que queremos para que el cliente interactúe:
	 * - Dar de alta un libro
	 * - Dar de baja un libro por ID
	 * - Modificar un libro por ID
	 * - Obtener un libro por ID
	 * - Listar todos los libros
	 */
	
	public DaoBooks () {
		System.out.println("DaoBooks -> Creando lista de libros");
		bookList = new ArrayList<Books> ();
		
		Books b1 = new Books(counter++, "El capitán Alatriste", "Debolsillo",10);
		Books b2 = new Books(counter++, "Las meditaciones de Marco Aurelio", "Alejandría",8);
		Books b3 = new Books(counter++, "Clean Code", "Pearson",9);
		Books b4 = new Books(counter++, "Git y GitHub desde cero", "Independiente",8);
		Books b5 = new Books(counter++, "El Gran Libro de HTML, CSS3, y JavaScript", "Marcombo",9);
		
		bookList.add(b1);
		bookList.add(b2);
		bookList.add(b3);
		bookList.add(b4);
		bookList.add(b5);
	}

	/**
	 * Introduce un nuevo libro en una posición al final del array. En el CRUD, es el método dar de alta un libro (1)
	 * @param b Libro que queremos introducir en la lista
	 */
	
	public void add(Books b) {
		b.setId(counter++);
		bookList.add(b);
	}
	
	/**
	 * Método que borra un libro de la lista a partir de su ID. En el CRUD es el método Dar de baja un libro por ID (2)
	 * @param id ID del libro que queremos borrar
	 * @return Devuelve el libro que se ha borrado. En caso de no existir, devolverá null.
	 */
	
	public Books delete(int id) {
		try {
			return bookList.remove(id);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Borrar -> El libro con id " + id + "no se encuentra en memoria");
			return null;
		}
	}
	
	/**
	 * Método que modifica un libro de una posición del array. En el CRUD es el método modificar libro por ID (3)
	 * @param b Contiene los atributos del objeto que deseamos modificar, pero b.getId() contiene la posición del array
	 * que queremos eliminar
	 * @return Libro modificado. Si el libro no existe, devolverá null.
	 */
	
	public Books update(Books b) {
		try {
			Books bAux = bookList.get(b.getId());
			bAux.setTitle(b.getTitle());
			bAux.setEditorial(b.getEditorial());
			bAux.setNote(b.getNote());

			return bAux;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Modificar -> El libro no se encuentra en memoria");
			return null;
		}
	}
	
	 /** Método que devuelve un libro a partir de su posición en el ArrayList.
	 * En el CRUD, esta es la opcion de obtener un libro por ID (4)
	 * @param posición que buscamos dentro del array
	 * @return Libro que ocupa la posicion del array. En caso de que no exista devolvera null
	 */
	
	public Books get(int position) {
		try {
			return bookList.get(position);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("El Libro no se encuentra en memoria");
			return null;
		}
	}
	
	/**
	 * Método que devuelve todos los libros dentro del array. En el CRUD, es la opción de Listar todos los libros (5)
	 * @return Lista con todos los libros del array
	 */
	public List<Books> List() {
		return bookList;
	}
}

	
	
	
	
	
	

