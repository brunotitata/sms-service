package br.com.sms;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmsServiceApplication {

    public static void main(String[] args) {

	Thread thread = new Thread(new Runnable() {

	    @Override
	    public void run() {
		while (true) {
		    try {
			Thread.sleep(600000L);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }

		    System.out.println("Verificado: " + LocalDateTime.now());

		}

	    }
	});
	thread.start();

	SpringApplication.run(SmsServiceApplication.class, args);
    }

}
