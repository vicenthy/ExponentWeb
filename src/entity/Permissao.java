package entity;

public class Permissao {
	
	
	private Integer objref;
	private String permiss�o;
	private Usuario usuario;
	
	
	
	




	public Permissao(Integer objref, String permiss�o, Usuario usuario) {
		super();
		this.objref = objref;
		this.permiss�o = permiss�o;
		this.usuario = usuario;
	}








	public Integer getObjref() {
		return objref;
	}








	public void setObjref(Integer objref) {
		this.objref = objref;
	}








	public String getPermiss�o() {
		return permiss�o;
	}








	public void setPermiss�o(String permiss�o) {
		this.permiss�o = permiss�o;
	}








	public Usuario getUsuario() {
		return usuario;
	}








	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	
	
	
}
