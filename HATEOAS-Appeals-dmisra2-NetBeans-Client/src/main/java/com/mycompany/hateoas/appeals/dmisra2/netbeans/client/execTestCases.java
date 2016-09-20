package com.mycompany.hateoas.appeals.dmisra2.netbeans.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import java.net.URI;
import model.Appeal;
import model.AppealStatus;
import representation.AppealRepresentation;

public class execTestCases
{
    private static final String GRADEBOOK_MEDIA_TYPE = "application/vnd-cse564-appeals+xml";
    private static final String ENTRY_POINT_URI = "http://localhost:8080/HATEOAS-Appeals-dmisra2-NetBeans-Server-2/webapi/appeal/";
    
    public static void main(String[] args) throws Exception
    {
        URI serviceUri = new URI(ENTRY_POINT_URI);
        happyCase(serviceUri);
        abandonedCase(serviceUri);
        forgottenCase(serviceUri);
        badStartCase(serviceUri);
        badIdCase(serviceUri);
    }
    
    public static void happyCase(URI serviceUri) throws Exception
    {
        //POST GRADES
        String str = serviceUri.toString() + "creategrade/";
        URI newUri = new URI(str);
        System.out.println(String.format("About to start Happy Case test. Posting grades at [%s] via POST", newUri.toString()));
        Appeal appeal = new Appeal("Midterm", "", "59", "Bad Performance", AppealStatus.GRADED);
        Client client = Client.create();
        AppealRepresentation appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).post(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Grades posted at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Appeal Link: " + appealRepresentation.getAppealLink().getUri().toString());
        System.out.println();
        
        //POST APPEAL
        newUri = new URI(appealRepresentation.getAppealLink().getUri().toString());
        System.out.println(String.format("About to post an appeal. Posting appeal at [%s] via PUT", newUri.toString()));
        appeal.setAppeal("Calculation mistake.");
        appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).put(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Appeal placed at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Update Link: " + appealRepresentation.getSelfLink().getUri().toString());
        System.out.println();
        
        //UPDATE APPEAL
        newUri = new URI(appealRepresentation.getSelfLink().getUri().toString());
        System.out.println(String.format("About to update the appeal. Updating appeal at [%s] via PUT", newUri.toString()));
        appeal.setAppeal("Calculation mistake. Please recheck.");
        appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).put(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Appeal updated at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Submit Link: " + appealRepresentation.getSubmitLink().getUri().toString());
        System.out.println("Delete Link: " + appealRepresentation.getDeleteLink().getUri().toString());
        System.out.println();
        
        //SUBMIT APPEAL
        newUri = new URI(appealRepresentation.getSubmitLink().getUri().toString());
        System.out.println(String.format("About to submit the appeal. Submitting appeal at [%s] via PUT", newUri.toString()));
        appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).put(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Appeal submitted at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Update Link: " + appealRepresentation.getUpdateLink().getUri().toString());
        System.out.println();
        
        //SEND REPLY AND UPDATE GRADES
        
        newUri = new URI(appealRepresentation.getUpdateLink().getUri().toString());
        System.out.println(String.format("About to send a reply to the appeal. Posting reply at [%s] via PUT", serviceUri.toString()));
        appeal.setComments("Mistake fixed. 10 points added.");
        appeal.setMarks("69");
        appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).put(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Reply sent at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Status Code: " + appealRepresentation.getAppeal().getStatus().toString());
        
        System.out.println();
        System.out.println();
    }
    
    public static void abandonedCase(URI serviceUri) throws Exception
    {
        //POST GRADES
        String str = serviceUri.toString() + "creategrade/";
        URI newUri = new URI(str);
        System.out.println(String.format("About to start Abandoned Case test. Posting grades at [%s] via POST", newUri.toString()));
        Appeal appeal = new Appeal("Midterm", "", "59", "Bad Performance", AppealStatus.GRADED);
        Client client = Client.create();
        AppealRepresentation appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).post(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Grades posted at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Appeal Link: " + appealRepresentation.getAppealLink().getUri().toString());
        System.out.println();
        
        //POST APPEAL
        newUri = new URI(appealRepresentation.getAppealLink().getUri().toString());
        System.out.println(String.format("About to post an appeal. Posting appeal at [%s] via PUT", newUri.toString()));
        appeal.setAppeal("Calculation mistake.");
        appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).put(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Appeal placed at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Delete Link: " + appealRepresentation.getDeleteLink().getUri().toString());
        System.out.println();
        
        //DELETE APPEAL
        newUri = new URI(appealRepresentation.getDeleteLink().getUri().toString());
        System.out.println(String.format("About to delete the appeal. Deleting appeal at [%s] via PUT", newUri.toString()));
        appeal.setAppeal("");
        appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).put(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Appeal deleted at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Status Code: " + appealRepresentation.getAppeal().getStatus().toString());
        System.out.println();
        System.out.println();
    }
    
