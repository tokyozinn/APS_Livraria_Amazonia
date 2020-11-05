package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Editora;

public class EditoraDAO extends DAORoot{

	private Connection con;
	
	public EditoraDAO() {
		this.con = abreConexao();
	}
	
	public void adiciona(Editora edi) {
        try {
            String sql = "insert into publishers (id_editora, nome, url) values (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, edi.getIdEditora());
            ps.setString(2, edi.getNome());
            ps.setString(3, edi.getUrl());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}

