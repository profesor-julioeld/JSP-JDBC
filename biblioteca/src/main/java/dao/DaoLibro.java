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

import conexiones.Conexion;
import entidades.Libro;


public class DaoLibro  {

	public DaoLibro() {
		// TODO Auto-generated constructor stub
	}
	
	public Libro findByISBN(String isbn)throws SQLException {
		Libro a=null;
        Connection con=null;
        PreparedStatement st=null;
        ResultSet rs = null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "SELECT ID,ISBN,TITULO,TEJUELO FROM LIBRO"
            				+ " WHERE ISBN=?";
            st = con.prepareStatement(ordenSQL);
            st.setString(1, isbn);
            rs=st.executeQuery();   
            if(rs.next()) {             
            	a=new Libro();
            	a.setId(rs.getInt("ID"));
            	a.setIsbn(rs.getString("ISBN"));
            	a.setTitulo(rs.getString("TITULO"));
            	a.setTejuelo(rs.getString("TEJUELO"));
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
	
	public Libro findById(int id)throws SQLException {
		Libro a=null;
        Connection con=null;
        PreparedStatement st=null;
        ResultSet rs = null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "SELECT ID,ISBN,TITULO,TEJUELO FROM LIBRO"
            				+ " WHERE ID=?";
            st = con.prepareStatement(ordenSQL);
            st.setInt(1, id);
            rs=st.executeQuery();   
            if(rs.next()) {             
            	a=new Libro();
            	a.setId(rs.getInt("ID"));
            	a.setIsbn(rs.getString("ISBN"));
            	a.setTitulo(rs.getString("TITULO"));
            	a.setTejuelo(rs.getString("TEJUELO"));
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
	
	// Deben cargar en Libro, isbn, titulo y tejuelo
	public void inserta(Libro a) throws SQLException, Exception {
        Connection con=null;
        PreparedStatement st=null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            con.setAutoCommit(false);
            // El registro 1 de estado siempre será el de estado normal
            // uso un funcion creada con fichero CreaFuncioCreaSerie
            String ordenSQL = "INSERT INTO LIBRO VALUES"
            				+ "(SEQ_LIBRO_ID.NEXTVAL,?,?,?)";
            String generatedColumns[] = {"ID"};
            st = con.prepareStatement(ordenSQL,generatedColumns);
            st.setString(1, a.getIsbn());
            st.setString(2, a.getTitulo());
            st.setString(3, a.getTejuelo());
            st.executeUpdate();
            ResultSet rsgk = st.getGeneratedKeys();
            rsgk.next();
            // Cargo resto de valores de a
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
}
