package su.kami.moyen.Model;

import reactor.util.annotation.Nullable;

public class User {
    private int id;
    private String name;
    private String contact;
    private double balance;
    @Nullable
    private String elsewhat;

    private int status;

    public User(String order){
        switch (order){
            case "initialRootUser":
                this.id = 0;
                this.name = "";
                this.contact = "";
                this.balance = 0d;
                this.elsewhat = "root user";
                return;
            case "initialBlankUser":
                this.name = "AnotherTestUser" + String.valueOf(Math.random()).substring(0, 3);
                this.contact = "";
                this.balance = 0d;
                this.elsewhat = "test";
                return;
            default:
                System.out.println("! @Internal User Class Violation: Null of Ordered Initialize. ");
                return;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": \"" + id +
                "\", \"name\": \"" + name +
                "\", \"contact\": \"" + contact +
                "\", \"balance\": \"" + balance +
                "\", \"status\": \"" + status +
                "\" }, ";
    }

    public User(){

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getElsewhat() {
        return elsewhat;
    }

    public void setElsewhat(String elsewhat) {
        this.elsewhat = elsewhat;
    }
}
