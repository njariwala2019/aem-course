package com.myapp.web.core.servlets;

import com.adobe.acs.commons.email.EmailService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(service = {Servlet.class})
@SlingServletResourceTypes(
        resourceTypes = "sling/servlet/default",
        selectors = "sendemail",
        extensions = "json",
        methods = HttpConstants.METHOD_GET
)
@ServiceDescription("SEND SAMPLE EMAIL SERVLET")
public class EmailServlet extends SlingSafeMethodsServlet {

    @Reference
    private EmailService emailService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        Map<String, String> emailParams = new HashMap<>();
        emailParams.put("senderEmailAddress","nikunj.jariwala@gmail.com");
        emailParams.put("senderName","David Smith");
        emailParams.put("ccrecipient","nikunj_jariwala@hotmail.com");
        emailParams.put("bccrecipient","nikunj_jariwala@hotmail.com");
        emailParams.put("message","hello there, How are you??");
        emailParams.put("recipientName","Nikunj Jariwala");


        emailService.sendEmail("/etc/notification/email/emailTemplate.txt", emailParams, "nikunj_jariwala@hotmail.com");

    }
}
