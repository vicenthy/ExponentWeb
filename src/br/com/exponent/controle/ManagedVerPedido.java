package br.com.exponent.controle;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.SessionFactoryImplementor;

import br.com.exponent.entity.ItemPedido;
import br.com.exponent.entity.Pedido;
import br.com.exponent.persistence.ClassDao;
import br.com.exponent.persistence.HibernateUtil;

@ManagedBean(name = "beanVerpedido")
@ViewScoped
public class ManagedVerPedido {

	private List<Pedido> pedidos;
	private ClassDao<Pedido> daoPedido;
	private Pedido pedido;
	private List<ItemPedido> itenPedidos;
	private ClassDao<ItemPedido> daoItenPedido;

	public ManagedVerPedido() {
		daoPedido = new ClassDao<Pedido>(Pedido.class);
		daoItenPedido = new ClassDao<ItemPedido>(ItemPedido.class);
		// TODO Auto-generated constructor stub
	}

	// Negócios

	public String fechar() {
		pedido.setStatus("Fechado");
		System.out.println(pedido.getStatus());

		return null;
	}

	public String desfazer() {
		pedido.setStatus("Em aberto");
		System.out.println(pedido.getStatus());

		return null;
	}

	@SuppressWarnings("unchecked")
	public String verDetalhePedido() {

		itenPedidos = daoItenPedido.consultaByCriteria()
				.createAlias("pedido", "p")
				.add(Restrictions.eq("p.objref", pedido.getObjref())).list();
		return null;
	}

	public String imprimirSalvar() {

		try {
			Connection con = ((SessionFactoryImplementor) HibernateUtil
					.getSessionFactory()).getConnectionProvider()
					.getConnection();
			String arquivo = FacesContext.getCurrentInstance()
					.getExternalContext()
					.getRealPath("relatorios\\report1.jrxml");
			JasperReport jr = JasperCompileManager.compileReport(arquivo);
			Map<String, Integer> parameters = new HashMap<String, Integer>();
			parameters.put("cod_pedido", pedido.getObjref());
			JasperPrint jp = JasperFillManager.fillReport(jr, parameters, con);
			HttpServletResponse res = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			res.setContentType("application/pdf");
			OutputStream out = res.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jp, out);
			out.flush();
			out.close();
			con.close();
		} catch (Exception e) {
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

	public List<ItemPedido> getItenPedidos() {
		return itenPedidos;
	}

	public void setItenPedidos(List<ItemPedido> itenPedidos) {
		this.itenPedidos = itenPedidos;
	}

}
