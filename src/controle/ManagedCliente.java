package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.hibernate.Session;

import persistence.ClassDao;
import persistence.HibernateUtil;
import entity.Cliente;

@ManagedBean(name="beancliente")
public class ManagedCliente {

	
	
	private Cliente cliente;
	private List<Cliente> clinteList;
	private Session session = null;
	private ClassDao<Cliente> dao;
	
	
	
	public ManagedCliente() {
		openSession();
		dao = new ClassDao<Cliente>(Cliente.class, session);			
		cliente = new Cliente();
		clinteList = new ArrayList<Cliente>();

	}

	
	
	


	
	
	
	
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public List<Cliente> getClinteList() {
		clinteList = dao.findAll();
		return clinteList;
	}


	public void setClinteList(List<Cliente> clinteList) {
		this.clinteList = clinteList;
	}


public String selecionado(){
return null;
}
	
	
	
	private void openSession() {
try {
session = HibernateUtil.getSessionFactory().getCurrentSession();
} catch (Exception e) {
	e.printStackTrace();
	
}


		
	}
	
	
	
	
}
