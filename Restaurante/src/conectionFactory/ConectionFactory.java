package conectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectionFactory {
	
	public Connection getConnection(){
		try{
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbRestaurante", "postgres", "1234");
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
