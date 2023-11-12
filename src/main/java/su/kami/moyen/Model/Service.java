package su.kami.moyen.Model;

public class Service {
    private int id;
    private String name;
    private String describe;
    private double cost;
    private String plus;

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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getPlus() {
        return this.plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }
}
