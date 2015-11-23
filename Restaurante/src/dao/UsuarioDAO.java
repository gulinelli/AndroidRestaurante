package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import beans.Usuario;
import conectionFactory.ConectionFactory;

public class UsuarioDAO {
	
	public UsuarioDAO(){}
	
	/*----------------- LOGIN USUARIO -----------------*/
	public boolean loginUsuario(Usuario usuario){
		String sql = "SELECT senha FROM usuario WHERE login = ?";
		try(Connection conexao = new ConectionFactory().getConnection()){
			System.out.println("Conexão realizada com sucesso");
			try(PreparedStatement stmt = conexao.prepareStatement(sql)){
				stmt.setString(1, usuario.getLogin());
				try(ResultSet rs = stmt.executeQuery()){
					String senhaBD = null;
					while(rs.next()){
						senhaBD = rs.getString("senha");
					}
					if(senhaBD.equals(usuario.getSenha()))
						return true;
				}catch (Exception e){
					e.printStackTrace();
				}
			}catch(Exception e){
				e.printStackTrace();
			} 			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}

}
