package beans;

public class PedidoItem {
	private int idPedido;
	private int idItem;
	private int quantidade;
	
	public int getIdPedido(){
		return idPedido;
	}
	
	public void setIdPedido(int idPedido){
		this.idPedido = idPedido;
	}
	
	public int getIdItem(){
		return idItem;
	}
	
	public void setIdItem(int idItem){
		this.idItem = idItem;
	}
	
	public int getQuantidade(){
		return quantidade;
	}
	
	public void setQuantidade(int quantidade){
		this.quantidade = quantidade;
	}
	

}
