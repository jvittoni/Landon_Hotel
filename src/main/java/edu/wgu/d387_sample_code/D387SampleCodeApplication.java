package edu.wgu.d387_sample_code;

import edu.wgu.d387_sample_code.time.TimeZoneConvert;
import edu.wgu.d387_sample_code.translation.WelcomeMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import edu.wgu.d387_sample_code.translation.WelcomeMessageController;

import java.util.Locale;

@SpringBootApplication
public class D387SampleCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(D387SampleCodeApplication.class, args);

		// Thread For English Welcome Message
		WelcomeMessage welcomeMessageEnglish = new WelcomeMessage(Locale.US);
		Thread englishWelcomeThread = new Thread(welcomeMessageEnglish);
		englishWelcomeThread.start();

		// Thread For French Welcome Message
		WelcomeMessage welcomeMessageFrench = new WelcomeMessage(Locale.CANADA_FRENCH);
		Thread frenchWelcomeThread = new Thread(welcomeMessageFrench);
		frenchWelcomeThread.start();

		// Print Time Convert
		System.out.println("Time Zones: " + TimeZoneConvert.getTime());
	}
}
