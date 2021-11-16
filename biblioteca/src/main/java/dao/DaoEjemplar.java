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
import entidades.Ejemplar;
import excepciones.BibliotecaException;

public class DaoEjemplar  {

	public DaoEjemplar() {
		// TODO Auto-generated constructor stub
	}
	
	/*public ArrayList<Autor> listado() throws SQLException,Exception{
		
		ArrayList<Autor> listado=new ArrayList<>();
		Conexion conexion=new Conexion();  
		Connection con=null;
		ResultSet rs = null;
		Statement st = null;
		try {
			con=conexion.getConexion();  
			st = con.createStatement();// Creamos un objeto Statement
			 
			String ordenSQL = "SELECT * FROM AUTOR ORDER By NOMBRE"; // sentencia a ejecutar
			rs = st.executeQuery(ordenSQL);	
			while (rs.next()) {
				Autor miAutor = new Autor();
				miAutor.setId(rs.getInt("ID"));
				miAutor.setNombre(rs.getString("NOMBRE"));
				listado.add(miAutor);
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
	/**
	 * 
	 * @param id  Código del Autor a consultar
	 * @return Autor   Datos del autor que consultamos
	 *                 Se devuelve null si no hay 
	 *                 ningún autor en la base de datos con 
	 *                 el identificador que se pasa como argumento.
	 * @throws SQLException Si se produce algún error
	 * @throws Exception  Excepciones no controladas
	 *   
	 */
	
	public Ejemplar findById(int id)throws SQLException {
		Ejemplar a=null;
        Connection con=null;
        PreparedStatement st=null;
        ResultSet rs = null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "SELECT ID,CODBARRAS,FECHAALTA,"
            				+ "CALCULADO_PRESTADO,CALCULADO_PRESTABLE,LIBRO_ID,ESTADO_ID FROM EJEMPLAR"+
            				  " WHERE ID=?";
            st = con.prepareStatement(ordenSQL);
            st.setInt(1, id);
            rs=st.executeQuery();   
            if(rs.next()) {             
            	a=new Ejemplar();
            	a.setId(rs.getInt("ID"));
            	a.setCodbarras(rs.getString("CODBARRAS"));
            	a.setFechaalta(rs.getObject("FECHAALTA",java.time.LocalDate.class));
            	a.setCalculado_prestado(rs.getBoolean("CALCULADO_PRESTADO"));
            	a.setCalculado_prestado(rs.getBoolean("CALCULADO_PRESTADO"));
            	a.setLibro_id(rs.getInt("LIBRO_ID"));
            	a.setEstado_id(rs.getInt("ESTADO_ID"));
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

	/**
	 * 
	 * @param a   Objeto con los datos modificados, localizo por id en 
	 * la BD y modifico el resto de columnas
	 * @throws SQLException, 
	 */
	// Deberíamos crear nuestras propias excepciones
	/*public void modifica(Autor a) throws SQLException,BibliotecaException{
        Connection con=null;
        PreparedStatement pst=null;
        int filasmodificadas;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            con.setAutoCommit(false);
            String ordenSQL = "UPDATE AUTOR SET NOMBRE=?"+
            				  " WHERE ID=?";
            pst = con.prepareStatement(ordenSQL);
            pst.setString(1, a.getNombre());
            pst.setInt(2,a.getId());
            filasmodificadas=pst.executeUpdate();
            if (filasmodificadas!=1) throw new BibliotecaException("No existe el autor");
            con.commit();
        } catch (SQLException se) {
            throw se;
        } catch (BibliotecaException e) {
            throw e;
        }
        finally{
         	if(pst!=null)
                pst.close();
         	if(con!=null)
                con.close();
        }	
	}*/
	
	// El dato libro_id debe aparecer relleno
	public void inserta(Ejemplar a) throws SQLException, Exception {
        Connection con=null;
        PreparedStatement st=null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            con.setAutoCommit(false);
            // El registro 1 de estado siempre será el de estado normal
            // uso un funcion creada con fichero CreaFuncionCreaSerie
            String ordenSQL = "INSERT INTO EJEMPLAR VALUES"
            				+ "(SEQ_EJEMPLAR_ID.NEXTVAL,CREASERIE(SEQ_EJEMPLAR_ID.NEXTVAL),SYSDATE,'N','Y',?,1)";
            String generatedColumns[] = {"ID","CODBARRAS","FECHAALTA"};
            st = con.prepareStatement(ordenSQL,generatedColumns);
            st.setInt(1, a.getLibro_id());
            st.executeUpdate();
            ResultSet rsgk = st.getGeneratedKeys();
            rsgk.next();
            // Cargo resto de valores de a
            
            a.setId(rsgk.getInt(1)); 
            a.setCodbarras(rsgk.getString(2));
            a.setFechaalta(rsgk.getObject(3,java.time.LocalDate.class));
            a.setCalculado_prestado(false);
            a.setCalculado_prestable(true);
            // Libro_id viene relleno como parámetro
            a.setEstado_id(1);
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
