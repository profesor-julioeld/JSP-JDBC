/**
 * 
 */
package entidades;

/**
 *  
 *  La clase Libro establece la correspondencia con la tabla LIBRO.
 */

/**
 * 
 * @author Julio León
 *
 */
public class Libro {

	private int id;
	private String isbn;
	private String titulo;
	private String tejuelo;
	
	public Libro() {
		 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getTejuelo() {
		return tejuelo;
	}

	public void setTejuelo(String tejuelo) {
		this.tejuelo = tejuelo;
	}

	@Override
	public String toString() {
		return "Libro ["+
				"id=" + id + "," +
				"isbn=" + isbn + "," +
				"titulo=" + titulo + "," +
				"tejuelo=" + tejuelo +
				"]";
	}
}
