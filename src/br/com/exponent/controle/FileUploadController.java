package br.com.exponent.controle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.hibernate.criterion.Restrictions;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import br.com.exponent.entity.Produto;
import br.com.exponent.persistence.ClassDao;

@ManagedBean
public class FileUploadController {

	private UploadedFile file;
	private String diretorio;
	private Produto produto;
	private ClassDao<Produto> dao;
	private int cont = 1;
	private String Slinha[]  = new String[4];;
	private String linha = "";
	private String cod = "";
	private String Sproduto = "";
	private String un = "";
	private String preco = "";

	public FileUploadController() {

		dao = new ClassDao<Produto>(Produto.class);
	}

	public void upload() throws IOException {
		if (file != null) {

			// Abaixo método para upload que não está funcionando, está dando
			// erro de acesso negado

			// uploadArquivo();

			FileReader ler = new FileReader(file.getFileName());
			BufferedReader b = new BufferedReader(ler);
			Produto p = null;
			Produto pTemp = null;

			// linha recebe a linha lida até a linha lida ser nula
			while ((linha = b.readLine()) != null) {

				if (linha.split(";").length == 5) {
					Slinha = linha.split(";");

					System.out.println("Tamanho Slinha ------>>" + Slinha.length);

					cod = Slinha[0];
					Sproduto = Slinha[1];
					un = Slinha[2];
					preco = Slinha[4];

					Double valor = convert(preco);

					if (cont % 20 == 0) {
						dao.clear();
						System.out.println("limpando Cache Hibernate");
					}
					

					 
					pTemp  = (Produto) dao.consultaByCriteria().add(Restrictions.eq("codigo", cod)).uniqueResult();
					//se não encontrar nenhum produto pelo codigo cria um novo
					if(pTemp == null){
						
						p = new Produto(null, cod, Sproduto, un, valor);
						p = dao.save(p);
						System.out.println("Produto inserido =  " + "Codigo: "
								+ cod + " " + "Produto: " + Sproduto + " "
								+ " UN: " + un + " " + "Preco: " + preco + " "
								+ "Contatdor: " + cont);
				//se nenhum produto foi criado é pq encontro		
					}else if(p == null){				
						dao.update(pTemp);
						System.out.println("Produto atualizado ---> "+pTemp.getDescricao());
						pTemp = null;
					}
					
					p = null;
					cont++;


				}
				
				// se o tamanho do array for diferente do esperado
				// quer dizer que os dados do produto está incompleto
				// então não é possivek inserir o produto
			}

			ler.close();
			b.close();

		}

		System.out.println("TOTAL DE PRODUTOS ------>>>>>>> " + cont);
	}

	public void handleFileUpload(FileUploadEvent event) throws IOException {
		file = event.getFile();
		upload();
		FacesMessage msg = new FacesMessage("Succesful", event.getFile()
				.getFileName() + " Arquivo importado com sucesso.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Double convert(String numero) {
		String num[] = new String[1];
		num = numero.split(",");
		numero = num[0] + "." + num[1];
		return Double.parseDouble(numero);
	}

	public File abrirArquivo(String diretorio) {
		File arquivo = new File(diretorio);
		return arquivo;
	}

	public void uploadArquivo() {

		FacesContext context = FacesContext.getCurrentInstance();

		diretorio = context.getExternalContext().getRealPath("/");

		File file1 = new File(diretorio + "\\upload\\");
		file1.mkdirs();

		diretorio = diretorio + "upload\\";
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(diretorio + file.getFileName());
			fos.write(file.getContents());
			fos.flush();
			fos.close();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("Diretorio recuperado:  " + diretorio);
		System.out
				.println(file.getFileName() + " Arquivo>>>>>>>>>>>>>>>>>>>>>");

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
