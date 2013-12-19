package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.Session;

import persistence.ClassDao;
import persistence.HibernateUtil;
import entity.Produto;

@ManagedBean(name="beanProduto")
@ViewScoped
public class ManagedProduto {

	
	private Produto produto;
	private List<Produto> produtoList;
	private Session session;
	private ClassDao<Produto> dao;
	private Integer tipoConsulta;
	private String campo;
	private String pesProd;
	
	
	public ManagedProduto() {

session = HibernateUtil.getSessionFactory().openSession();
dao = new ClassDao<Produto>(Produto.class, session);
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
					
			produtoList  = 	dao.consultaByTipoCriteria(0, null, tipoConsulta, campo, pesProd).list();
			
		} catch (Exception e) {
			e.printStackTrace();

		}
	
		
		
		return null;
		
	
	}
	
	
	
	
	
	
}
