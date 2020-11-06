package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.ResultSetMetaData;

import model.Livro;

public class LivroDAO extends DAORoot {
	
	private Connection con;
	
	public LivroDAO() {
		this.con = abreConexao();
	};
		
    public void adicionaLivro(Livro livro) {
        try {
            String sql = "insert into books (title, isbn, publisher_id, price) values (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getIsbn());
            ps.setInt(3, livro.getId_editora());
            ps.setDouble(4, livro.getPreco());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public ResultSet mostraLivro() {
    	
    	ResultSet rs = null;
        try {
            String sql = "SELECT * FROM books";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            
            rs = ps.getResultSet();
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void removeLivro(String s) {
    	String sql = "DELETE FROM books WHERE isbn = ?";
    	PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
	    	ps.setString(1, s);
	    	ps.execute();
	    	
	    	ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }
        
}


