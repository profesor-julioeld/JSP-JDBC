package test;

import java.sql.SQLException;

import dao.DaoSocio;
import entidades.Socio;

public class TestDaoSocioFindById {

	public static void main(String[] args) {
		DaoSocio daoSocio=new DaoSocio();
		try {
			Socio s=daoSocio.findById(1);
			if(s!=null)
				System.out.println(s.toString());
			else
				System.out.println("*** No existe un autor con ese código ****");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
