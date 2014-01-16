package controle;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.criteria.Join;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.commons.collections.map.HashedMap;
import org.apache.jasper.servlet.JasperLoader;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.SessionFactoryImplementor;

import persistence.ClassDao;
import persistence.HibernateUtil;
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
	
	
	
	public String imprimirSalvar(){
		
		try {
			Connection con = ((SessionFactoryImplementor) HibernateUtil.getSessionFactory()).getConnectionProvider().getConnection();
			
		String arquivo =  FacesContext.getCurrentInstance().getExternalContext().getRealPath("relatorios\\report1.jrxml");
		 
		JasperReport jr =  JasperCompileManager.compileReport(arquivo);
			
		Map<String, Integer> parameters = new HashMap<String, Integer>();
		parameters.put("cod_pedido", pedido.getObjref());
		JasperPrint jp = JasperFillManager.fillReport(jr, parameters, con);
		
	HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	res.reset();
	res.setContentType("application/pdf");
	
	OutputStream out = res.getOutputStream();
	JasperExportManager.exportReportToPdfStream(jp, out);
	out.flush();
	out.close();
	con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
// Set e Get...
	
	
	public List<Pedido> getPedidos() {
	pedidos = daoPedido.findOrderDesc("objref");
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
