package br.ind.savoy.gerq.model;

import javax.persistence.*;
import java.io.Serializable;

/*
 * This is our model class and it corresponds to Categoria table in database
 */
@Entity
@Table(name="CATEGORIA")
public class Categoria implements Serializable {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	@Column(name="descricao")
	String descricao;

	public Categoria() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}