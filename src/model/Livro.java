package model;

public class Livro {
	
	private String titulo;
	private String isbn;
	private Integer idEditora;
	private Double preco;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Integer getId_editora() {
		return idEditora;
	}
	public void setId_editora(Integer id_editora) {
		this.idEditora = id_editora;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
