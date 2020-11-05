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
            ps.setDouble(4, livro.getPreço());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void mostraLivro() {
    	
    	ResultSet rs = null;
        try {
            String sql = "SELECT * FROM books";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.execute();
            
            rs = ps.getResultSet();
            ResultSetMetaData meta = rs.getMetaData();
            int n = meta.getColumnCount();
            while(rs.next()) {
            	Object [] rowData = new Object[n];
            	for(int i = 0; i < rowData.length; i++) {
            		rowData[i] = rs.getObject(i+1);
            		System.out.println(rowData[i]);
            	}
            }
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


