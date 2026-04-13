ERROR_HANDLING- tema

CERINTA

1.	Creează o excepție proprie (Checked Exception):

○	Creează o clasă nouă InvalidUserDataException.java care moștenește Exception. Aceasta va fi folosită pentru a semnala date invalide la crearea unui utilizator.


2.	Creează o ierarhie de clase: 

○	O clasă de bază User cu proprietăți private String username și private int age.

○	O clasă AdminUser care moștenește (extends) User și adaugă o proprietate private String permissionLevel.


3.	Creează o clasă UserRepository:

○	Această clasă va avea o listă privată de utilizatori: private List<User> users = new ArrayList<>();.

○	Implementează o metodă public void addUser(User user) care adaugă un utilizator în listă.


4.	Implementează validarea și gestiunea excepțiilor:

○	Modifică metoda addUser astfel încât să arunce (throws) excepția ta InvalidUserDataException dacă una dintre următoarele condiții este adevărată:

■	user este null.

■	username-ul utilizatorului este null sau are mai puțin de 3 caractere.

■	age-ul utilizatorului este un număr negativ.

○	Adaugă o metodă public List<User> getUsers() care returnează lista de utilizatori.


5.	Creează o clasă Main pentru testare:

○	În metoda main, creează o instanță de UserRepository.

○	Încearcă să adaugi mai mulți utilizatori (valizi și invalizi), fiecare într-un bloc try-catch separat pentru a demonstra că programul nu se oprește la prima eroare.

■	Un User valid (ex: "testuser", 25).

■	Un AdminUser valid (ex: "admin", 30, "full_access").

■	Un User cu username prea scurt (ex: "ab", 22).

■	Un User cu age negativ (ex: "baduser", -5).

○	Blocul catch trebuie să prindă InvalidUserDataException și să afișeze un mesaj de eroare prietenos.

○	La final, după toate blocurile try-catch, afișează toți utilizatorii valizi care au fost adăugați cu succes în repository.


REZOLVARE

1. Folosim *public class InvalidUserDataException extends Exception* pentru a mosteni clasa *Exception*.
2. Folosim un constructor in clasa *User* pentru a trata erorile de user null si varsta invalida:

        if (username == null || username.isBlank()) {
            throw new InvalidUserDataException("Numele de utilizator nu poate fi gol.");
        }
        if (age < 0 || age > 120) {
            throw new InvalidUserDataException("Vârsta introdusă este invalidă: " + age);
        }
		
Suprascriem metoda *toString* pentru a afisa datele:
 
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';

Clasa *AdminUser* moștenește clasa *User* : *public class AdminUser extends User*.

Folosim un constructor pentru a trata permisiunea:

    public AdminUser(String username, int age, String permissionLevel) throws InvalidUserDataException {
        super(username, age);
        if (permissionLevel == null || permissionLevel.isBlank()) {
            throw new InvalidUserDataException("Nivelul de permisiune nu poate fi gol.");
        }
        this.permissionLevel = permissionLevel;
    }

De asemenea suprascriem metoda *toString* pentru a afisa datele:

    @Override
    public String toString() {
        return "AdminUser{" +
                "username='" + getUsername() + '\'' +
                ", age=" + getAge() +
                ", permissionLevel='" + permissionLevel + '\'' +
                '}';
    }

3-4.  Folosim clasa *UserRepository* care contine o lista de utilizatori si o metoda *addUser* care trateaza cazurile de utilizatori invalizi:

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
	
De asemenea metoda *getUsers* va afisa lista de utilizatori: 

    public List<User> getUsers() {
        return users;
    }
	
5. In clasa *Main* vom folosi o instanta *UserRepository*:

UserRepository repo = new UserRepository();

Folosim *try & catch* pentru a testa codul si a afisa scenariile dorite (Atat Testare Pozitiva, fara a "prinde" erorile, cat si Testare Negativa, unde ne asteptam ca erorile sa fie "aruncate"):

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
		

OUTPUT Terminal

    ✅ Utilizator adăugat cu succes: testuser
    ✅ Utilizator adăugat cu succes: admin
    ⚠️ Eroare: Username-ul este prea scurt → Numele de utilizator trebuie să aibă cel puțin 3 caractere.
    ⚠️ Eroare: Vârsta este invalidă → Vârsta introdusă este invalidă: -5
    ⚠️ Eroare: Utilizator null → Utilizatorul nu poate fi null.

    ----- UTILIZATORI VALIZI ADAUGAȚI -----
    Lista utilizatorilor:
     - User{username='testuser', age=25}
     - AdminUser{username='admin', age=30, permissionLevel='full_access'}