package ECOMMERCE;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class createTables {
	public createTables() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project1", "root",
					"root");
			Statement stmt = con.createStatement();
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "customer", null);
			if (!tables.next()) {
				String sql = "create table customer " + " (customer_first_name varchar(50) NOT NULL, "
						+ " customer_last_name varchar(50)," + " customer_id int(100) PRIMARY KEY, "
						+ " customer_password varchar(50) NOT NULL," + " customer_email varchar(30) NOT NULL,"
						+ " customer_phoneno varchar(50) NOT NULL," + " customer_alternate_no varchar(50) ,"
						+ " customer_address varchar(50) NOT NULL," + " customer_gender varchar(10) NOT NULL  )";
				stmt.executeUpdate(sql);
			}
			tables = dbm.getTables(null, null, "cart", null);
			if (!tables.next()) {
				String sql = "create table cart " + " (prod_id int , " + " prod_quantity int ,"
						+ "customer_id  int NOT NULL )";
				stmt.executeUpdate(sql);
				stmt.executeUpdate("ALTER TABLE cart add primary key(prod_id, customer_id)");
				stmt.executeUpdate(
						"Alter table cart ADD CONSTRAINT cart_productid FOREIGN KEY(prod_id) REFERENCES product(prod_id)");
				stmt.executeUpdate(
						"Alter table cart ADD CONSTRAINT cart_customerID FOREIGN KEY(customer_id) REFERENCES customer(customer_id)");
			}
		} catch (Exception e) {
		}
	}
}
