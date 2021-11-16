/**
 * 
 */
package entidades;

import java.sql.Blob;
import java.time.LocalDate;

/**
 *  
 *  La clase Socio establece la correspondencia con la tabla SOCIO.
 */

/**
 * @author Julio León
 *
 */
public class Socio {
    private int id;
    private String codbarras;
    private Blob foto;
    private String nombre;
    private String telefono;
    private String email;
    // Antiguamente se ponía java.sql.Date
    private LocalDate fechaalta;
    private LocalDate fechabaja;
    private boolean calculado_sancionado;
    private LocalDate calculado_fechafinsancion;

	public Socio() {
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
	
	public Blob getFoto() {
		return foto;
	}

	public void setFoto(Blob foto) {
		this.foto = foto;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getFechaalta() {
		return fechaalta;
	}

	public void setFechaalta(LocalDate fechaalta) {
		this.fechaalta = fechaalta;
	}

	public LocalDate getFechabaja() {
		return fechabaja;
	}

	public void setFechaAlta(LocalDate fechabaja) {
		this.fechabaja = fechabaja;
	}
	
	public boolean getCalculado_sancionado() {
		return calculado_sancionado;
	}
	
	public void setCalculado_sancionado(boolean calculado_sancionado) {
		this.calculado_sancionado = calculado_sancionado;
	}
	
	public LocalDate getCalculado_fechafinsancion() {
		return calculado_fechafinsancion;
	}
	
	public void setCalculado_fechafinsancion(LocalDate calculado_fechafinsancion) {
		this.calculado_fechafinsancion = calculado_fechafinsancion;
	}
	
	@Override
	public String toString() {
		return "Socio ["+
				"id=" + id + "," +
				"codbarras=" + codbarras + "," +
				"foto=" + foto + "," +
				"nombre=" + nombre + "," +
				"telefono=" + telefono + "," +
				"email=" + email + "," +
				"fechalta=" + fechaalta + "," +
				"fechabaja=" + fechabaja + "," +
				"sancionado=" + calculado_sancionado + "," +
				"fechafinsancion=" + calculado_fechafinsancion +
				"]";
	} 

}
