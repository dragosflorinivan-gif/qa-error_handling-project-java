import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private List<User> users = new ArrayList<>();

    /**
     * Adaugă un utilizator în listă după validare.
     * Aruncă InvalidUserDataException dacă datele sunt invalide.
     */
    public void addUser(User user) throws InvalidUserDataException {
        if (user == null) {
            throw new InvalidUserDataException("Utilizatorul nu poate fi null.");
        }

        if (user.getUsername() == null || user.getUsername().length() < 3) {
            throw new InvalidUserDataException("Numele de utilizator trebuie să aibă cel puțin 3 caractere.");
        }

        if (user.getAge() < 0) {
            throw new InvalidUserDataException("Vârsta nu poate fi un număr negativ.");
        }

        users.add(user);
        System.out.println("✅ Utilizator adăugat cu succes: " + user.getUsername());
    }

    /**
     * Returnează lista de utilizatori.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * (Opțional) Afișează toți utilizatorii pentru testare.
     */
    public void printAllUsers() {
        if (users.isEmpty()) {
            System.out.println("Nu există utilizatori înregistrați.");
        } else {
            System.out.println("Lista utilizatorilor:");
            for (User u : users) {
                System.out.println(" - " + u);
            }
        }
    }
}