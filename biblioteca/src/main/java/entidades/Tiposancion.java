/**
 * 
 */
package entidades;

/**
 *  
 *  La clase Tiposancion establece la correspondencia con la tabla TIPOSANCION.
 */

/**
 * 
 * @author Julio León
 *
 */
public class Tiposancion {

	private int id;
	private String descripcion;
	private int diassancion;
	
	public Tiposancion() {
		 
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

	public int getDiassancion() {
		return diassancion;
	}

	public void setDiassancion(int diassancion) {
		this.diassancion = diassancion;
	}
	
	@Override
	public String toString() {
		return "TipoSancion ["+
				"id=" + id + "," +
				"descripcion=" + descripcion  + "," +
				"diassancion=" + diassancion  +
				"]";
	}
}
