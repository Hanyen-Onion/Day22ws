package vttp.batchb.paf.day22_ws.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.batchb.paf.day22_ws.models.Rsvp;
import static vttp.batchb.paf.day22_ws.repositories.Query.*;

@Repository
public class RsvpRepository {
    
    @Autowired
    private JdbcTemplate template;

    public Boolean insertRsvp(Rsvp rsvp) {

        int rsvpInserted = template.update(SQL_ADD_OR_UPDATE_RSVP, 
            rsvp.getName(), 
            rsvp.getEmail(), 
            rsvp.getPhone(), 
            rsvp.getConfirmationDate(), 
            rsvp.getComments()
        );

        if (rsvpInserted > 0)
            return true;

        return false;
    }

    public List<Rsvp> retrieverRsvpByName(String name) {
        
        List<Rsvp> rsvps = new LinkedList<>();

        SqlRowSet rs = template.queryForRowSet(SQL_GET_RSVP_BY_NAME, name);

        while (rs.next()) {
            Rsvp rsvp = new Rsvp();
            rsvp.setId(rs.getInt("id"));
            rsvp.setName(rs.getString("name"));
            rsvp.setEmail(rs.getString("email"));
            rsvp.setPhone(rs.getString("phone"));
            rsvp.setConfirmationDate(rs.getDate("confirmation_date"));
            rsvp.setComments(rs.getString("text"));
        }

        return rsvps;
    } 

    public List<Rsvp> retrieveRsvpList() {

        List<Rsvp> rsvps = new LinkedList<>();

        SqlRowSet rs = template.queryForRowSet(SQL_GET_RSVP_LIST);

        while (rs.next()) {
            Rsvp rsvp = new Rsvp(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getDate("confirmation_date"),
                rs.getString("text")
            );

            rsvps.add(rsvp);
        }

        return rsvps;
    }
}
