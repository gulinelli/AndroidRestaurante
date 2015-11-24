package beans;

import java.util.List;

public class Pedido {
	
	private int idPedido;
	private int loginUsuario;
	private String tipoPagamento;
	private List<Item> itens;
	
	
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getLoginUsuario() {
		return loginUsuario;
	}
	public void setLoginUsuario(int loginUsuario) {
		this.loginUsuario = loginUsuario;
	}
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	

}
