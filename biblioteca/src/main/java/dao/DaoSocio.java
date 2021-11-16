/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Peticion;
import entidades.Socio;

/**
 * @author Ortiz modificado por Julio León
 *
 */
public class DaoSocio {

	/**
	 * 
	 */
	public DaoSocio() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param id Codigo del socio a buscar
	 * @return El socio con codigo id o null si no lo encuentra
	 * @throws SQLException Errores SQL en el acceso.
	 * @throws Exception
	 */
	public Socio findById(long id) throws SQLException, Exception {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		Socio socio = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			String ordenSQL = "SELECT ID,CODBARRAS,FOTO,NOMBRE,TELEFONO,"+
							 "EMAIL,FECHAALTA,FECHABAJA,CALCULADO_SANCIONADO,"+
							 "CALCULADO_FECHAFINSANCION FROM SOCIO WHERE id=?";
			st = con.prepareStatement(ordenSQL);
			st.setLong(1, id);
			rs = st.executeQuery(); // CUIDADO CON ESCRIBIR rs = st.executeQuery(ordenSQL) ESTO FALLA Y NO DE ERROR
									// DE COMPILACION
			if (rs.next()) {
				socio = new Socio();
				socio.setId(rs.getInt("ID"));
				socio.setCodbarras(rs.getString("CODBARRAS"));
				socio.setFoto(rs.getBlob("FOTO"));
				socio.setNombre(rs.getString("NOMBRE"));
				socio.setTelefono(rs.getString("TELEFONO"));
				socio.setEmail(rs.getString("EMAIL"));
				// Aquí tenemos que hacer un poco de trampa
				socio.setFechaalta(rs.getObject("FECHAALTA",LocalDate.class));
				socio.setCalculado_sancionado(rs.getBoolean("CALCULADO_SANCIONADO"));
				// Igual que antes
				socio.setCalculado_fechafinsancion(rs.getObject("CALCULADO_FECHAFINSANCION",LocalDate.class));
			}
		} catch (SQLException se) {
			throw se;
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();

		}
		return socio;

	}
	
public ArrayList<Socio> listado() throws SQLException,Exception{
		
		ArrayList<Socio> listado=new ArrayList<>();
		Conexion conexion=new Conexion();  
		Connection con=null;
		ResultSet rs = null;
		Statement st = null;
		try {
			con=conexion.getConexion();  
			st = con.createStatement();// Creamos un objeto Statement
			 
			String ordenSQL = "SELECT * FROM SOCIO ORDER By ID"; // sentencia a ejecutar
			rs = st.executeQuery(ordenSQL);	
			while (rs.next()) {
				Socio socio = new Socio();
				socio.setId(rs.getInt("ID"));
				socio.setCodbarras(rs.getString("CODBARRAS"));
				socio.setFoto(rs.getBlob("FOTO"));
				socio.setNombre(rs.getString("NOMBRE"));
				socio.setTelefono(rs.getString("TELEFONO"));
				socio.setEmail(rs.getString("EMAIL"));
				socio.setFechaalta(rs.getObject("FECHAALTA",LocalDate.class));
				socio.setCalculado_sancionado(rs.getBoolean("CALCULADO_SANCIONADO"));
				socio.setCalculado_fechafinsancion(rs.getObject("CALCULADO_FECHAFINSANCION",LocalDate.class));
				listado.add(socio);
			}			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw e;
		} catch (Exception ex) {
			//ex.printStackTrace();
			throw ex;
		}
		finally {      
			// liberamos los recursos en un finally para asegurarnos que se cierra
			// todo lo abierto
			if(rs!=null)
				rs.close();
			if(st!=null)
				st.close();
			if(con!=null)
				con.close();
		}
		return listado; // retornamos el resultado en forma de array
	}

	public void inserta(Socio socio) throws SQLException, Exception {
        Connection con=null;
        PreparedStatement st=null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            con.setAutoCommit(false);
            // El registro 1 de estado siempre será el de estado normal
            // uso un funcion creada con fichero CreaFuncionCreaSerie
            String ordenSQL = "INSERT INTO SOCIO VALUES"
            				+ "(SEQ_SOCIO_ID.NEXTVAL,CREASERIE(SEQ_SOCIO_ID.NEXTVAL),null,?,?,?,SYSDATE,null,'N',null)";
            String generatedColumns[] = {"ID","CODBARRAS","FECHAALTA"};
            st = con.prepareStatement(ordenSQL,generatedColumns);
            st.setString(1, socio.getNombre());
            st.setString(2, socio.getTelefono());
            st.setString(3, socio.getEmail());
            st.executeUpdate();
            ResultSet rsgk = st.getGeneratedKeys();
            rsgk.next();
            // Cargo resto de valores de a
            // CUIDADO: Si ponemos getInt("ID") pone como error Funcion no soportada
            socio.setId(rsgk.getInt(1)); 
			socio.setCodbarras(rsgk.getString(2));
			socio.setFoto(null);
			socio.setFechaalta(rsgk.getObject(3,LocalDate.class));
			socio.setCalculado_sancionado(false);
			socio.setCalculado_fechafinsancion(null);
            con.commit();
            st.close();
            con.close();
        } catch (SQLException se) {
            throw se;
        } catch (Exception e) {
            throw e;
        }
        finally{
         	if(st!=null)
                st.close();
         	if(con!=null)
                con.close();
        }
    }

}
