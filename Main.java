public class Main {
    public static void main(String[] args) {
        // Creăm o instanță de UserRepository
        UserRepository repo = new UserRepository();

        // TC1: User valid
        try {
            User user1 = new User("testuser", 25);
            repo.addUser(user1);
        } catch (InvalidUserDataException e) {
            System.out.println("⚠️ Eroare la adăugarea utilizatorului valid: " + e.getMessage());
        }

        // TC2: AdminUser valid
        try {
            AdminUser admin = new AdminUser("admin", 30, "full_access");
            repo.addUser(admin);
        } catch (InvalidUserDataException e) {
            System.out.println("⚠️ Eroare la adăugarea adminului: " + e.getMessage());
        }

        // TC3: User cu username scurt
        try {
            User userInvalid1 = new User("ab", 22);
            repo.addUser(userInvalid1);
        } catch (InvalidUserDataException e) {
            System.out.println("⚠️ Eroare: Username-ul este prea scurt → " + e.getMessage());
        }

        // TC4: User cu vârstă negativă
        try {
            User userInvalid2 = new User("baduser", -5);
            repo.addUser(userInvalid2);
        } catch (InvalidUserDataException e) {
            System.out.println("⚠️ Eroare: Vârsta este invalidă → " + e.getMessage());
        }

        // TC5: User null
        try {
            repo.addUser(null);
        } catch (InvalidUserDataException e) {
            System.out.println("⚠️ Eroare: Utilizator null → " + e.getMessage());
        }

        // ✅ Afișăm toți utilizatorii validați
        System.out.println("\n----- UTILIZATORI VALIZI ADAUGAȚI -----");
        repo.printAllUsers();
    }
}