package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;
import javax.swing.JOptionPane;

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
        	JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
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
        	JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    public void removeLivro(String s) throws Exception {
    	String sql = "DELETE FROM books WHERE isbn = ?";
    	PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
	    	ps.setString(1, s);
	    	
	    	if(ps.executeUpdate() < 1) {
	    		JOptionPane.showMessageDialog(null, "Não foi possível localizar o livro");
	    		throw new Exception();
	    	}
	    	ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
		}

    }
    
    public ResultSet selecionaLivro(String s) {
    	String sql = "SELECT * FROM books WHERE isbn = ?";
    	PreparedStatement ps;
    	ResultSet rs;
		try {
			ps = con.prepareStatement(sql);
	    	ps.setString(1, s);
	    	ps.execute();
	    	rs = ps.getResultSet();
	    	return rs;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			throw new RuntimeException(e);
		}    	
    }
    
    public void alteraLivro(String s1, String s2, Integer s3, String s4) {
    	String sql = "UPDATE books SET title = ?, price = ?, publisher_id = ? WHERE isbn = ?";
    	PreparedStatement ps;
    	try {
			ps = con.prepareStatement(sql);
			ps.setString(1, s1);
			ps.setString(2, s2);
			ps.setInt(3, s3);
			ps.setString(4,	s4);
			ps.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			throw new RuntimeException(e);
		}
    }
    
    public Integer retornaId(String s) {
    	String sql = "SELECT id_editora FROM publishers WHERE nome = ?";
    	PreparedStatement ps;
    	ResultSet rs;
 
		try {
		   	Integer id = null;
			ps = con.prepareStatement(sql);
		  	ps.setString(1, s);
		  	ps.execute();
		  	rs = ps.getResultSet();
		  	while(rs.next()) {
		  		id = rs.getInt(1);
		  	}
		  	
		  	return id;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			throw new RuntimeException();
		}
  
    	
    }
        
}


