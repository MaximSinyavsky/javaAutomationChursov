package dto;

public class User {
    String firstName;
    String secondName;
    int age;
    public User (String firstName, String secondName, int age){
        this.firstName=firstName;
        this.secondName=secondName;
        this.age=age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }
    public int getAge(){
        return age;
    }
}
