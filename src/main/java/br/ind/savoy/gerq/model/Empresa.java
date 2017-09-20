package br.ind.savoy.gerq.model;

import javax.persistence.*;
import java.io.Serializable;

/*
 * This is our model class and it corresponds to Categoria table in database
 */
@Entity
@Table(name="EMPRESA")
public class Empresa implements Serializable {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;

	@Column(name="nome")
	String nome;

	@Column(name="area")
	String area;

	@Column(name="endereco")
	String endereco;

	@Column(name="telefone")
	String telefone;

	@Column(name="telefone_aux")
	String telefoneAux;

	@Column(name="email")
	String email;

	@Column(name="contato_emergencia")
	String contatoEmergencia;

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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefoneAux() {
		return telefoneAux;
	}

	public void setTelefoneAux(String telefoneAux) {
		this.telefoneAux = telefoneAux;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContatoEmergencia() {
		return contatoEmergencia;
	}

	public void setContatoEmergencia(String contatoEmergencia) {
		this.contatoEmergencia = contatoEmergencia;
	}
}