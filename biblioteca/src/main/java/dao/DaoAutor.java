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
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Autor;
import excepciones.BibliotecaException;

public class DaoAutor  {

	public DaoAutor() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return Devuelve un ArrayList de objetos autor con los autores 
	 *         hay actualmente en la tabla AUTOR de nuestra base de datos.
	 * @throws SQLException: Cualquier error en el acceso o en la ejecución
	 */
	public ArrayList<Autor> listado() throws SQLException,Exception{
		
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
	
	public Autor findById(int id)throws SQLException {
		Autor a=null;
        Connection con=null;
        PreparedStatement st=null;
        ResultSet rs = null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "SELECT ID,NOMBRE FROM AUTOR"+
            				  " WHERE ID=?";
            st = con.prepareStatement(ordenSQL);
            st.setInt(1, id);
            rs=st.executeQuery();   
            if(rs.next()) {             
            	a=new Autor();
            	a.setId(rs.getInt("ID"));
            	a.setNombre(rs.getString("NOMBRE"));
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

	public Autor findByNombre(String nombre)throws SQLException {
		Autor a=null;
        Connection con=null;
        PreparedStatement st=null;
        ResultSet rs = null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "SELECT ID,NOMBRE FROM AUTOR"+
            				  " WHERE NOMBRE=?";
            st = con.prepareStatement(ordenSQL);
            st.setString(1, nombre);
            rs=st.executeQuery();   
            if(rs.next()) {             
            	a=new Autor();
            	a.setId(rs.getInt("ID"));
            	a.setNombre(rs.getString("NOMBRE"));
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
	public void modifica(Autor a) throws SQLException,BibliotecaException{
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
	}
	/**
	 * 
	 * @param a   Objeto con los datos para insertar, carga en id de autor
	 * el valor obtenido por la secuencia y con el que se inserta en la BD
	 * @throws SQLException
	 */
	public void inserta(Autor a) throws SQLException, Exception {
        Connection con=null;
        PreparedStatement st=null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            con.setAutoCommit(false);
            // Las secuencias siguen el formato seq_autor_id
            String ordenSQL = "INSERT INTO AUTOR VALUES(SEQ_AUTOR_ID.NEXTVAL,?)";
            String generatedColumns[] = {"ID"};
            st = con.prepareStatement(ordenSQL,generatedColumns);
            st.setString(1, a.getNombre());
            st.executeUpdate();
            ResultSet rsgk = st.getGeneratedKeys();
            rsgk.next();
            a.setId(rsgk.getInt(1)); 
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
	// Pruebas de rendimiento entre Statement y PreparedStatement
	public void rendimientoPS(int pasos) throws SQLException {
		Autor a=null;
        Connection con=null;
        PreparedStatement st=null;
        ResultSet rs = null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            long inicio=System.currentTimeMillis();
            String ordenSQL = "SELECT ID,NOMBRE FROM AUTOR"+
  				  " WHERE ID=?";
            st = con.prepareStatement(ordenSQL);
            for (int i=1;i<=pasos;i++) {
              st.setInt(1, 1);
              rs=st.executeQuery();  
              if(rs.next()) {             
              	a=new Autor();
              	a.setId(rs.getInt("ID"));
              	a.setNombre(rs.getString("NOMBRE"));
              }
              rs.close();
            }
            long fin=System.currentTimeMillis();
            System.out.println(" Ha tardado "+((fin-inicio)/1000.0)+ "segundos");
            st.close();
            con.close();
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
	}	
	
	public void rendimientoS(int pasos) throws SQLException {
		Autor a = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			long inicio = System.currentTimeMillis();
			for (int i = 1; i <= pasos; i++) {
				String ordenSQL = "SELECT ID,NOMBRE FROM AUTOR WHERE ID=1 and "+
			                      i+"="+i;
				st =con.createStatement();
				rs = st.executeQuery(ordenSQL);
				if (rs.next()) {
					a = new Autor();
					a.setId(rs.getInt("ID"));
					a.setNombre(rs.getString("NOMBRE"));
				}
				rs.close();
				st.close();
			}
			long fin = System.currentTimeMillis();
			System.out.println(" Ha tardado " + ((fin - inicio) / 1000.0) + "segundos");
			con.close();
		} catch (SQLException se) {
			throw se;
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		}
	}
}
