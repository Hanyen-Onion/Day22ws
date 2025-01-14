package vttp.batchb.paf.day22_ws.repositories;

public class Query {
    
    public static final String SQL_GET_RSVP_LIST = "SELECT * from rsvp";

    public static final String SQL_GET_RSVP_BY_NAME = "SELECT * form rsvp where name = %?%";
    
    public static final String SQL_ADD_OR_UPDATE_RSVP = "INSERT into rsvp (name, email, phone, confirmation_date, comments)" +
                                    " VALUES (?,?,?,?,?) AS v" + " ON DUPLICATE KEY UPDATE" + 
                                    " name = v.name, email = v.email, phone = v.phone," + 
                                    " confirmation_date = v.confirmation_date, comments = v.comments";

}
