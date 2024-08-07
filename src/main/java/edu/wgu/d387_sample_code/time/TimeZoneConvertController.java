package edu.wgu.d387_sample_code.time;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class TimeZoneConvertController {
    @GetMapping("/presentation")
    public ResponseEntity<String> presentationAnnouncement() {
        String displayAnnouncement = "The Landon Hotel is hosting a live, online presentation at " + TimeZoneConvert.getTime();
        return new ResponseEntity<String>(displayAnnouncement, HttpStatus.OK);
    }
}
