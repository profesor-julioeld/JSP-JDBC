/**
 * 
 */
package entidades;

/**
 *  
 *  La clase Autor establece la correspondencia con la tabla AUTOR.
 */

/**
 * 
 * @author Julio León
 *
 */
public class Autor {

	private int id;
	private String nombre;
	
	public Autor() {
		 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Autor ["+
				"id=" + id + "," +
				"nombre=" + nombre  +
				"]";
	}
}
