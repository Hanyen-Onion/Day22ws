package vttp.batchb.paf.day22_ws.models.Exceptions;

import java.sql.Date;

public class ApiError {

    private int status;
    private String message;
    private Date timeStamp;  
    private String url;
    
    public ApiError(int status, String message, Date timeStamp, String url) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
        this.url = url;
    }

    public int getStatus() {    return status;}
    public void setStatus(int status) {    this.status = status;}
    
    public String getMessage() {    return message;}
    public void setMessage(String message) {    this.message = message;}
    
    public Date getTimeStamp() {    return timeStamp;}
    public void setTimeStamp(Date timeStamp) {    this.timeStamp = timeStamp;}
    
    public String getUrl() {    return url;}
    public void setUrl(String url) {    this.url = url;}  
}
