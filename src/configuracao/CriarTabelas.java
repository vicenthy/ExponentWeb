package configuracao;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class CriarTabelas {

	
	
	public static void main(String[] args) {
		criarTabelas();
	}
	
	
	public static void criarTabelas(){
	try {
	
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure("config/pgsql_hibernate.cfg.xml");
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	
	} catch (Exception e) {
		e.printStackTrace();

	
	}
	
	
	}
	
	
}
