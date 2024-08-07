
# WESTERN GOVERNORS UNIVERSITY 
## D387 – ADVANCED JAVA
Welcome to Advanced Java! This is an opportunity for students to write multithreaded object-oriented code using Java frameworks and determine how to deploy software applications using cloud services.

<hr>

## TRACKED CHANGES
\\


### B.  Modify the Landon Hotel scheduling application for localization and internationalization by doing the following:

1. Install the Landon Hotel scheduling application in your integrated development environment (IDE). Modify the Java classes of application to display a welcome message by doing the following:
    - a. Build resource bundles for both English and French (languages required by Canadian law). Include a welcome message in the language resource bundles.
    - b. Display the welcome message in both English and French by applying the resource bundles using a different thread for each language.

    - _*Note: You may use Google Translate for the wording of your welcome message._


2. Modify the front end to display the price for a reservation in currency rates for U.S. dollars ($), Canadian dollars (C$), and euros (€) on different lines.

    - _*Note: It is not necessary to convert the values of the prices._


3. Display the time for an online live presentation held at the Landon Hotel by doing the following:
    - a. Write a Java method to convert times between eastern time (ET), mountain time (MT), and coordinated universal time (UTC) zones.
    - b. Use the time zone conversion method from part B3a to display a message stating the time in all three time zones in hours and minutes for an online, live presentation held at the Landon Hotel. The times should be displayed as ET, MT, and UTC.


<br>

### Part B1:

<br>
File Name: translation.properties, translation_en_US.properties, translation_fr_CA.properties
<br>Edit: Created Resource Bundle for english and french


<br>

File Name: translation_en_US.properties
<br>Line: 1
<br>Edit: Added welcome message in English
<br>Code:
```
welcome=Welcome to the Landon Hotel!
```

<br>

File Name:  translation_fr_CA.properties
<br>Line: 1
<br>Edit: Added welcome message in French
<br>Code:
```
welcome=Bienvenue à l'hôtel Landon!
```

<br>

File Name: WelcomeMessage.java
<br>Line: 1 - 21
<br>Edit: Created file and created class for welcome messages
<br>Code:
```
package edu.wgu.d387_sample_code.translation;

import java.util.Locale;
import java.util.ResourceBundle;

public class WelcomeMessage implements Runnable {
    Locale locale;

    //Constructor
    public WelcomeMessage(Locale locale) {
        this.locale = locale;
    }

    public String getWelcomeMessage() {
        ResourceBundle bundle = ResourceBundle.getBundle("translation", locale);
        return bundle.getString("welcome");
    }

    @Override
    public void run() {}
}
```

<br>

File Name: WelcomeMessageController.java
<br>Line: 1 - 25
<br>Edit: Created file and created controller for welcome messages
<br>Code:
```
package edu.wgu.d387_sample_code.translation;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class WelcomeMessageController {

    @GetMapping("/welcome")
    public ResponseEntity<String> displayWelcome(@RequestParam("lang") String lang) {
        Locale locale = Locale.forLanguageTag(lang);
        WelcomeMessage welcomeMessage = new WelcomeMessage(locale);

        return new ResponseEntity<String> (welcomeMessage.getWelcomeMessage(), HttpStatus.OK);
    }
}
```

<br>

File Name: WelcomeMesssge.java
<br>Line: 21
<br>Edit: Added thread verification for welcome messages
<br>Code:
```
@Override
    public void run() {
        System.out.println("Thread verification: " + getWelcomeMessage());
    }
```

<br>

File Name: D387SampleCodeApplication.java
<br>Line: 18 - 26
<br>Edit: Created threads for welcome messages
<br>Code:
```
// Thread For English Welcome Message
		WelcomeMessage welcomeMessageEnglish = new WelcomeMessage(Locale.US);
		Thread englishWelcomeThread = new Thread(welcomeMessageEnglish);
		englishWelcomeThread.start();

		// Thread For French Welcome Message
		WelcomeMessage welcomeMessageFrench = new WelcomeMessage(Locale.CANADA_FRENCH);
		Thread frenchWelcomeThread = new Thread(welcomeMessageFrench);
		frenchWelcomeThread.start();

```

<br>

File Name: app.component.ts
<br>Line: 19 - 20, 37 - 39
<br>Edit: Created http request to show welcome messages
<br>Code:
```
// Welcome messages
  welcomeMessageEnglish$!: Observable<string>
  welcomeMessageFrench$!: Observable<string>
…
// Welcome Messages HTTP request
      this.welcomeMessageEnglish$ = this.httpClient.get(this.baseURL + '/welcome?lang=en-US', {responseType: 'text'})
      this.welcomeMessageFrench$ = this.httpClient.get(this.baseURL + '/welcome?lang=fr-CA', {responseType: 'text'})
```

<br>

File Name: app.component.html
<br>Line: 25 - 30
<br>Edit: Called in welcome messages to show on the hotel page
<br>Code:
```
<div>
      <br>
      <h1>{{welcomeMessageEnglish$ | async}}</h1>
      <h1>{{welcomeMessageFrench$ | async}}</h1>
      <br>
    </div>
```

<br>

### Part B2:

<br>
File Name: app.component.ts
<br>Line: 64, 115 - 119
<br>Edit: Added Canadian dollar and Euro room reservation prices
<br>Code:

```
this.rooms.forEach( room => { room.priceCAD = room.price; room.priceEUR = room.price})
…
  // Canadian Dollar 
  priceCAD:string;

// Euro
priceEUR:string;
```

<br>

File Name: app.component.html
<br>Line: 84 - 85
<br>Edit: Display Canadian dollar and euro prices on hotel page
<br>Code: 
```
<strong>Price: C${{room.priceCAD}}</strong><br>
<strong>Price: €{{room.priceEUR}}</strong>
```


<br>


### Part B3:

<br>
File Name: TimeZoneConvert.java
<br>Line: 1 - 23
<br>Edit: Created file and created java method to convert times with ET, MT, and UTC time zones
<br>Code:

```
package edu.wgu.d387_sample_code.time;

import org.springframework.web.bind.annotation.CrossOrigin;
import java.time.*;
import java.time.format.DateTimeFormatter;

@CrossOrigin(origins = "http://localhost:4200")```

public class TimeZoneConvert {
   public static String getTime(){
        ZonedDateTime zdt = ZonedDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        
         ZonedDateTime et = zdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        ZonedDateTime mt = zdt.withZoneSameInstant(ZoneId.of("America/Denver"));
        ZonedDateTime utc = zdt.withZoneSameInstant(ZoneId.of("UCT"));
        
   String times = et.format(timeFormatter) + "ET, " + mt.format(timeFormatter) + "MT, " + utc.format(timeFormatter) + "UTC";
        
   return times;
    }
}
```


<br>

File Name: TimeZoneConvertController.java
<br>Line: 
<br>Edit: Created file and created controller to use time zone converter in the presentation message
<br>Code: 
```
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
```

<br>

File Name: app.component.ts
<br>Line: 23, 45
<br>Edit: Created http request for presentation message with time zones
<br>Code: 
```
presentationAnnouncement$!: Observable<String>
…
this.presentationAnnouncement$ = this.httpClient.get(this.baseURL + '/presentation', {responseType: 'text'})
```

<br>

File Name: app.component.html
<br>Line: 32 - 36
<br>Edit: Display presentation message with et, mt, and utc on the site
<br>Code: 
```
<div class="scene" id="presentation">
        <h2>Announcement:</h2>
        <h1>{{presentationAnnouncement$ | async}}</h1>
        <br>
      </div>
```

<br>

<hr>

<br>