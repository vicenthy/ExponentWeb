package controle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.Session;

import persistence.ClassDao;
import persistence.HibernateUtil;
import entity.Cliente;
import entity.ItenPedido;
import entity.Pedido;
import entity.Produto;


@ManagedBean(name="beanPedido")
@ViewScoped
public class ManagedPedido {

	
	private Pedido pedido;
	private List<Pedido> pedidosList;
	private Session session;
	private ClassDao<Pedido> daoPedido = null;
	private ClassDao<ItenPedido> daoItensPedido = null;
	private ItenPedido itenPedido;
	private List<ItenPedido> itenPedidos;
	private Cliente cliente;
	private Integer qtdProd;
	private Produto produto;
	private Double total;
	private  Integer cont;
	private String valor;

	public ManagedPedido() {
		openSession();
		daoPedido = new ClassDao<Pedido>(Pedido.class, session);
		daoItensPedido = new ClassDao<ItenPedido>(ItenPedido.class, session);
		produto = new Produto();
		cliente = new Cliente();
		qtdProd = 1;
		itenPedidos = new ArrayList<ItenPedido>();
		itenPedido = new ItenPedido();
		pedido = new Pedido();
		valor = "0,00";
		cont = 1;
	}

	
	
	

	
	
	

	
	
//Regras de negócios	
	

	public String selecionado(){
		
		pedido = new Pedido();
		DecimalFormat df = new DecimalFormat("0.00");
		String con[] = new String[1];
		con = valor.split(",");
		valor = con[0]+"."+con[1];
		total = Double.parseDouble(valor);		
	
		
	pedido.setCliente(cliente);
	pedido.setDtPedido(new Date());
	pedido.setTotal(total);
	itenPedido.setPedido(pedido);
	itenPedido.setProduto(produto);
	itenPedido.setQtd(qtdProd);
	itenPedidos.add(itenPedido);
	
	total = total+( produto.getPreco()*qtdProd);	
	

			
	
	itenPedido = new ItenPedido();
		System.out.println(produto.getDescricao()+"  "+" qtd "+total+" "+"Cont "+itenPedidos.size());
		
	valor = df.format(total);
		return null;
		
	}
	
	
	public String remover(){
		
		//Formatar total para currency
		DecimalFormat df = new DecimalFormat("0.00");
		String con[] = new String[1];
		con = valor.split(",");
		valor = con[0]+"."+con[1];
		total = Double.parseDouble(valor);		

		itenPedidos.remove(itenPedido);
		total = total - (itenPedido.getProduto().getPreco()*itenPedido.getQtd());
		valor = df.format(total);
		pedido.setTotal(total);
		return null;
	
	
	}
	
	
	public String limparPedido(){
		
		itenPedidos.clear();
		valor = "0,00";
		return null;
	}
	
	public String finalizarPedido(){
		pedido.setStatus("Em aberto");
		
		for(int i = 0; i< itenPedidos.size(); i++){
				
			itenPedido.setPedido(pedido);
			itenPedido.setProduto(itenPedidos.get(i).getProduto());
			itenPedido.setQtd(qtdProd);
		}
		
	
		
		return null;
	}
	
	
	public String openSession(){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		return null;
		
	}
	
	
	//Fims Negócios
	
	
	
	
	
//Metodos Gets Sets Para sincronização dos Beans
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getQtdProd() {
		return qtdProd;
	}

	public void setQtdProd(Integer qtdProd) {
		this.qtdProd = qtdProd;
	}

	public ItenPedido getItenPedido() {
		return itenPedido;
	}

	public void setItenPedido(ItenPedido itenPedido) {
		this.itenPedido = itenPedido;
	}

	public List<ItenPedido> getItenPedidos() {
		return itenPedidos;
	}

	public void setItenPedidos(List<ItenPedido> itenPedidos) {
		this.itenPedidos = itenPedidos;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Pedido> getPedidosList() {
		return pedidosList;
	}

	public void setPedidosList(List<Pedido> pedidosList) {
		this.pedidosList = pedidosList;
	}

	//Fim Gets Sets
	
	
	
}
