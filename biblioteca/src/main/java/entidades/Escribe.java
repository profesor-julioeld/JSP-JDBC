/**
 * 
 */
package entidades;

/**
 *  
 *  La clase Escribe establece la correspondencia con la tabla ESCRIBE.
 */

// >>>Creo que esta clase no es necesaria

/**
 * 
 * @author Julio León
 *
 */
public class Escribe {

	private int libro_id;
	private int autor_id;
	
	public Escribe() {
		 
	}

	public int getLibro_id() {
		return libro_id;
	}

	public void setLibro_id(int libro_id) {
		this.libro_id = libro_id;
	}

	public int getAutor_id() {
		return autor_id;
	}

	public void setAutor_id(int autor_id) {
		this.autor_id = autor_id;
	}

	@Override
	public String toString() {
		return "Escribe ["+
				"Libro_id=" + libro_id + "," +
				"Autor_id=" + autor_id +
				"]";
	}
}
