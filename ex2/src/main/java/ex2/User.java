package ex2;

public class User {
    @FriendlyName(key = "FriendlyName annotation for first name ")
    private String firstName;
    @FriendlyName(key = "FriendlyName annotation for last name ")
    private String lastName;
    @FriendlyName(key = "FriendlyName annotation for father's name ")
    private String fathersName;
    private int age;
    @EmailValid(key = "EmailValid annotation for email with pattern text@text.text")
    private String email;

    public User(String firstName, String lastName, String fathersName, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fathersName = fathersName;
        this.age = age;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fathersName='" + fathersName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}


