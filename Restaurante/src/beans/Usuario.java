package beans;

public class Usuario {
	
	protected String login;
	protected String senha;
	
	/* ----------- CONSTRUTOR ---------*/
	
	public Usuario(String login, String senha){
		this.login = login;
		this.senha = senha;
	}
	/* --------------------------------*/
	
	/* ----------- GETTERS AND SETTERS ---------*/
	
	public String getLogin(){
		return login;
	}
	
	public void setLogin(String login){
		this.login = login;		
	}
	
	public String getSenha(){
		return senha;
	}
	
	public void setSenha(String senha){
		this.senha = senha;
	}
	/* ------------------------------------------*/

}
