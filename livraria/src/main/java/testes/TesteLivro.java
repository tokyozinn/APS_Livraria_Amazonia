package testes;

import dao.LivroDAO;
import model.Livro;

public class TesteLivro {

	public static void main(String[] args) {

		LivroDAO dao = new LivroDAO();
//
//		Livro livro = new Livro();
//		livro.setTitulo("livro com dao1");
//		livro.setIsbn("78787");
//		livro.setPreço(44.99);
//		livro.setId_editora(1);
//
//		dao.adicionaLivro(livro);
		dao.mostraLivro();
	}

}
