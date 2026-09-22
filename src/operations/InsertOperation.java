package operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import utility.Ansi;

public class InsertOperation {
	
	static Scanner scan = new Scanner(System.in);

	public void insert(Connection con, String table) {
		try {
			switch(table) {
			case "city" -> insertCity(con);
//			case "country" -> insertCountry(con);
//			case "countrylanguage" -> insertCountryLanguage(con);
			
			default -> System.out.println(Ansi.warning("Neatbalstīta tabula: " + table));

			}
		}catch(SQLException e) {
			System.out.println(Ansi.error("INSERT Kļūda: " + e.getMessage()));
		}
	}
	
	private void insertCity(Connection con) throws SQLException{
		System.out.println("Ievadi pilsētas nosaukumu");
		String name = scan.nextLine();
		
		System.out.println("Ievadi valsts kodu (3 simboli)");
		String countryCode = scan.nextLine();
		
		System.out.println("Norādi apgabalu");
		String district = scan.nextLine();
		
		System.out.println("Norādi iedzīvotāju skaitu");
		int population = scan.nextInt();
		
		scan.nextLine();

		String sql = "INSERT INTO city (Name, CountryCode, District, Population) VALUES (?, ?, ?, ?)";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, name);
			ps.setString(2, countryCode);
			ps.setString(3, district);
			ps.setInt(4, population);
			
			int rows = ps.executeUpdate();
			System.out.println(Ansi.success("CITY tabulā ievietotas: " + rows + " rindas"));
		}
	}
	
	private void insertCountry(Connection con) throws SQLException{		
		System.out.println("Ievadi valsts kodu (3 simboli)");
		String countryCode = scan.nextLine();
		
		System.out.println("Ievadi valsts nosaukumu");
		String name = scan.nextLine();
		
		while(true) {
			System.out.println("Ievadi valsts nosaukumu");
			for(Continent continent : Continent.values()) {
				System.out.println(continent);
			}
		}
		
		System.out.println("Norādi iedzīvotāju skaitu");
		int population = scan.nextInt();
		
		scan.nextLine();

		String sql = "INSERT INTO city (Name, CountryCode, District, Population) VALUES (?, ?, ?, ?)";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, name);
			ps.setString(2, countryCode);
			ps.setString(3, district);
			ps.setInt(4, population);
			
			int rows = ps.executeUpdate();
			System.out.println(Ansi.success("CITY tabulā ievietotas: " + rows + " rindas"));
		}
	}
	
	
}
