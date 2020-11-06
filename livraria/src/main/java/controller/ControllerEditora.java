package controller;

import java.sql.ResultSet;

import dao.EditoraDAO;
import model.Editora;

public class ControllerEditora {
	
	EditoraDAO dao = new EditoraDAO();
	public ControllerEditora() {}
	
	public void adiciona(Editora e) {
		dao.adiciona(e);
	}
	
	public ResultSet listagemEditoras() {
		return dao.mostraEditoras();
	}

}
