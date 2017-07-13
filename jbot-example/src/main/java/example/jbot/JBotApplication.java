package example.jbot;

import example.jbot.slack.SlackWebhooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.HttpClientErrorException;

import static java.lang.Thread.sleep;

@SpringBootApplication(scanBasePackages = {"me.ramswaroop.jbot", "example.jbot"})
public class JBotApplication implements CommandLineRunner {

    @Autowired
    private SlackWebhooks slackWebhooks;

    /**
     * Entry point of the application. Run this method to start the sample bots,
     * but don't forget to add the correct tokens in application.properties file.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(JBotApplication.class, args);
    }

    @Override
    public void run(String... args) throws InterruptedException {
        for(int i=0;i<=10;i++){
            System.out.printf(">>>>> Send pack: %d <<<<<<<< \n",i);
            try{
                slackWebhooks.sendPack();
            } catch (HttpClientErrorException ex){
                if(ex.getRawStatusCode()==429){
                    sleep(10*000);
                }
            }

        }
    }
}
