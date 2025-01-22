package vttp.batchb.paf.day22_ws.restcontrollers;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import vttp.batchb.paf.day22_ws.models.Rsvp;
import vttp.batchb.paf.day22_ws.models.Exceptions.ResourceNotFoundException;
import vttp.batchb.paf.day22_ws.services.RsvpService;

@RestController
@RequestMapping("/api")
public class RsvpRController {
    
    @Autowired
    private RsvpService rsvpSvc;

    @GetMapping(path={"/rsvps/count"}, produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<Integer> countRsvps () {
        
        Integer count = rsvpSvc.countRsvps();
            
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(count);
    }

    @PutMapping(path={"/rsvp/{email}"}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateRsvpByEmail(@ModelAttribute Rsvp rsvpForm, @PathVariable String email) {

        Boolean updateRsvp = rsvpSvc.updateRsvpByEmail(rsvpForm,email);

        if (updateRsvp == true) 
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("update successful");

        throw new ResourceNotFoundException("email not found");

        //return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("email not found");
    }

    @PostMapping(path={"/rsvp"}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addRsvp(@ModelAttribute Rsvp rsvpForm) {

        Boolean addRsvp = rsvpSvc.addRsvp(rsvpForm);

        JsonObject json = Json.createObjectBuilder()
            .add("name", rsvpForm.getName())
            .add("email", rsvpForm.getEmail())
            .add("phone", rsvpForm.getPhone())
            .add("confirmation_date", rsvpForm.getConfirmationDate().toString())
            .add("comments", rsvpForm.getComments())
            .build();

        if (addRsvp == true) 
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(json.toString());
        return null;
    }


    @GetMapping(path={"/rsvp"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchRsvpByName( @RequestParam String name) {
        
        List<Rsvp> rsvps = rsvpSvc.getRsvpByName(name);

        if (rsvps.isEmpty()) {

            JsonObjectBuilder json = Json.createObjectBuilder();
            
            json.add("status_code","404")
                .add("message","rsvp for %s not found".formatted(name))
                .build();

            throw new ResourceNotFoundException(json.toString());

            // return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(json.toString());
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

    @GetMapping(path={"/rsvps"}, produces = MediaType.APPLICATION_JSON_VALUE)
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
