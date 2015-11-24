package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import conectionFactory.ConectionFactory;

public class PedidoDAO {
	
	public PedidoDAO(){}
	
	/*---------------- INSERE ITENS NO PEDIDO -----------*/
	
	public void setNovoPedido(String loginUsuario) throws SQLException{
		String sql = "INSERT INTO pedido(loginUsuario) values (?)";
		
		try(Connection conexao = new ConectionFactory().getConnection()){
			try(PreparedStatement stmt = conexao.prepareStatement(sql)){
				stmt.setString(1, loginUsuario);
				stmt.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*---------------- SELECIONA PEDIDO -----------*/
	public int selecionaPedido(String loginUsuario) {
		String sql = "SELECT idpedido FROM pedido WHERE loginusuario = ?";
		
			try(Connection conexao = new ConectionFactory().getConnection()){
				System.out.println("Conexão realizada com sucesso");
				try(PreparedStatement stmt = conexao.prepareStatement(sql)){
					stmt.setString(1, loginUsuario);
					try(ResultSet rs = stmt.executeQuery()){
						int idPedido = 0;
						while(rs.next()){
							idPedido  = rs.getInt("idpedido");
						}
						return idPedido;
						}catch(Exception e){
							e.printStackTrace();
						}
				}catch(Exception e){
					e.printStackTrace();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		return 0;
	}
	

}
