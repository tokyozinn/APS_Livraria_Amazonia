package model;

public class Livro {
	
	private String titulo;
	private String isbn;
	private Integer idEditora;
	private Double preço;
	
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
	public Double getPreço() {
		return preço;
	}
	public void setPreço(Double preço) {
		this.preço = preço;
	}

}
