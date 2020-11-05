package testes;

import dao.EditoraDAO;
import model.Editora;

public class TesteEditora {

	public static void main(String[] args) {
	
		EditoraDAO dao = new EditoraDAO();
		Editora e = new Editora();
		
		e.setIdEditora(33);
		e.setNome("Editora do Lucas");
		e.setUrl("www.unip.com.br");
		
		dao.adiciona(e);		
		
	}
	

}
