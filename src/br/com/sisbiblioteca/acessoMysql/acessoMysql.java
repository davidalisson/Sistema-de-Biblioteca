/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisbiblioteca.acessoMysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Murilo Alves
 */
public class acessoMysql {
    Connection con;

public static void main(String args[]){
    new acessoMysql();
}

public acessoMysql(){
    conectar();
}

public Connection conectar(){
    try{
       Class.forName("com.mysql.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/sisbiblioteca?user=root&password=");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível encontrar o banco");
        }catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível conectar ao banco");
        }
    return con;
    }

    public void desconectar(){
         try{
             con.close();
         } catch (SQLException ex){
             ex.printStackTrace();
         }
    }
}
