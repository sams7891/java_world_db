package operations;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import db.DataBaseConnection;
import utility.Ansi;

public class WorldDB {

    private static Connection con;
    private static final Scanner scan = new Scanner(System.in);

    private static String chooseTable() {

        while (true) {

            System.out.println(
                    Ansi.BACKGROUND_MAGENTA
                    + "\n--- TABULAS ---"
                    + Ansi.RESET
                    + "\n1. City"
                    + "\n2. Country"
                    + "\n3. CountryLanguage"
                    + "\n0. Atpakaļ"
                    + "\nIzvēlies tabulu: "
                    + Ansi.RESET
            );

            String choice = scan.nextLine().trim();

            return switch (choice) {

                case "1" -> "city";
                case "2" -> "country";
                case "3" -> "countrylanguage";
                case "0" -> "exit";

                default -> {
                    System.out.println(Ansi.warning("Nepareiza izvēle!"));

                    yield "exit";
                }
            };
        }
    }

    private static void tableMenu(String table, SelectOperation selectOp) {

        boolean back = false;

        while (!back) {

            System.out.println(
                    Ansi.BACKGROUND_MAGENTA
                    + "\n--- " + table.toUpperCase() + " ---"
                    + Ansi.RESET
                    + "\n1. Atlasīt (SELECT)"
                    + "\n2. Pievienot (INSERT)"
                    + "\n3. Atjaunināt (UPDATE)"
                    + "\n4. Dzēst (DELETE)"
                    + "\n0. Atpakaļ"
                    + "\nIzvēlies izvēli: "
                    + Ansi.RESET
            );

            String choice = scan.nextLine().trim();

            switch (choice) {
                case "1" -> selectOp.select(con, table);
                case "0" -> back = true;
                default ->
                        System.out.println(Ansi.warning("Nepareiza izvēle!"));
            }
        }
    }

    public static void main(String[] args) {

        try {

            con = DataBaseConnection.getConnection();

            System.out.println(Ansi.success("Izveidots savienojums ar DB"));

            SelectOperation selectOp = new SelectOperation();
            ViewManager viewManager = new ViewManager(con, selectOp, scan);

            boolean running = true;

            while (running) {

                System.out.println(
                        Ansi.BACKGROUND_MAGENTA
                        + "\n----- WORLD DB -----"
                        + Ansi.RESET
                        + "\n1. Tabulas"
                        + "\n2. Skats"
                        + "\n0. Apturēt"
                        + "\nIzvēlies darbību: "
                        + Ansi.RESET
                );

                String choice = scan.nextLine().trim();

                switch (choice) {
                    case "1" -> {

                        String table = chooseTable();

                        if (!table.equals("exit")) {
                            tableMenu(table, selectOp);
                        }
                    }
                    case "2" -> viewManager.showViewsMenu();
                    case "0" ->running = false;
                    default ->
                            System.out.println(Ansi.warning("Nepareiza izvēle!"));
                }
            }

            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
