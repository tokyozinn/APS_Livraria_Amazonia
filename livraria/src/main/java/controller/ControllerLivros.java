package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.LivroDAO;
import model.Livro;

public class ControllerLivros {
	
	LivroDAO dao = new LivroDAO();
	Livro livro = new Livro();
	
	public ControllerLivros() {

	}
	
	public void cruzamentoEditoraId(Livro livro, String inputNome) {
		String sqlEditoraLivro = "SELECT id_editora FROM publishers WHERE nome = ?";
		PreparedStatement ps;
		try {
			ps = dao.abreConexao().prepareStatement(sqlEditoraLivro);
			ps.setString(1, inputNome);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				livro.setId_editora(rs.getInt(1));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionaLivro(Livro livro) {
		dao.adicionaLivro(livro);
	}
	
	public ResultSet listagemLivros() {
		return dao.mostraLivro();
	}
	
	public void remove(String s) {
		dao.removeLivro(s);
	}
}
