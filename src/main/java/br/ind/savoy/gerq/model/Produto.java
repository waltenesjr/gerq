package br.ind.savoy.gerq.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
 * This is our model class and it corresponds to Produto table in database
 */
@Entity
@Table(name="PRODUTO")
public class Produto implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "data_vencimento")
	private Date dataVencimento;

	@Column(name = "quantidade")
	private int quantidade;

	@Column(name = "corrosivo")
	private Boolean corrosivo;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Boolean getCorrosivo() {
		return corrosivo;
	}

	public void setCorrosivo(Boolean corrosivo) {
		this.corrosivo = corrosivo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}