package controller;

import java.sql.ResultSet;

import dao.EditoraDAO;
import model.Editora;

public class ControllerEditora {

	EditoraDAO dao = new EditoraDAO();

	public ControllerEditora() {
	}

	public void adiciona(Editora e) {
		dao.adiciona(e);
	}

	public ResultSet listagemEditoras() {
		return dao.mostraEditoras();
	}

	public ResultSet seleciona(Integer i) {
		return dao.selecionaEditora(i);
	}
	
	public void altera(String s1, String s2, Integer i) {
		dao.alteraEditora(s1, s2, i);
	}
	
	public void remove(Integer i) throws Exception {
		dao.removeEditora(i);
	}

}
