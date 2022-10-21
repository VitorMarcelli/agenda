package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	
			//criando variaveis encapsuladas para acesso ao banco
			private String driver = "com.mysql.cj.jdbc.Driver";
			private String url = "jdbc:mysql://10.26.49.112:3306/agenda";
			private String user = "root";
			private String password = "123@senac";
			
			/**
			 * Método responsavel por abrir uma conexão com o banco de dados
			 * @return
			 */
			public Connection conectar() {
				//Criar um objeto
				Connection con = null;
				
				//tratamento de exceções 
				try {
					
					//lógica principal para abrir a conexão
					
					//Uso do Driver
					
					Class.forName(driver);
					
					//Obter os parametros da conexão (informações do servidor)
					
					con = DriverManager.getConnection(url, user, password); // !conectar
					
					//retornar a conexão ("Abrir a porta da geladeira")
					
					return con;
			
				} catch (Exception e) {
								System.out.println(e);
								return null;
							}
							
			}
}
