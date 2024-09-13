package org.example;

import org.example.auth.Account;
import org.example.auth.AccountManager;
import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        // Utworzenie obiektu DatabaseConnection
        DatabaseConnection dbConnection = new DatabaseConnection();

        // Ścieżka do pliku z bazą danych
        String dbPath = "D:\\Java\\test.db";

        // Połączenie z bazą danych
        dbConnection.connect(dbPath);

        // Pobranie połączenia
        Connection connection = dbConnection.getConnection();

        // Wstawianie danych
        AccountManager accountManager = new AccountManager();
        Account accountValentyn = new Account(1, "Valentyn");
        Account accountNadiia = new Account(2, "Nadiia");
        Account accountGeralte = new Account(3, "Geralte");
        accountManager.deleteAccount(accountValentyn, connection);
        accountManager.deleteAccount(accountNadiia, connection);

        accountManager.register(accountValentyn, "1234", connection);
        accountManager.register(accountNadiia, "password", connection);
        accountManager.register(accountGeralte, "Ciri123", connection);

        if (accountManager.authenticate("Valentyn", "1234", connection)) {
            System.out.println("Valentyn logged in");
        }
        else {
            System.out.println("Valentyn con't log in");
        }

        if (accountManager.authenticate("Nadiia", "password", connection)) {
            System.out.println("Nadiia logged in");
        } else {
            System.out.println("Nadiia con't log in");
        }

        if (accountManager.authenticate("password", "1234", connection)) {
            System.out.println("password logged in");
        } else {
            System.out.println("password con't log in");
        }

        if (accountManager.authenticate("Geralte", "Triss2", connection)) {
            System.out.println("Geralte logged in");
        } else {
            System.out.println("Geralte con't log in");
        }

        if (accountManager.authenticate("Geralte", "Ciri123", connection)) {
            System.out.println("Geralte logged in");
        } else {
            System.out.println("Geralte con't log in");
        }

        dbConnection.disconnect();
    }
}
