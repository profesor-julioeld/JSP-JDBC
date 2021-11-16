/**
 * 
 */
package entidades;

import java.time.LocalDate;

/**
 *  
 *  La clase Peticion establece la correspondencia con la tabla PETICION.
 */

/**
 * @author Julio León
 *
 */
public class Peticion {
    private int id;
    private LocalDate fechainicio;
    private LocalDate fechafin;
    private int socio_id;
    private int libro_id;
    private boolean calculado_activo;

	public Peticion() {
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
	
	public LocalDate getFechafin() {
		return fechafin;
	}

	public void setFechafin(LocalDate fechafin) {
		this.fechafin = fechafin;
	}
    
	public int getSocio_id() {
		return socio_id;
	}
     
	public void setSocio_id(int socio_id) {
		this.socio_id = socio_id;
	}
    
	public int getLibro_id() {
		return libro_id;
	}
     
	public void setLibro_id(int libro_id) {
		this.libro_id = libro_id;
	}
	
	public boolean getCalculado_activo() {
	  return calculado_activo;	
	}
	
	public void setCalculado_activo(boolean calculado_activo) {
		this.calculado_activo=calculado_activo;
	}
	
	@Override
	public String toString() {
		return "Peticion ["+
				"id=" + id + "," +
				"fechainicio=" + fechainicio + "," +
				"fechafin=" + fechafin + "," +		 
				"socio_id=" + socio_id + "," +
				"libro_id=" + libro_id + "," +
				"activo=" + calculado_activo +
				"]";
	} 

}
