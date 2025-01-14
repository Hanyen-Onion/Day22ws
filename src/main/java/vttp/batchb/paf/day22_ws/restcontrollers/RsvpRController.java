package vttp.batchb.paf.day22_ws.restcontrollers;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import vttp.batchb.paf.day22_ws.models.Rsvp;
import vttp.batchb.paf.day22_ws.services.RsvpService;

@RestController
@RequestMapping("/api")
public class RsvpRController {
    
    @Autowired
    private RsvpService rsvpSvc;

    @PostMapping(path={"/rsvp"}, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addRsvp( @ModelAttribute Rsvp rsvpForm) {

        
        return null;
    }


    @GetMapping(path={"/rsvp"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchRsvpByName( @RequestParam String name) {
        
        List<Rsvp> rsvps = rsvpSvc.getRsvpByName(name);

        if (rsvps.isEmpty()) {

            JsonObjectBuilder objBuilder = Json.createObjectBuilder();
            
            objBuilder.add("status_code","404")
                .add("message","rsvp for %s not found".formatted(name))
                .build();

            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(objBuilder.build().toString());
        }

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        rsvps.stream().map(obj -> 
            Json.createObjectBuilder()
                .add("id", obj.getId())
                .add("name",obj.getName())
                .add("email", obj.getEmail())
                .add("phone", obj.getPhone())
                .add("confirmation_date", obj.getConfirmationDate().toString())
                .add("comments", obj.getComments())
                .build()
        )
        .forEach(j -> arrBuilder.add(j));

        return ResponseEntity.ok(arrBuilder.build().toString());
    }

    @GetMapping(path={"/rsvps"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRsvps() {

        List<Rsvp> rsvps = rsvpSvc.getRsvps();

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        rsvps.stream().map(obj -> 
            Json.createObjectBuilder()
            .add("id", obj.getId())
            .add("name",obj.getName())
            .add("email", obj.getEmail())
            .add("phone", obj.getPhone())
            .add("confirmation_date", obj.getConfirmationDate().toString())
            .add("comments", obj.getComments())
            .build()
        )
        .forEach(j -> arrBuilder.add(j));

        return ResponseEntity.ok(arrBuilder.build().toString());
    }
}
