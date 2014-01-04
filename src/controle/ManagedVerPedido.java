package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import persistence.ClassDao;
import entity.ItenPedido;
import entity.Pedido;



@ManagedBean(name="beanVerpedido")
@ViewScoped
public class ManagedVerPedido {

	
	private List<Pedido> pedidos;
	private ClassDao<Pedido> daoPedido ;

	
	
	public ManagedVerPedido() {
	
		pedidos = new ArrayList<Pedido>();
		daoPedido = new ClassDao<Pedido>(Pedido.class);
		// TODO Auto-generated constructor stub
	}


	
	
	

	public List<Pedido> getItenpedidos() {
	pedidos = daoPedido.findAll();
		return pedidos;
	}



	public void setItenpedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
	
	
	
}
