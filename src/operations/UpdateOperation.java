package operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import utility.Ansi;

public class UpdateOperation {

	static Scanner scan = new Scanner(System.in);

	public void update(Connection con, String table) {
		try {
			switch(table) {
			case "city" -> updateCity(con);
//			case "country" -> insertCountry(con);
//			case "countrylanguage" -> insertCountryLanguage(con);
			
			default -> System.out.println(Ansi.warning("Neatbalstīta tabula: " + table));
			}
		}catch(SQLException e) {
			System.out.println(Ansi.error("UPDATE Kļūda: " + e.getMessage()));
		}
	}
	
	private void updateCity(Connection con) throws SQLException{
		System.out.println("Kuru pilsētu atjaonināsiet(Norādi ID)");
		int id = scan.nextInt();
		
		scan.nextLine();
		System.out.println("Ievadi pilsētas nosaukumu");
		String name = scan.nextLine();
		
		System.out.println("Ievadi valsts kodu (3 simboli)");
		String countryCode = scan.nextLine();
		
		System.out.println("Norādi apgabalu");
		String district = scan.nextLine();
		
		System.out.println("Norādi iedzīvotāju skaitu");
		int population = scan.nextInt();
		
		scan.nextLine();
		
		String sql = "UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?";
		
		try (PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, name);
			ps.setString(2, countryCode);
			ps.setString(3, district);
			ps.setInt(4, population);
			ps.setInt(5, id);
			
			int rows = ps.executeUpdate();
			System.out.println(Ansi.success("CITY tabulā atjaunināta: " + rows + " rindas"));
		}
		
	}

}
