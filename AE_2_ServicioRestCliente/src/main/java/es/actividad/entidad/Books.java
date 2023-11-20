package es.actividad.entidad;

/*
 * Clase que contiene los atributos que se utilizaran para almacenar
 * los libros que queremos en el servidor.
 * 
*/

public class Books {

	private int id;
	private String title;
	private String editorial;
	private int note;
	
	public Books() {
		super();
	}
	
	public Books(int id, String title, String editorial, int note) {
		this.id = id;
		this.title = title;
		this.editorial = editorial;
		this.note = note;
	}
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
	
	@Override
	public String toString() {
		return "Books [id=" + id + ", title=" + title + ", editorial=" + editorial + ", note=" + note + "]";
	}
	
	
	
	
	
	
}
