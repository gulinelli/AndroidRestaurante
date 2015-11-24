package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Item;
import conectionFactory.ConectionFactory;

public class ItemDAO {
	
	public ItemDAO(){}
	
	/*--------------- SELECIONA ITEMS ------------------ */
	
	public ArrayList<Item> selecionaItems(){
		String sql = "select nome\n" +
					 "	, valor\n" +
					 "	, imagem\n" +
					 "from item";
		
		try(Connection conexao = new ConectionFactory().getConnection()){
			try(PreparedStatement stmt = conexao.prepareStatement(sql)){
				try(ResultSet rs = stmt.executeQuery()){
					ArrayList<Item> listaItems = new ArrayList<>();
					while(rs.next()){
						String nome = rs.getString("nome");
						double valor = rs.getDouble("valor");
						String imagem = rs.getString("imagem");
						Item item = new Item(nome, valor, imagem);
						listaItems.add(item);
					}
					return listaItems;
				} catch(Exception e){
					e.printStackTrace();
				}
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}catch(Exception e2){
			e2.printStackTrace();
		}
		
		return null;
	}
	
	/*-------------------------------------------------------- */
	
	/*--------------- SELECIONA ITEM ------------------ */
	public int selecionaItem(String nomeItem){
		String sql = "SELECT iditem FROM item WHERE nome = ?";
		
		
		try(Connection conexao = new ConectionFactory().getConnection()){
			try(PreparedStatement stmt = conexao.prepareStatement(sql)){
				stmt.setString(1, nomeItem);
				try(ResultSet rs = stmt.executeQuery()){
					int idItem = 0;
					while(rs.next()){
						idItem = rs.getInt("iditem");
					}
					return idItem;
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
