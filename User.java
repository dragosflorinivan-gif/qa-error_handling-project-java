public class User {
    private String username;
    private int age;

    // Constructor
    public User(String username, int age) throws InvalidUserDataException {
        if (username == null || username.isBlank()) {
            throw new InvalidUserDataException("Numele de utilizator nu poate fi gol.");
        }
        if (age < 0 || age > 120) {
            throw new InvalidUserDataException("Vârsta introdusă este invalidă: " + age);
        }
        this.username = username;
        this.age = age;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    // Setters
    public void setUsername(String username) throws InvalidUserDataException {
        if (username == null || username.isBlank()) {
            throw new InvalidUserDataException("Numele de utilizator nu poate fi gol.");
        }
        this.username = username;
    }

    public void setAge(int age) throws InvalidUserDataException {
        if (age < 0 || age > 120) {
            throw new InvalidUserDataException("Vârsta introdusă este invalidă: " + age);
        }
        this.age = age;
    }

    // Metodă pentru afișarea datelor
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}