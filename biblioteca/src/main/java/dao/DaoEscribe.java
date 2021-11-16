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
import entidades.Escribe;
import excepciones.BibliotecaException;

public class DaoEscribe  {

	public DaoEscribe() {
		// TODO Auto-generated constructor stub
	}
	
	/* // Devuelve un array de id de Autores si pasamos un id de libro
	public int[] findByLibro(int id)throws SQLException {
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
	}	*/

	// Inserta los valores dados en a
	public void inserta(Escribe a) throws SQLException, Exception {
        Connection con=null;
        PreparedStatement st=null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            con.setAutoCommit(false);
            // Las secuencias siguen el formato seq_autor_id
            String ordenSQL = "INSERT INTO ESCRIBE VALUES(?,?)";
            /* String generatedColumns[] = {"ID"};
            st = con.prepareStatement(ordenSQL,generatedColumns);*/
            st = con.prepareStatement(ordenSQL);
            st.setInt(1, a.getLibro_id());
            st.setInt(2, a.getAutor_id());
            st.executeUpdate();
            /*ResultSet rsgk = st.getGeneratedKeys();
            rsgk.next();
            a.setId(rsgk.getInt(1)); */
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
