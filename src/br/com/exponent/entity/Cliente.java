package br.com.exponent.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name="cliente")
public class Cliente {

	@Id
	@SequenceGenerator(sequenceName="seq_objref_cliente", initialValue=1, allocationSize=1, name="objref_cliente")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="objref_cliente")
	private Integer objref;
	@Column
	private String nome;
	@Column
	private String nomeLoja;
	
	@Column(nullable=false)
	private String cpfCnpj;
	
	
	
	public Cliente() {

	}

	
	

	
	



	public Cliente(Integer objref, String nome, String nomeLoja) {
		super();
		this.objref = objref;
		this.nome = nome;
		this.nomeLoja = nomeLoja;
	}










	public String getCpfCnpj() {
		return cpfCnpj;
	}



	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}




	public Integer getObjref() {
		return objref;
	}



	public void setObjref(Integer objref) {
		this.objref = objref;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getNomeLoja() {
		return nomeLoja;
	}



	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}
	
	
	
	
	
	
}
