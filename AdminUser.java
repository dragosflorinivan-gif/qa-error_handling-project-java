public class AdminUser extends User {
    private String permissionLevel;

    // Constructor
    public AdminUser(String username, int age, String permissionLevel) throws InvalidUserDataException {
        super(username, age);
        if (permissionLevel == null || permissionLevel.isBlank()) {
            throw new InvalidUserDataException("Nivelul de permisiune nu poate fi gol.");
        }
        this.permissionLevel = permissionLevel;
    }

    // Getter
    public String getPermissionLevel() {
        return permissionLevel;
    }

    // Setter
    public void setPermissionLevel(String permissionLevel) throws InvalidUserDataException {
        if (permissionLevel == null || permissionLevel.isBlank()) {
            throw new InvalidUserDataException("Nivelul de permisiune nu poate fi gol.");
        }
        this.permissionLevel = permissionLevel;
    }

    // Suprascriere toString()
    @Override
    public String toString() {
        return "AdminUser{" +
                "username='" + getUsername() + '\'' +
                ", age=" + getAge() +
                ", permissionLevel='" + permissionLevel + '\'' +
                '}';
    }
}