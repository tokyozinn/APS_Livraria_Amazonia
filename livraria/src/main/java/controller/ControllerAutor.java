package controller;

import java.sql.ResultSet;

import dao.AutorDAO;
import model.Autor;

public class ControllerAutor {
	
	AutorDAO dao = new AutorDAO();
	 
	public ControllerAutor() {}
	
	public void adiciona(Autor a) {
		dao.adiciona(a);
	}
	
	public ResultSet listaAutores() {
		return dao.mostraAutores();
	}

}
