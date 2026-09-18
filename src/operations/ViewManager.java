package operations;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import utility.Ansi;

public class ViewManager {
	private final Connection con;
	private final SelectOperation selectOp;
	private final Scanner scan;
	
	
	public ViewManager(Connection con, SelectOperation selectOperation, Scanner scan) {
		this.con = con;
		this.selectOp = selectOperation;
		this.scan = scan;
	}

	
	public void showViewsMenu() {
		boolean back = false;
		
		while(!back) {
			List<String> views = getDataBaseViews();
			
			if(views.isEmpty()) {
				System.out.println(Ansi.warning("Datu bāze nav atrasts neviens skats"));
				return;
			}
			
			System.out.println("\n--- SKATI ---");
			for(int i = 0; i < views.size(); i++) {
				System.out.println((i + 1) + ". " + views.get(i));
			}
			
			System.out.println("0. Atpakaļ\n" + "Izvēle: ");
			String choice = scan.nextLine().trim();
			
			if(choice.equals("0")) {
				back = true;
				
			}else {
				try {
					int index = Integer.parseInt(choice) - 1;
					
					if(index >= 0 && index < views.size()) {
						selectOp.select(con, views.get(index));
					} else {
						System.out.println(Ansi.warning("Nepareizi izvēlēts skats"));
					}
				}catch(NumberFormatException e){
					System.out.println(Ansi.warning("Nepareiza izvēle"));
				}
			}
		}
	}


	private List<String> getDataBaseViews() {
		List<String> views = new ArrayList<>();
		
		try {
			DatabaseMetaData metaData = con.getMetaData();
			
			try(ResultSet rs = metaData.getTables(con.getCatalog(), null, "%", new String[] {"VIEW"})){
				
				while(rs.next()) {
					views.add(rs.getString("TABLE_NAME"));
				}
			}
		}catch(SQLException e) {
			System.out.println(Ansi.error("Kļūda iegūstot skatu sarakstu: " + e.getMessage()));
		}
		return views;
	}
}
