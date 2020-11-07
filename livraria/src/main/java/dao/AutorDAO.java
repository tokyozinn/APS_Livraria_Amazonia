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
    
}
