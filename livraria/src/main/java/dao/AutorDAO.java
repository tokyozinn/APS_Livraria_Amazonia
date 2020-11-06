package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            throw new RuntimeException(e);
        }
    }
    
}
