package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.Autor;

public class AutorDAO extends DAORoot{

	private Connection con;
	
	public AutorDAO() {
		this.con = abreConexao();
	}
	
	public void adiciona(Autor a) {
        try {
            String sql = "insert into authors (nome, nomeCompleto) values (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, a.getNome());
            ps.setString(2, a.getNomeCompleto());

            ps.execute();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException(e);
        }
        
    }
	
    public ResultSet mostraAutores() {
    	
    	ResultSet rs = null;
        try {
            String sql = "SELECT * FROM authors";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.execute();
            
            rs = ps.getResultSet();
            return rs;
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    public ResultSet selecionaAutor(Integer i) {
    	String sql = "SELECT * FROM authors WHERE id_autor = ?";
    	PreparedStatement ps;
    	ResultSet rs;
		try {
			ps = con.prepareStatement(sql);
	    	ps.setInt(1, i);
	    	ps.execute();
	    	rs = ps.getResultSet();
	    	return rs;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			throw new RuntimeException(e);
		}    	
    }
    
    public void alteraAutor(String s1, String s2, Integer s3) {
    	String sql = "UPDATE authors SET nome = ?, nomeCompleto = ? WHERE id_autor = ?";
    	PreparedStatement ps;
    	try {
			ps = con.prepareStatement(sql);
			ps.setString(1, s1);
			ps.setString(2, s2);
			ps.setInt(3, s3);
			ps.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			throw new RuntimeException(e);
		}
    }
    
    public void removeAutor(Integer i) throws Exception {
    	String sql = "DELETE FROM authors WHERE id_autor = ?";
    	PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
	    	ps.setInt(1, i);
	    	
	    	if(ps.executeUpdate() < 1) {
	    		JOptionPane.showMessageDialog(null, "Não foi possível localizar o autor");
	    		throw new Exception();
	    	}
	    	ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
		}

    }
    
}
