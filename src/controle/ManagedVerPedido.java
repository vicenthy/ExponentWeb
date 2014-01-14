package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.criteria.Join;

import org.hibernate.criterion.Restrictions;

import persistence.ClassDao;
import entity.ItenPedido;
import entity.Pedido;



@ManagedBean(name="beanVerpedido")
@ViewScoped
public class ManagedVerPedido {

	
	private List<Pedido> pedidos;
	private ClassDao<Pedido> daoPedido ;
	private Pedido pedido;
	private List<ItenPedido> itenPedidos;
	private ClassDao<ItenPedido> daoItenPedido ;
	

	
	
	public ManagedVerPedido() {
			daoPedido = new ClassDao<Pedido>(Pedido.class);
			daoItenPedido = new ClassDao<ItenPedido>(ItenPedido.class);
		// TODO Auto-generated constructor stub
	}


	
	
	
	//Negócios 
	
	
	public String fechar(){
		pedido.setStatus("Fechado");
		System.out.println(pedido.getStatus());
		
		return null;
	}
	
	

	public String desfazer(){
		pedido.setStatus("Em aberto");
		System.out.println(pedido.getStatus());
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String verDetalhePedido(){
		
		itenPedidos = daoItenPedido.consultaByCriteria().createAlias("pedido", "p")
						.add(Restrictions.eq("p.objref", pedido.getObjref())).list();
			return null;
	}
	
	
	
// Set e Get...
	
	
	public List<Pedido> getPedidos() {
	pedidos = daoPedido.findAll();
		return pedidos;
	}



	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}



	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}





	public List<ItenPedido> getItenPedidos() {
		return itenPedidos;
	}



	public void setItenPedidos(List<ItenPedido> itenPedidos) {
		this.itenPedidos = itenPedidos;
	}
	
	
	
	
	
	
	
}
