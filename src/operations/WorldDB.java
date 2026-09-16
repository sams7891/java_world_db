package operations;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import db.DataBaseConnection;

public class WorldDB {
	private static Connection con;
	private static final Scanner scan = new Scanner(System.in);
	
	public static final String  RESET				= "\u001B[0m";
	
	public static final String	HIGH_INTENSITY		= "\u001B[1m";
	public static final String	LOW_INTENSITY		= "\u001B[2m";

	public static final String	ITALIC				= "\u001B[3m";
	public static final String	UNDERLINE			= "\u001B[4m";
	public static final String	BLINK				= "\u001B[5m";
	public static final String	RAPID_BLINK			= "\u001B[6m";
	public static final String	REVERSE_VIDEO		= "\u001B[7m";
	public static final String	INVISIBLE_TEXT		= "\u001B[8m";

	public static final String	BLACK				= "\u001B[30m";
	public static final String	RED					= "\u001B[31m";
	public static final String	GREEN				= "\u001B[32m";
	public static final String	YELLOW				= "\u001B[33m";
	public static final String	BLUE				= "\u001B[34m";
	public static final String	MAGENTA				= "\u001B[35m";
	public static final String	CYAN				= "\u001B[36m";
	public static final String	WHITE				= "\u001B[37m";

	public static final String	BACKGROUND_BLACK	= "\u001B[40m";
	public static final String	BACKGROUND_RED		= "\u001B[41m";
	public static final String	BACKGROUND_GREEN	= "\u001B[42m";
	public static final String	BACKGROUND_YELLOW	= "\u001B[43m";
	public static final String	BACKGROUND_BLUE		= "\u001B[44m";
	public static final String	BACKGROUND_MAGENTA	= "\u001B[45m";
	public static final String	BACKGROUND_CYAN		= "\u001B[46m";
	public static final String	BACKGROUND_WHITE	= "\u001B[47m";
	
	
	private static String chooseTable() {
		while(true) {
			System.out.println( BACKGROUND_MAGENTA + "\n--- TABULAS ---" + RESET
					+ "\n1. City"
					+ "\n2. Country"
					+ "\n3. CountryLanguage"
					+ "\n0. Atpakaļ"
					+ "\nIzvēlies tabulu: " + BLINK);
			
			String c = scan.nextLine().trim();

			
			return switch(c) {
			case "1" -> "city";
			case "2" -> "country";
			case "3" -> "countrylanguage";
			case "0" -> "exit";
			
			default -> {
				System.out.println(BACKGROUND_RED + HIGH_INTENSITY  + "Nepareize izvēle!" + RESET);
				yield "exit";
			}
			};
		}
	}
	
	private static void tableMenu(String table, SelectOperation selectOp) {
		boolean back = false;
		
		while(!back) {
			System.out.println(BACKGROUND_MAGENTA + "\n--- " + table.toUpperCase() + " ---" + RESET
					+ "\n1. Atlasīt (SELECT)"
					+ "\n2. Pievienot (INSERT)"
					+ "\n3. Atjaunināt (UPDATE)"
					+ "\n4. Dzēst (DELETE)"
					+ "\n0. Atpakaļ"
					+ "\nIzvēlies izvēli: " + BLINK);
			
			String c = scan.nextLine().trim();
			
			switch(c) {
			
			}

		}
	}
	
	public static void main(String[] args) {
		try {
			con = DataBaseConnection.getConnection();
			System.out.println("Izveidots savienojums ar DB");
			
			SelectOperation selectOp = new SelectOperation();
			
			boolean running = true;
			
			
			
			while(running) {
				System.out.println(BACKGROUND_MAGENTA + "\n----- WORLD DB -----" + RESET
						+ "\n1. Tabulas"
						+ "\n2. Skaits"
						+ "\n0. Apturēt"
						+ "\nIzvēlies darbību: " + BLINK);
				
				String mainChoice = scan.nextLine().trim();
				
				switch(mainChoice) {
				case "1" -> {
					String table = chooseTable();
					
					if(!table.equals("exit")) {
//						tableMenu(table, selectOp);
					}
				}
				
				case "2" ->
				System.out.println("Not implemented");
				
				case "0" -> running = false;
				
				default -> System.out.println(BACKGROUND_RED + HIGH_INTENSITY  + "Nepareize izvēle!" + RESET);
				}
				
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
