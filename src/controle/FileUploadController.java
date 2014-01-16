package controle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import persistence.ClassDao;
import entity.Produto;
  
@ManagedBean
public class FileUploadController {  
  
    private UploadedFile file;  
	private String diretorio;
	private Produto produto;
	private ClassDao<Produto> dao;

  public FileUploadController() {

dao = new ClassDao<Produto>(Produto.class);
  }
  
    public void upload() {  
        if(file != null) {  
            
        	/*
        	FacesContext context = FacesContext.getCurrentInstance();

            diretorio =  context.getExternalContext().getRealPath("/");
        	diretorio = diretorio+"upload\\";
        	File f = new File(diretorio);
        	f.mkdir();
    	
        	FileOutputStream fos;
			try {
				
				fos = new FileOutputStream(diretorio+file.getFileName());
				fos.write(file.getContents());
	        	fos.flush();
	        	fos.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        
        	    */
        		System.out.println("Diretorio recuperado:  "+diretorio);
        		System.out.println(file.getFileName()+" Arquivo>>>>>>>>>>>>>>>>>>>>>");
        	
        		int cont = 0;
        		String Slinha [] = new String[4];
        		 String linha = "";
        			String cod  = "";
        			String produto =""; 
        			String un="";
        			String preco="";
        		try {
        			FileReader ler = new FileReader(file.getFileName());
        			BufferedReader b = new BufferedReader(ler);
        			
        			// linha recebe a linha lida até a linha lida ser nula
        			while((linha = b.readLine()) != null){
        				Slinha = linha.split(";");
        				System.out.println("Slinha teste>>>>>>>"+Slinha.length);
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
        				    FacesMessage msg = new FacesMessage("Error", e.getMessage());  
                	        FacesContext.getCurrentInstance().addMessage(null, msg);  
                	  
        				}
        				 System.out.println("Produto = "+"Codigo: "+cod+" "+"Produto: "+produto+" "+" UN: "+un+" "+"Preco: "+preco+" "+"Contatdor: "+cont );
        			cont++;
        			}
        			ler.close();
        			b.close();
        		} catch (Exception e) {
        			e.printStackTrace();
        		    FacesMessage msg = new FacesMessage("Error", e.getMessage());  
        	        FacesContext.getCurrentInstance().addMessage(null, msg);  
        	    	
        		
        		}

     			System.out.println("TOTAL DE PRODUTOS ------>>>>>>> "+cont);
            
        }  
    }
    
    
    

    public void handleFileUpload(FileUploadEvent event) {  
    file = event.getFile();
    upload();
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " Arquivo importado com sucesso.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
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
    
    
    
    
    
}  
  