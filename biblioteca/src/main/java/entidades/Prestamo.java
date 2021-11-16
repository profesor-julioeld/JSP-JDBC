/**
 * 
 */
package entidades;

import java.time.LocalDate;

/**
 *  
 *  La clase Prestamo establece la correspondencia con la tabla PRESTAMO.
 */

/**
 * @author Julio León
 *
 */
public class Prestamo {
    private int id;
    private LocalDate fechainicio;
    private LocalDate fechalimite;
    private LocalDate fechadevolucion;
    private int ejemplar_id;
    private int socio_id;
    private int tiposancion_id;
    private boolean calculado_activo;

	public Prestamo() {
    }

    public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}

    public LocalDate getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(LocalDate fechainicio) {
		this.fechainicio = fechainicio;
	}
	
	public LocalDate getFechalimite() {
		return fechalimite;
	}

	public void setFechalimite(LocalDate fechalimite) {
		this.fechalimite = fechalimite;
	}
	
	public LocalDate getFechadevolucion() {
		return fechadevolucion;
	}

	public void setFechadevolucion(LocalDate fechadevolucion) {
		this.fechadevolucion = fechadevolucion;
	}
    
	public int getEjemplar_id() {
		return ejemplar_id;
	}
     
	public void setEjemplar_id(int ejemplar_id) {
		this.ejemplar_id = ejemplar_id;
	}
    
	public int getSocio_id() {
		return socio_id;
	}
     
	public void setSocio_id(int socio_id) {
		this.socio_id = socio_id;
	}
    
	public int getTiposancion_id() {
		return tiposancion_id;
	}
     
	public void setTiposancion_id(int tiposancion_id) {
		this.tiposancion_id = tiposancion_id;
	}
	
	public boolean getCalculado_activo() {
	  return calculado_activo;	
	}
	
	public void setCalculado_activo(boolean calculado_activo) {
		this.calculado_activo=calculado_activo;
	}
	
	@Override
	public String toString() {
		return "Prestamo ["+
				"id=" + id + "," +
				"fechainicio=" + fechainicio + "," +
				"fechalimite=" + fechalimite + "," +
				"fechadevolucion=" + fechadevolucion + "," +
				"ejemplar_id=" + ejemplar_id + "," +
				"socio_id=" + socio_id + "," +
				"tiposancion_id=" + tiposancion_id + "," +
				"activo=" + calculado_activo +
				"]";
	} 

}
