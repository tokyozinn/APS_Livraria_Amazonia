package controller;

import dao.AutorDAO;
import model.Autor;

public class ControllerAutor {
	
	AutorDAO dao = new AutorDAO();
	 
	public ControllerAutor() {}
	
	public void adiciona(Autor a) {
		dao.adiciona(a);
	}

}
