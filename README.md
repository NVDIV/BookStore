# Book Store Account Management and Authentication System

This project implements a basic account management and authentication system in Java. It uses an SQLite database to store user credentials and provides functionalities such as account registration, deletion, and authentication.

## Project Structure

- `Main`: The main entry point of the application, where accounts are created, deleted, and authenticated.
- `Account`: Represents a user account with a unique ID and username.
- `AccountManager`: Handles operations related to account management, such as registering and authenticating users.
- `DatabaseConnection`: Manages the connection to the SQLite database.

## Features

- **Account Registration**: Allows creating new accounts with a username and password.
- **Account Deletion**: Allows deleting existing accounts from the database.
- **Account Authentication**: Authenticates users based on username and password.

## Setup

1. Clone this repository.

2. Open the project in your Java IDE.

3. Add the SQLite JDBC driver (download the latest `.jar` file from https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc) to your project's classpath.

4. Update the database path in `Main.java`:
    ```java
    String dbPath = "D:\\Java\\test.db";
    ```

5. Ensure that the `test.db` SQLite database exists in the specified path, or change `dbPath` to the desired location.

## Usage

Run the `Main` class to execute the application. 

The main operations include:
- **Registering new accounts**:
    ```java
    accountManager.register(account, "password", connection);
    ```
- **Deleting accounts**:
    ```java
    accountManager.deleteAccount(account, connection);
    ```
- **Authenticating users**:
    ```java
    accountManager.authenticate("username", "password", connection);
    ```

The output in the console will indicate whether each account could log in successfully or if authentication failed.

## Example Output

```plaintext
Valentyn logged in
Nadiia logged in
password can't log in
Geralte can't log in
Geralte logged in
```

This output shows which users successfully authenticated with their credentials.

## Contact
If you’d like to reach out, feel free to connect with me:
- [LinkedIn](https://www.linkedin.com/in/nadiia-rybak-5092b8336)
- [Email](mailto:nvdiv5@gmail.com)

Thanks for visiting!
