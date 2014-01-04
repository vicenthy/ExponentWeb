package controle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.primefaces.model.UploadedFile;

import persistence.ClassDao;
import persistence.HibernateUtil;
import entity.Produto;


@ManagedBean(name="beanImportar")
public class ImportarProdutos {

	
	private String diretorio;
	private Produto produto;
	private Session  session;
	private ClassDao<Produto> dao;
	private UploadedFile file;


public ImportarProdutos() { 

	//session = HibernateUtil.getSessionFactory().getCurrentSession();
	dao = new ClassDao<Produto>(Produto.class);

}





public String lerArquivo(){

	
	System.out.println("Diretorio recuperado:  "+diretorio);
	int cont = 0;
	String Slinha [] = new String[4];
	 String linha = "";
		String cod  = "";
		String produto =""; 
		String un="";
		String preco="";
	try {
		FileReader ler = new FileReader(abrirArquivo(diretorio));
		BufferedReader b = new BufferedReader(ler);
		
		// linha recebe a linha lida até a linha lida ser nula
		while((linha = b.readLine()) != null){
			Slinha = linha.split(";");
			 cod = Slinha[0] ;
			 produto = Slinha[1] ;
			 un = Slinha[2];
			 preco = Slinha[4];		
			 try {
				 
					Double valor = convert(preco.toString());
						 if(cont % 20 == 0){;
						 dao.clear();
						 }
			Produto p = new Produto(null, cod,produto,un,valor);
			dao.create(p);
							
			} catch (Exception e) {
				e.printStackTrace();
			}
			 System.out.println("Produto = "+"Codigo: "+cod+" "+"Produto: "+produto+" "+" UN: "+un+" "+"Preco: "+preco+" "+"Contatdor: "+cont );
		cont++;
		}
		ler.close();
		b.close();
		System.out.println("TOTAL DE PRODUTOS ------>>>>>>> "+cont);
	} catch (Exception e) {
		e.printStackTrace();
		
	
	}
	return null;
	
}



public Double convert(String numero){
	String num[] =  new String[1];
	
num = numero.split(",");
	numero = num[0]+"."+num[1];
	
	return Double.parseDouble(numero);
}


public File abrirArquivo(String diretorio){

	File  arquivo  = new File(diretorio);
	return arquivo;
}





public UploadedFile getFile() {
	return file;
}





public void setFile(UploadedFile file) {
	this.file = file;
}





public String getDiretorio() {
	return diretorio;
}



public void setDiretorio(String diretorio) {
	this.diretorio = diretorio;
}



public Produto getProduto() {
	return produto;
}



public void setProduto(Produto produto) {
	this.produto = produto;
}






public String teste(){
	System.out.println(file.getFileName());
	return null;
}




}
