package controle;

import java.io.Serializable;
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
public class ManagedPedido implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pedido pedido;
	private List<Pedido> pedidosList;
	//private Session session;
	private ClassDao<Pedido> daoPedido;
	private ClassDao<ItenPedido> daoItensPedido ;
	private ClassDao<Cliente> daoCliente;
	private ItenPedido itenPedido;
	private List<ItenPedido> itenPedidos;
	private Cliente cliente;
	private Integer qtdProd;
	private Produto produto;
	private Double total;
	private  Integer cont;

	public ManagedPedido() {
		daoPedido = new ClassDao<Pedido>(Pedido.class);
		daoCliente = new ClassDao<Cliente>(Cliente.class);
		daoItensPedido = new ClassDao<ItenPedido>(ItenPedido.class);
		produto = new Produto();
		cliente = new Cliente();
		qtdProd = 1;
		itenPedidos = new ArrayList<ItenPedido>();
		itenPedido = new ItenPedido();
		pedido = new Pedido();
		total = 0.;
		cont = 1;
	}

	
	
	

	
	
	

	
	
//Regras de negócios	
	

	public String selecionado(){
		
		pedido = new Pedido();
	pedido.setCliente(cliente);
	pedido.setDtPedido(new Date());
	itenPedido.setPedido(pedido);
	itenPedido.setProduto(produto);
	itenPedido.setQtd(qtdProd);
	
	itenPedidos.add(itenPedido);
	
	total = total+( produto.getPreco()*qtdProd);	
	
	pedido.setTotal(total);
	System.out.println("Valor do Total do Pedidos:  "+total.toString());
		itenPedido = new ItenPedido();
		System.out.println(produto.getDescricao()+"  "+" qtd "+total+" "+"Cont "+itenPedidos.size());
		qtdProd = 1;		
		return null;
		
	}
	
	
	public String remover(){
		itenPedidos.remove(itenPedido);
		total = total - (itenPedido.getProduto().getPreco()*itenPedido.getQtd());
		pedido.setTotal(total);
		return null;
	}
	
	
	
	//Formata para separar por virgula
	private String format(String valor){
		String con[] = new String[1];
		con = valor.split(",");
		valor = con[0]+"."+con[1];
		return valor;
	}

	//Formata para separar por ponto
	private String format(Double valor){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(valor);
	}

	
	
	
	public String limparPedido(){
		itenPedidos.clear();
		total = 0.;
		return null;
	}
	
	
	
	public String finalizarPedido(){
		System.out.println(pedido.toString());
	System.out.println("Numeros de produtos no momento: "+ itenPedidos.size());
			//por default o pedido é setado como aberto
			pedido.setStatus("Em aberto");
			
			try {			
			//sessão chegando como null aqui
				if(pedido.getFormaPg()==""){
					pedido.setFormaPg("Dinheiro ");
				}
			pedido = daoPedido.save(pedido);

				
				for(int i = 0; i< itenPedidos.size(); i++){
					itenPedido.setPedido(pedido);
					itenPedido.setProduto(itenPedidos.get(i).getProduto());
					itenPedido.setQtd(itenPedidos.get(i).getQtd());
					daoItensPedido.create(itenPedido);
					itenPedido = new ItenPedido();
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
			
			}
			
			System.out.println(">>>>>>>>>>>PEDIDO CRIADO COM SUCESSO!!!!!!!<<<<<<<<<");

		cliente = new Cliente();
		pedido = new Pedido();
		itenPedidos = new ArrayList<ItenPedido>();
		return "verPedidos.jsf";
	}
	
	
	//Fims Negócios
	
	
	
	
	
//Metodos Gets Sets Para sincronização dos Beans


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
