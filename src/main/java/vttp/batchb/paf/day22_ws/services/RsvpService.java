package vttp.batchb.paf.day22_ws.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.batchb.paf.day22_ws.models.Rsvp;
import vttp.batchb.paf.day22_ws.repositories.RsvpRepository;

@Service
public class RsvpService {
    
    @Autowired
    private RsvpRepository rsvpRepo;

    public int countRsvps() {
        int count = rsvpRepo.numOfRsvps();
        return count;
    }

    public Boolean updateRsvpByEmail(Rsvp rsvp, String email) {
        return rsvpRepo.updateRsvpByEmail(rsvp, email);
    }

    public Boolean addRsvp(Rsvp rsvp) {
        return rsvpRepo.insertRsvp(rsvp);
    }

    public List<Rsvp> getRsvpByName(String name) {
        
        List<Rsvp> rsvpsByName = rsvpRepo.retrieverRsvpByName(name);

        return rsvpsByName;
    }

    public List<Rsvp> getRsvps() {

        List<Rsvp> rsvps = rsvpRepo.retrieveRsvpList();

        return rsvps;
    }
}
