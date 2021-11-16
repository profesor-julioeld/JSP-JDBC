/**
 * Clase que ofrecerá los métodos para interactuar con la tabla
 * Autor de nuestra base de datos
 */
package dao;

/**
 * @author Ortiz modificado por Julio León
 *
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Peticion;
import excepciones.BibliotecaException;

public class DaoPeticion  {

	public DaoPeticion() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Peticion> listado() throws SQLException,Exception{
		
		ArrayList<Peticion> listado=new ArrayList<>();
		Conexion conexion=new Conexion();  
		Connection con=null;
		ResultSet rs = null;
		Statement st = null;
		try {
			con=conexion.getConexion();  
			st = con.createStatement();// Creamos un objeto Statement
			 
			String ordenSQL = "SELECT * FROM PETICION ORDER By ID"; // sentencia a ejecutar
			rs = st.executeQuery(ordenSQL);	
			while (rs.next()) {
				Peticion a = new Peticion();
				a.setId(rs.getInt("ID"));
            	a.setFechainicio(rs.getObject("FECHAINICIO",java.time.LocalDate.class));
            	a.setFechafin(rs.getObject("FECHAFIN",java.time.LocalDate.class));
            	a.setSocio_id(rs.getInt("SOCIO_ID"));
            	a.setLibro_id(rs.getInt("LIBRO_ID"));
            	a.setCalculado_activo(rs.getBoolean("CALCULADO_ACTIVO"));
				listado.add(a);
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

	
	public Peticion findById(int id)throws SQLException {
		Peticion a=null;
        Connection con=null;
        PreparedStatement st=null;
        ResultSet rs = null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "SELECT ID,FECHAINICIO,FECHAFIN,SOCIO_ID,LIBRO_ID,"
            				+ "CALCULADO_ACTIVO FROM PETICION"+
            				  " WHERE ID=?";
            st = con.prepareStatement(ordenSQL);
            st.setInt(1, id);
            rs=st.executeQuery();   
            if(rs.next()) {             
            	a=new Peticion();
            	a.setId(rs.getInt("ID"));
            	a.setFechainicio(rs.getObject("FECHAINICIO",java.time.LocalDate.class));
            	a.setFechafin(rs.getObject("FECHAFIN",java.time.LocalDate.class));
            	a.setSocio_id(rs.getInt("SOCIO_ID"));
            	a.setLibro_id(rs.getInt("LIBRO_ID"));
            	a.setCalculado_activo(rs.getBoolean("CALCULADO_ACTIVO"));
            }
        } catch (SQLException se) {
            throw se;
        } finally{
         	if(rs!=null)
                rs.close();
         	if(st!=null)
                st.close();
         	if(con!=null)
                con.close();
        }
        return a;
	}	


	// El dato socio_id y libro_id deben aparecer rellenos
	public void inserta(Peticion a) throws SQLException, Exception {
        Connection con=null;
        PreparedStatement st=null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            con.setAutoCommit(false);
            // El registro 1 de estado siempre será el de estado normal
            // uso un funcion creada con fichero CreaFuncionCreaSerie
            String ordenSQL = "INSERT INTO PETICION VALUES"
            				+ "(SEQ_PETICION_ID.NEXTVAL,SYSDATE,SYSDATE+3,?,?,'Y')";
            String generatedColumns[] = {"ID","FECHAINICIO","FECHAFIN"};
            st = con.prepareStatement(ordenSQL,generatedColumns);
            st.setInt(1, a.getSocio_id());
            st.setInt(2, a.getLibro_id());
            st.executeUpdate();
            ResultSet rsgk = st.getGeneratedKeys();
            rsgk.next();
            // Cargo resto de valores de a
            a.setId(rsgk.getInt(1)); 
            a.setFechainicio(rsgk.getObject(2,java.time.LocalDate.class));
            a.setFechafin(rsgk.getObject(3,java.time.LocalDate.class));
            a.setCalculado_activo(true);
            // Libro_id viene relleno como parámetro
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
