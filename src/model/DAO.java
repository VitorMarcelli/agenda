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
			 * M�todo responsavel por abrir uma conex�o com o banco de dados
			 * @return
			 */
			public Connection conectar() {
				//Criar um objeto
				Connection con = null;
				
				//tratamento de exce��es 
				try {
					
					//l�gica principal para abrir a conex�o
					
					//Uso do Driver
					
					Class.forName(driver);
					
					//Obter os parametros da conex�o (informa��es do servidor)
					
					con = DriverManager.getConnection(url, user, password); // !conectar
					
					//retornar a conex�o ("Abrir a porta da geladeira")
					
					return con;
			
				} catch (Exception e) {
								System.out.println(e);
								return null;
							}
							
			}
}
