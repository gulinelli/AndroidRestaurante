package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;


import conectionFactory.ConectionFactory;

public class PedidoItemDAO {
	
	public PedidoItemDAO(){}
	
	/*------------- ADICIONA ITENS NO PEDIDO --------- */
	
	public boolean setPedidoItem(int idPedido, int idItem, int quantidade){
		String sql = "INSERT INTO pedidoItem VALUES (?, ?, ?)";
		try(Connection conexao = new ConectionFactory().getConnection()){
			try(PreparedStatement stmt = conexao.prepareStatement(sql)){
				stmt.setInt(1, idPedido);
				stmt.setInt(2, idItem);
				stmt.setInt(3, quantidade);
				stmt.executeUpdate();
				return true;
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	

}
