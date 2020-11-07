package controller;

import java.sql.ResultSet;

import dao.AutorDAO;
import model.Autor;

public class ControllerAutor {

	AutorDAO dao = new AutorDAO();

	public ControllerAutor() {
	}

	public void adiciona(Autor a) {
		dao.adiciona(a);
	}

	public ResultSet listaAutores() {
		return dao.mostraAutores();
	}

	public ResultSet seleciona(Integer i) {
		return dao.selecionaAutor(i);
	}
	
	public void altera(String s1, String s2, Integer i) {
		dao.alteraAutor(s1, s2, i);
	}

	public void remove(Integer i) throws Exception {
		dao.removeAutor(i);
	}
}
