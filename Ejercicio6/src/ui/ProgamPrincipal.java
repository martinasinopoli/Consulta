package ui;

import java.sql.*;
import entities.*;

import java.util.Iterator;
import java.util.LinkedList;

public class ProgamPrincipal {
	
public static void main(String[] args) {
		
		// registrar el conector
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		recuperarTodos();
		
	}

private static void recuperarTodos() {
	Connection conn = null;
	LinkedList<Product> producto = new LinkedList<>();
	try {
		// crear una conexión
		conn =
	       DriverManager.getConnection("jdbc:mysql://localhost:3306/javaMarket","java","himitsu");
		 //DriverManager.getConnection("jdbc:mysql://localhost:3306/javamarket?user=java&password=himitsu");

		// ejecutar la query
        Statement stmt = conn.createStatement();
        ResultSet rs= stmt.executeQuery("select * from product");

        // mapear de resultset a objeto
        while(rs.next()) {
        	Product p=new Product();

            p.setId(rs.getInt("id"));
            p.setNombre(rs.getString("nombre"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setPrecio(rs.getDouble("precio"));
            p.setShippingIncluded(rs.getBoolean("shippingIncluded"));

            producto.add(p);

        }
        
        //cerrar recursos
        if(rs!=null){rs.close();}
        if(stmt!=null){stmt.close();}

	    conn.close();
	    
	    // mostrar info
	    System.out.println("Listado Completo");
	    System.out.println(producto);
	    System.out.println();System.out.println();
	    System.out.println("prueba");

	} catch (SQLException ex) {
	    // Manejo de errores
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
}


}
