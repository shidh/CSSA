package controllers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import play.libs.Mail;
import play.mvc.Mailer;

public class Mails extends Mailer {

    public static void sendEmail(final String toAddress, final String subject, final String msg) throws EmailException {
    	ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            public String call() throws Exception{
                try {
                	SimpleEmail e_mail = new SimpleEmail();
    				e_mail.setDebug(true);
    				e_mail.setFrom("munich.cssa@gmail.com");
    				e_mail.setSubject(subject);
    				e_mail.setMsg(msg);
    				e_mail.addTo(toAddress);
    				Mail.send(e_mail);
                    return "Sending email to user successfully!";
                }
                catch(Exception e) {
                    throw new Exception("Callable terminated with Exception!"); // call method throw exception
                }
            }
        });
        executor.execute(future);

    }

}
