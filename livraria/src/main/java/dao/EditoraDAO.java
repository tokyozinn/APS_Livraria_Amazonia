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
    
}

