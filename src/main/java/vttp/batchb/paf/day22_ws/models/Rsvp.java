package vttp.batchb.paf.day22_ws.models;

import java.sql.Date;

public class Rsvp {

    private int id;
    private String name;
    private String email;
    private String phone;
    private Date confirmationDate;
    private String comments;

    //constructor
    public Rsvp () {};
    
    public Rsvp(int id, String name, String email, String phone, Date confirmationDate, String comments) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.confirmationDate = confirmationDate;
        this.comments = comments;
    }

    //getter setter
    public int getId() {    return id;}
    public void setId(int id) {    this.id = id;}

    public String getName() {    return name;}
    public void setName(String name) {    this.name = name;}

    public String getEmail() {    return email;}
    public void setEmail(String email) {    this.email = email;}
    
    public String getPhone() {    return phone;}
    public void setPhone(String phone) {    this.phone = phone;}
    
    public Date getConfirmationDate() {    return confirmationDate;}
    public void setConfirmationDate(Date confirmationDate) {    this.confirmationDate = confirmationDate;}
    
    public String getComments() {    return comments;}
    public void setComments(String comments) {    this.comments = comments;}

    
}
