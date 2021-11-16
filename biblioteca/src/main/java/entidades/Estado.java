/**
 * 
 */
package entidades;

/**
 *  
 *  La clase Estado establece la correspondencia con la tabla ESTADO.
 */

/**
 * 
 * @author Julio León
 *
 */
public class Estado {

	private int id;
	private String descripcion;
	
	public Estado() {
		 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Estado ["+
				"id=" + id + "," +
				"descripcion=" + descripcion  +
				"]";
	}
}
