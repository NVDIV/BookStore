package org.example.auth;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountManager {
    public void register(Account account, String password, Connection connection) {
        if (connection != null) {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            String insertSQL = "INSERT INTO users (id, name, password) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertSQL)) {
                statement.setInt(1, account.id());
                statement.setString(2, account.name());
                statement.setString(3, hashedPassword);
                statement.executeUpdate();
                System.out.println("User has been registered successfully.");
            } catch (SQLException e) {
                System.err.println("Failed to register user: " + e.getMessage());
            }
        }
    }

    public boolean authenticate(String name, String password, Connection connection) {
        if (connection != null) {
            String selectSQL = "SELECT password FROM users WHERE name = ?";
                try (PreparedStatement statement = connection.prepareStatement(selectSQL)) {
                    statement.setString(1, name);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            String hashedPassword = resultSet.getString("password");
                            return BCrypt.checkpw(password, hashedPassword);
                        }
                    } catch (SQLException e) {
                        System.err.println("Failed to authenticate user: " + e.getMessage());
                    }
                } catch (SQLException e) {
                    System.err.println("Failed to authenticate user: " + e.getMessage());
                }
            }
        return false;
    }

    public Account getAccount(String name, Connection connection) {
        if (connection != null) {
            String selectSQL = "SELECT id, name FROM users WHERE name = ?";
            try (PreparedStatement statement = connection.prepareStatement(selectSQL)) {
                statement.setString(1, name);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int accountId = resultSet.getInt("id");
                        String accountName = resultSet.getString("name");
                        return new Account(accountId, accountName);
                    } else {
                        System.out.println("User not found");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Failed to select user: " + e.getMessage());
            }
        }
        return null;
    }

    public Account getAccount(int id, Connection connection) {
        if (connection != null) {
            String selectSQL = "SELECT id, name FROM users WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(selectSQL)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int accountId = resultSet.getInt("id");
                        String accountName = resultSet.getString("name");
                        return new Account(accountId, accountName);
                    } else {
                        System.out.println("User not found");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Failed to select user: " + e.getMessage());
            }
        }
        return null;
    }

    public void deleteAccount(Account account, Connection connection) {
        if (connection != null) {
            String deleteSQL = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
                statement.setInt(1, account.id());
                statement.executeUpdate();
                System.out.println("User has been deleted.");
            } catch (java.sql.SQLException e) {
                System.err.println("Failed to delete user: " + e.getMessage());
            }
        }
    }
}
