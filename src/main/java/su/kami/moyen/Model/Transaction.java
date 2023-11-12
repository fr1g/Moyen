package su.kami.moyen.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Transaction {
    private int id;
    private User user;
    private Service service;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date date; //TODO if not working change to long.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setGenerateDate(long generateDate) {
        this.date = new Date(generateDate);
    }
}
