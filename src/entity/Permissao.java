package entity;

public class Permissao {
	
	
	private Integer objref;
	private String permissão;
	private Usuario usuario;
	
	
	
	




	public Permissao(Integer objref, String permissão, Usuario usuario) {
		super();
		this.objref = objref;
		this.permissão = permissão;
		this.usuario = usuario;
	}








	public Integer getObjref() {
		return objref;
	}








	public void setObjref(Integer objref) {
		this.objref = objref;
	}








	public String getPermissão() {
		return permissão;
	}








	public void setPermissão(String permissão) {
		this.permissão = permissão;
	}








	public Usuario getUsuario() {
		return usuario;
	}








	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	
	
	
}
