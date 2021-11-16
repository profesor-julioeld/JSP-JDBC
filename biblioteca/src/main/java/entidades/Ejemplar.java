/**
 * 
 */
package entidades;

import java.time.LocalDate;

/**
 *  
 *  La clase Ejemplar establece la correspondencia con la tabla EJEMPLAR.
 */

/**
 * @author Julio León
 *
 */
public class Ejemplar {
	private int id;
	private String codbarras;
	private LocalDate fechaalta;
	private boolean calculado_prestado;
	private boolean calculado_prestable;
	private int libro_id;
	private int estado_id;
	 
	public Ejemplar() {

	}

	public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}

	public String getCodbarras() {
		return codbarras;
	}

	public void setCodbarras(String codbarras) {
		this.codbarras = codbarras;
	}
	
	public LocalDate getFechaalta() {
		return fechaalta;
	}

	public void setFechaalta(LocalDate fechaalta) {
		this.fechaalta = fechaalta;
	}
	
	public boolean getCalculado_prestado() {
		return calculado_prestado;
	}
	
	public void setCalculado_prestado(boolean calculado_prestado) {
		this.calculado_prestado = calculado_prestado;
	}
	
	public boolean getCalculado_prestable() {
		return calculado_prestable;
	}

	public void setCalculado_prestable(boolean calculado_prestable) {
		this.calculado_prestable = calculado_prestable;
	}
	
	public int getLibro_id() {
		return libro_id;
	}
    
	public void setLibro_id(int libro_id) {
		this.libro_id = libro_id;
	}
	
	public int getEstado_id() {
		return estado_id;
	}
     
	public void setEstado_id(int estado_id) {
		this.estado_id = estado_id;
	}
	
	@Override
	public String toString() {
		return "Ejemplar ["+
				"id=" + id + "," +
				"codbarras=" + codbarras + "," +
				"fechalta=" + fechaalta + "," +
				"calculado_prestado=" + calculado_prestado + "," +
				"calculado_prestable=" + calculado_prestable + "," +
				"libro_id=" + libro_id + "," +
				"estado_id=" + estado_id + 
				"]";
	} 


}
