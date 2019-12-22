//package br.com.idtrust.icustodia.api.login.service;
//
//import java.io.StringWriter;
//
//import javax.print.attribute.standard.Destination;
//
//import org.aspectj.bridge.Message;
//import org.springframework.boot.autoconfigure.web.ResourceProperties.Content;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SendEmailService {
//
//    private static final String EMAIL_TEMPLATE_FILE = "/templates/notification.vm";
//
//    private AmazonSimpleEmailService amazonSES;
//
//    public SendEmailService(AmazonSimpleEmailService amazonSES) {
//        this.amazonSES = amazonSES;
//    }
//
//    public void sendNotification(String api, String email, String title) {
//        VelocityContext velocityContext = new VelocityContext();
//        velocityContext.put("api", api);
//
//        sendEmail(amazonSES,
//                getFormattedEmail(EMAIL_TEMPLATE_FILE, velocityContext), email,
//                title);
//
//    }
//
//    private String getFormattedEmail(String templateFileName, Context context) {
//        Template template = Velocity.getTemplate(templateFileName, "UTF-8");
//        StringWriter stringWriter = new StringWriter();
//        template.merge(context, stringWriter);
//        return stringWriter.toString();
//
//    }
//
//    private void sendEmail(AmazonSimpleEmailService amazonSimpleEmailService,
//            String body, String receiver, String subject) {
//
//        SendEmailRequest request = new SendEmailRequest()
//                .withDestination(new Destination().withToAddresses(receiver))
//                .withMessage(new Message()
//                        .withBody(new Body().withHtml(new Content()
//                                .withCharset("ISO-8859-1").withData(body)))
//                        .withSubject(new Content().withCharset("UTF-8")
//                                .withData(subject)))
//                .withSource("noreply@idtrust.com.br");
//
//        amazonSimpleEmailService.sendEmail(request);
//    }
//
//}
