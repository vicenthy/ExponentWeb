package br.com.exponent.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.exponent.entity.Produto;
import br.com.exponent.persistence.ClassDao;
import br.com.exponent.persistence.HibernateUtil;


@ManagedBean(name="beanProduto")
@ViewScoped
public class ManagedProduto {

	
	private Produto produto;
	private List<Produto> produtoList;
	private ClassDao<Produto> dao;
	private Integer tipoConsulta;
	private String campo;
	private String pesProd;
	
	
	public ManagedProduto() {
	dao = new ClassDao<Produto>(Produto.class);
	produto = new Produto();
	produtoList = new ArrayList<Produto>();
	}

	
	
	
	
	
	

	public Integer getTipoConsulta() {
		return tipoConsulta;
	}



	public void setTipoConsulta(Integer tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}








	public String getCampo() {
		return campo;
	}








	public void setCampo(String campo) {
		this.campo = campo;
	}








	public String getPesProd() {
		return pesProd;
	}



	public void setPesProd(String pesProd) {
		this.pesProd = pesProd;
	}




	public List<Produto> getProdutoList() {

		return produtoList;
	}
	
	
	
	

	public void setProdutoList(List<Produto> produtoList) {
		this.produtoList = produtoList;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	@SuppressWarnings("unchecked")
	public String findProd(){
		try {
					
			produtoList  = 	dao.consultaByTipoCriteria(0, null, tipoConsulta, campo, pesProd)
								.addOrder(Order.asc("descricao")).list();
			
		} catch (Exception e) {
			e.printStackTrace();

		}
	
		
		
		return null;
		
	
	}
	
	
	
	
	
	
}