    public static void forgottenCase(URI serviceUri) throws Exception
    {
        //POST GRADES
        String str = serviceUri.toString() + "creategrade/";
        URI newUri = new URI(str);
        System.out.println(String.format("About to start Forgotten Case test. Posting grades at [%s] via POST", newUri.toString()));
        Appeal appeal = new Appeal("Midterm", "", "59", "Bad Performance", AppealStatus.GRADED);
        Client client = Client.create();
        AppealRepresentation appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).post(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Grades posted at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Appeal Link: " + appealRepresentation.getAppealLink().getUri().toString());
        System.out.println();
        
        //POST APPEAL
        newUri = new URI(appealRepresentation.getAppealLink().getUri().toString());
        System.out.println(String.format("About to post an appeal. Posting appeal at [%s] via PUT", newUri.toString()));
        appeal.setAppeal("Calculation mistake.");
        appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).put(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Appeal placed at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Update Link: " + appealRepresentation.getSelfLink().getUri().toString());
        System.out.println();
        
        //UPDATE APPEAL
        newUri = new URI(appealRepresentation.getSelfLink().getUri().toString());
        System.out.println(String.format("About to update the appeal. Updating appeal at [%s] via PUT", newUri.toString()));
        appeal.setAppeal("Calculation mistake. Please recheck.");
        appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).put(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Appeal updated at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Submit Link: " + appealRepresentation.getSubmitLink().getUri().toString());
        System.out.println("Update Link: " + appealRepresentation.getSelfLink().getUri().toString());
        System.out.println();
        
        //SUBMIT APPEAL
        newUri = new URI(appealRepresentation.getSubmitLink().getUri().toString());
        System.out.println(String.format("About to submit the appeal. Submitting appeal at [%s] via PUT", newUri.toString()));
        appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).put(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Appeal submitted at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Follow up Link: " + appealRepresentation.getFollowLink().getUri().toString());
        System.out.println();
        
        //FOLLOW UP
        newUri = new URI(appealRepresentation.getFollowLink().getUri().toString());
        System.out.println(String.format("About to follow up the appeal. Following up at [%s] via PUT", newUri.toString()));
        ClientResponse cr = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).put(ClientResponse.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Followed up at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Status Code: " + appealRepresentation.getAppeal().getStatus().toString());
        System.out.println();
        System.out.println();
    }
    
    public static void badStartCase(URI serviceUri) throws Exception
    {
        //POST GRADES
        String str = serviceUri.toString() + "creategrade/";
        URI newUri = new URI(str);
        System.out.println(String.format("About to start Bad Start Case test. Posting grades at [%s] via POST", newUri.toString()));
        Appeal appeal = new Appeal("Midterm", "", "59", "Bad Performance", AppealStatus.GRADED);
        Client client = Client.create();
        AppealRepresentation appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).post(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Grades posted at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println();
        
        //POST APPEAL
        try
        {
            newUri = new URI(appealRepresentation.getAppealLink().getUri().toString() + "24help");
            System.out.println(String.format("About to post an appeal. Posting appeal at [%s] via PUT", newUri.toString()));
            appeal.setAppeal("Calculation mistake.");
            appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).put(AppealRepresentation.class, new AppealRepresentation(appeal));
            System.out.println(String.format("Appeal placed at [%s]", newUri.toString()));
            System.out.println("Payload: " + appealRepresentation.toString());
            System.out.println("Status Code: " + appealRepresentation.getAppeal().getStatus().toString());
            System.out.println();
            System.out.println();
        }
        catch(Exception e)
        {
            ClientResponse cr = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).put(ClientResponse.class, new AppealRepresentation(appeal));
            System.out.println("Could not process request. Response code: " + cr.getStatus());
            System.out.println();
            System.out.println();
        }
    }
    
    public static void badIdCase(URI serviceUri) throws Exception
    {
        //POST GRADES
        String str = serviceUri.toString() + "creategrade/";
        URI newUri = new URI(str);
        System.out.println(String.format("About to start Bad ID Case test. Posting grades at [%s] via POST", newUri.toString()));
        Appeal appeal = new Appeal("Midterm", "", "59", "Bad Performance", AppealStatus.GRADED);
        Client client = Client.create();
        AppealRepresentation appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).post(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Grades posted at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Appeal Link: " + appealRepresentation.getAppealLink().getUri().toString());
        System.out.println();
        
        //POST APPEAL
        newUri = new URI(appealRepresentation.getAppealLink().getUri().toString());
        System.out.println(String.format("About to post an appeal. Posting appeal at [%s] via PUT", newUri.toString()));
        appeal.setAppeal("Calculation mistake.");
        appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).put(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Appeal placed at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Update Link: " + appealRepresentation.getSelfLink().getUri().toString());
        System.out.println();
        
        //UPDATE APPEAL
        newUri = new URI(appealRepresentation.getSelfLink().getUri().toString());
        System.out.println(String.format("About to update the appeal. Updating appeal at [%s] via PUT", newUri.toString()));
        appeal.setAppeal("Calculation mistake. Please recheck.");
        appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).put(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Appeal updated at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Submit Link: " + appealRepresentation.getSubmitLink().getUri().toString());
        System.out.println("Update Link: " + appealRepresentation.getSelfLink().getUri().toString());
        System.out.println();
        
        //SUBMIT APPEAL
        newUri = new URI(appealRepresentation.getSubmitLink().getUri().toString());
        System.out.println(String.format("About to submit the appeal. Submitting appeal at [%s] via PUT", newUri.toString()));
        appealRepresentation = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).put(AppealRepresentation.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Appeal submitted at [%s]", newUri.toString()));
        System.out.println("Payload: " + appealRepresentation.toString());
        System.out.println("Follow up Link: " + appealRepresentation.getFollowLink().getUri().toString());
        System.out.println();
        
        //FOLLOW UP
        
        newUri = new URI(appealRepresentation.getFollowLink().getUri().toString() + "29smiteme");
        System.out.println(String.format("About to follow up the appeal. Following up at [%s] via PUT", newUri.toString()));
        ClientResponse cr = client.resource(newUri).accept(GRADEBOOK_MEDIA_TYPE).type(GRADEBOOK_MEDIA_TYPE).put(ClientResponse.class, new AppealRepresentation(appeal));
        System.out.println(String.format("Could not process request. Response code: %d", cr.getStatus()));
        System.out.println();
        System.out.println();
    }
}
