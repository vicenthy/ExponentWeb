package controle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.swing.filechooser.FileSystemView;

import org.hibernate.Session;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import persistence.ClassDao;
import entity.Produto;


@ManagedBean(name="beanImportar")
public class ImportarProdutos {

	
	private String diretorio;
	private Produto produto;
	private ClassDao<Produto> dao;
	private UploadedFile file;


public ImportarProdutos() { 

	dao = new ClassDao<Produto>(Produto.class);

}





public void lerArquivo( ) throws IOException{

	System.out.println(file);
	
    FacesContext context = FacesContext.getCurrentInstance();
    diretorio =  context.getExternalContext().getRealPath("/");
   
	//File[] f = File.listRoots();
	//File f1 = new  File(f[0] +"/arquivo");
	//f1.mkdir();
	
	diretorio = System.getProperty("user.home");
	
FileOutputStream fos = new FileOutputStream(diretorio);
fos.write(file.getContents());
fos.flush();
fos.close();
    
	System.out.println("Diretorio recuperado:  "+diretorio);
	int cont = 0;
	String Slinha [] = new String[4];
	 String linha = "";
		String cod  = "";
		String produto =""; 
		String un="";
		String preco="";
	try {
		FileReader ler = new FileReader(diretorio);
		BufferedReader b = new BufferedReader(ler);
		
		// linha recebe a linha lida at� a linha lida ser nula
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
