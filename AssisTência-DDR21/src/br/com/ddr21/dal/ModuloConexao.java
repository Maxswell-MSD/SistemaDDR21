/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ddr21.dal;
// este import libera para as conexões com o banco
import java.sql.*;

/**
 *
 * @author Maxswell Diniz
 */
public class ModuloConexao {
    //Estabelecendo a conexão com o banco
    public static Connection conector(){
       Connection conexao = null;
       //chamando o driver importado da biblioteca
       String driver = "com.mysql.cj.jdbc.Driver";
       // Armazenando informações referente ao banco
       String url = "jdbc:mysql://localhost:3306/ddr21";
       String user = "root";
       String password = "M6936995591845880s";
       // iniciando a conexão com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            // abaixo serve de apoio para ajudar no erro de conexão
            //System.out.println(e);
            return null;
        }
       
       
        
    }
     
    
}
