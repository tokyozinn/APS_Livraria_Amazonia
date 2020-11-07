package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.Editora;

public class EditoraDAO extends DAORoot{

	private Connection con;
	
	public EditoraDAO() {
		this.con = abreConexao();
	}
	
	public void adiciona(Editora edi) {
        try {
            String sql = "insert into publishers (nome, url) values (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, edi.getNome());
            ps.setString(2, edi.getUrl());

            ps.execute();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException(e);
        }
        
    }
	
    public ResultSet mostraEditoras() {
    	
    	ResultSet rs = null;
        try {
            String sql = "SELECT * FROM publishers";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.execute();
            
            rs = ps.getResultSet();
            return rs;
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    public ResultSet selecionaEditora(Integer i) {
    	String sql = "SELECT * FROM publishers WHERE id_editora = ?";
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
    
    public void alteraEditora(String s1, String s2, Integer i) {
    	String sql = "UPDATE publishers SET nome = ?, url = ? WHERE id_editora = ?";
    	PreparedStatement ps;
    	try {
			ps = con.prepareStatement(sql);
			ps.setString(1, s1);
			ps.setString(2, s2);
			ps.setInt(3, i);
			ps.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			throw new RuntimeException(e);
		}
    }
    
    public void removeEditora(Integer i) throws Exception {
    	String sql = "DELETE FROM publishers WHERE id_editora = ?";
    	PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
	    	ps.setInt(1, i);
	    	
	    	if(ps.executeUpdate() < 1) {
	    		JOptionPane.showMessageDialog(null, "Não foi possível localizar a editora");
	    		throw new Exception();
	    	}
	    	ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: Não é possível remover uma editora que tenha livros já publicados");
		}

    }
}

