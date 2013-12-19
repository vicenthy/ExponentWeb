package configuracao;

import javax.faces.bean.ManagedBean;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

@ManagedBean(name="beanBanco")
public class CriarTabelas {

	

public CriarTabelas() {


}
	
	
	public String criarTabelas(){
	try {
	
		Configuration cfg = new Configuration();
		cfg.configure("config/pgsql_hibernate.cfg.xml");
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	
	} catch (Exception e) {
		e.printStackTrace();

	
	}
	
	
	return "sistema.jsf";
	}
	
	
}
