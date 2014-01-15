package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;

import persistence.ClassDao;
import persistence.HibernateUtil;
import entity.Cliente;

@ManagedBean(name="beancliente")
@ViewScoped
public class ManagedCliente {

	
	
	private Cliente cliente;
	private List<Cliente> clienteList;
	//private Session session = null;
	private ClassDao<Cliente> dao;
	private int tipoConsulta;
	private String campo;
	private String pesCli;
	
	
	
	public ManagedCliente() {
		dao = new ClassDao<Cliente>(Cliente.class);			
		cliente = new Cliente();

	}

	
	
	


	
	
	
	
	public int getTipoConsulta() {
		return tipoConsulta;
	}



	public void setTipoConsulta(int tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}


	public String getCampo() {
		return campo;
	}



	public void setCampo(String campo) {
		this.campo = campo;
	}


	public String getPesCli() {
		return pesCli;
	}


	public void setPesCli(String pesCli) {
		this.pesCli = pesCli;
	}



	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public List<Cliente> getClienteList() {
			return clienteList;
		
	}


	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}


	public String limpar(){
		cliente = new Cliente();
		return null;
	}
	
public String selecionado(){
	System.out.println("<<<<<<<<<<<<<<<<<<<"+cliente.getNome()+">>>>>>>>>>>>>.");

	return null;
}
	



public String salvar(){
	
	FacesContext fc = FacesContext.getCurrentInstance();
	
	if(cliente.getObjref()!=0){
		try{
			dao.update(cliente);
			fc.addMessage("cadCli", new FacesMessage("Cliente Salvo com sucesso!!!"));
			cliente = new Cliente();
			findCli();
		}catch(Exception e){
			e.printStackTrace();
			fc.addMessage("cadCli", new FacesMessage("Error: "+e.getMessage()));
		}
		
		return null;
		
	}
	
	try{
		dao.create(cliente);
		fc.addMessage("cadCli", new FacesMessage("Cliente Salvo com sucesso!!!"));
		findCli();
		cliente = new Cliente();
	}catch(Exception e){
		e.printStackTrace();
		fc.addMessage("cadCli", new FacesMessage("Error: "+e.getMessage()));

	}


	return null;
}


public String delete()
{
	FacesContext fc = FacesContext.getCurrentInstance();
	try{
		dao.delete(cliente);
		fc.addMessage("cadCli", new FacesMessage("Cliente Deletado com sucesso!!!"));
	}catch(Exception e){
		e.printStackTrace();
		fc.addMessage("cadCli", new FacesMessage("Error: "+e.getMessage()));
	}
	findCli();
cliente = new Cliente();
return null;
}	



@SuppressWarnings("unchecked")
public String findCli(){
	try {
		if(campo =="objref")
		{ 
			Integer pesCli1 =   Integer.parseInt(pesCli); 
			clienteList  = (ArrayList<Cliente>)	dao.consultaByTipoCriteria(0, null, tipoConsulta, campo, pesCli1).list();
			return null;
		}		
		clienteList  = 	(ArrayList<Cliente>) dao.consultaByTipoCriteria(0, null, tipoConsulta, campo, pesCli).list();
	} catch (Exception e) {
		e.printStackTrace();

	}
	return null;
	

}





	/*
	private void openSession() {
try {
session = HibernateUtil.getSessionFactory().getCurrentSession();
} catch (Exception e) {
	e.printStackTrace();
	
}


		
	}*/
	


	
	
	
}
