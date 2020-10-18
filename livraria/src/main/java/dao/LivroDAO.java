package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Livro;

public class LivroDAO {
	
	private Connection con;
	
	public LivroDAO() {
		
		try {
			this.con = DriverManager.getConnection("jdbc:mysql://localhost/livraria?serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
    public void adicionaLivro(Livro livro) {
        try {
            String sql = "insert into books (title, isbn, publisher_id, price) values (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getIsbn());
            ps.setInt(3, livro.getId_editora());
            ps.setDouble(4, livro.getPreço());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void comando(String sql) {
        try {
        	PreparedStatement ps = con.prepareStatement(sql);
        	ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


