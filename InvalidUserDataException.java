// InvalidUserDataException.java
public class InvalidUserDataException extends Exception {

    // Constructor fără parametri
    public InvalidUserDataException() {
        super("Datele utilizatorului sunt invalide.");
    }

    // Constructor cu mesaj personalizat
    public InvalidUserDataException(String message) {
        super(message);
    }

    // Constructor cu mesaj și cauză
    public InvalidUserDataException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor doar cu cauză
    public InvalidUserDataException(Throwable cause) {
        super(cause);
    }
}