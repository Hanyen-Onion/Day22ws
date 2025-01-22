package vttp.batchb.paf.day22_ws.repositories;

public class Query {
    
    public static final String SQL_GET_RSVP_LIST = "SELECT * from rsvp";

    public static final String SQL_GET_RSVP_BY_NAME = "SELECT * from rsvp where name like ?";
    
    public static final String SQL_ADD_OR_UPDATE_RSVP = """
                                                        INSERT into rsvp (name, email, phone, confirmation_date, comments)
                                                        VALUES (?,?,?,?,?) AS v ON DUPLICATE KEY 
                                                        UPDATE name = v.name, email = v.email, phone = v.phone,
                                                        confirmation_date = v.confirmation_date, comments = v.comments
                                                        """;

    public static final String SQL_UPDATE_RSVP = """
                                                UPDATE rsvp SET name = ?, email = ?, phone = ?, 
                                                confirmation_date = ?, comments = ?
                                                WHERE email = ?
                                                """;

    public static final String SQL_COUNT_RSVPS = """
                                                SELECT distinct count(*) from rsvp
                                                """;

}
