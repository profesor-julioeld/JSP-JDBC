package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import conexiones.Conexion;
import dao.*;
import entidades.*;
import tools.GoogleBooks;
import tools.Tools;
/**
 * Servlet implementation class ControllerAdmin
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}
	// CONTROL DE ERRORES
	protected void procesarError(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws ServletException, IOException {
		String mensajeError = e.getMessage();
		request.setAttribute("error", mensajeError);
		request.getRequestDispatcher("/error.jsp").forward(request, response);

	}

	protected void procesarErrorSQL(HttpServletRequest request, HttpServletResponse response, SQLException e)
			throws ServletException, IOException {
		int codigoError = e.getErrorCode();
		String mensajeError;
		switch (codigoError) {
		// >>>> Tenemos que ver los códigos de error y lo que vamos a hacer
		default:
			mensajeError = e.getMessage();
		}
    	request.setAttribute("error", mensajeError);
		request.getRequestDispatcher("/error.jsp").forward(request,response);
	}    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operacion = request.getParameter("operacion");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		DaoAutor daoautor;
		DaoLibro daolibro;
		DaoEscribe daoescribe;
		DaoEjemplar daoejemplar;
		switch (operacion) {
		// Se trata del proceso de registro de cada libro que llega a la Biblioteca
		// pero realizado sin captura adicional de información
		// Si no encuentra el libro debe proceder de forma manual
		case "registroautomaticolibro":
			// El proceso empieza con la lectura del ISBN del libro
			String isbn=request.getParameter("isbn");
			// Primero miramos si ya está registrado
			try {
				Libro libro;
				daolibro=new DaoLibro();
				libro=daolibro.findByISBN(isbn);
				if (libro==null) {
					// No ha sido dado de alta anteriormente, lo busco en Google
					JSONObject jsLibro=GoogleBooks.buscaISBN(isbn);
					if (jsLibro==null) { 
						request.setAttribute("error", "Libro no localizado en BD Gooogle");
						request.getRequestDispatcher("/registroautomaticolibro.jsp").forward(request, response);
					}
					else {
						// Leo datos JSON para proceder al alta
						libro=new Libro();
						libro.setIsbn(isbn);
						libro.setTitulo(jsLibro.getString("titulo"));
						// Ahora mismo paso del tejuelo para no hacer ésto mas largo
						//libro.Tejuelo();
						daolibro.inserta(libro);
						// Ahora proceso los autores, si están dados de alta no los vuelvo a dar
						Autor autor;
						daoautor=new DaoAutor();
						daoescribe=new DaoEscribe();
						JSONArray jsAutores=jsLibro.getJSONArray("autores");
						for (int i=0;i<jsAutores.length();i++) {
							String nombre=jsAutores.getString(i);
							// Nombre de autor vacio
							// >>> Quizás mejor que no aparezca en json
							if (!nombre.equals("")) {
								autor=daoautor.findByNombre(nombre);
								// No he encontrado el autor asi que lo doy de alta
								if (autor==null) {
									autor=new Autor();
									autor.setNombre(nombre);
									daoautor.inserta(autor);
								}
								// Tengo que decir que ha escrito ese libro
								Escribe escribe=new Escribe();
								escribe.setLibro_id(libro.getId());
								escribe.setAutor_id(autor.getId());
								daoescribe.inserta(escribe);
							}
						} // fin for de autores
					} // fin jsLibro
				} // fin Libro
				// Ahora inserto un ejemplar de ese libro
				Ejemplar ejemplar=new Ejemplar();
				ejemplar.setLibro_id(libro.getId());
				daoejemplar=new DaoEjemplar();
				daoejemplar.inserta(ejemplar);
				// Paso como atributos el libro y el ejemplar
				request.setAttribute("libro", libro);
				request.setAttribute("ejemplar",ejemplar);
				String ruta=getServletConfig().getServletContext().getRealPath("/generados");
				// Genero el pdf en generados
				String file=Tools.creaFicheroPDF(ruta+"\\","Ejemplar",ejemplar.getCodbarras());
				//request.setAttribute("error",file);
				request.getRequestDispatcher("/registroautomaticolibro.jsp").forward(request, response);
			} catch (SQLException e) {procesarErrorSQL(request, response, e);} 
			  catch (Exception e) {	procesarError(request, response, e);}
			break;
		case "insertaautor":
			Autor autor = new Autor();
			String nombreAutor=request.getParameter("nombre");
			autor.setNombre(nombreAutor);
			daoautor = new DaoAutor();
			try {
				daoautor.inserta(autor);
			} 
			catch (SQLException e) {procesarErrorSQL(request, response, e);} 
			catch (Exception e) {	procesarError(request, response, e);}
			request.setAttribute("confirmaroperacion", "Autor creado satisfactoriamente");
			request.getRequestDispatcher("/altaautor.jsp").forward(request, response);
			break;
		case "listarAutores":
			daoautor=new DaoAutor();
			try {
				ArrayList<Autor> listadoautores = daoautor.listado();
				request.setAttribute("listadoautores", listadoautores);
				request.getRequestDispatcher("listadoautores.jsp").forward(request, response);
			} catch (SQLException e) {procesarErrorSQL(request, response, e);} 
			  catch (Exception e) {	procesarError(request, response, e);}
			break;
		case "logout":
			HttpSession sesion = request.getSession();
			sesion.invalidate();
			response.sendRedirect("index.jsp");
			break;
		case "pruebaconexion":
			Conexion conexion=new Conexion();
			Connection con=null;
			try {
				 con=conexion.getConexion();
				 if (con!=null) {
					 System.out.println("He conectado");
					 con.close();
				 }
			}
			catch (SQLException e) {procesarErrorSQL(request, response, e);} 
			catch (Exception e) {	procesarError(request, response, e);}
			request.setAttribute("error", "Me he conectado correctamente");
			request.getRequestDispatcher("error.jsp").forward(request,response);
			break;
		default:
			request.setAttribute("error", "No existe esa opción en controller");
			request.getRequestDispatcher("error.jsp").forward(request,response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
