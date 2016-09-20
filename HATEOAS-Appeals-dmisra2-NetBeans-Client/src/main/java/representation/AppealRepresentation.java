package representation;

import activities.InvalidAppealException;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import model.Appeal;
import model.AppealStatus;

@XmlRootElement(name = "appeals", namespace = Representation.GRADEBOOK_NAMESPACE)
public class AppealRepresentation extends Representation
{
    @XmlElement(name = "gradeItem", namespace = Representation.GRADEBOOK_NAMESPACE)
    private String gradeItem;
    @XmlElement(name = "appeal", namespace = Representation.GRADEBOOK_NAMESPACE)
    private String appeal;
    @XmlElement(name = "marks", namespace = Representation.GRADEBOOK_NAMESPACE)
    private String marks;
    @XmlElement(name = "comments", namespace = Representation.GRADEBOOK_NAMESPACE)
    private String comments;
    @XmlElement(name = "status", namespace = Representation.GRADEBOOK_NAMESPACE)
    private AppealStatus status = AppealStatus.UNGRADED;

    public static AppealRepresentation fromXmlString(String xmlRepresentation) 
    {
        AppealRepresentation appealRepresentation = null;     
        try 
        {
            JAXBContext context = JAXBContext.newInstance(AppealRepresentation.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            appealRepresentation = (AppealRepresentation) unmarshaller.unmarshal(new ByteArrayInputStream(xmlRepresentation.getBytes()));
        }
        catch (Exception e)
        {
            throw new InvalidAppealException(e);
        }

        return appealRepresentation;
    }
    
    public AppealRepresentation()
    {
        System.out.print("");
    }
    public AppealRepresentation(Appeal appealObj, Link... links) 
    {
        try 
        {
            this.gradeItem = appealObj.getGradeItem();
            this.appeal = appealObj.getAppeal();
            this.comments = appealObj.getComments();
            this.marks = appealObj.getMarks();
            this.status = appealObj.getStatus();
            this.links = java.util.Arrays.asList(links);
        }
        catch (Exception ex)
        {
            throw new InvalidAppealException(ex);
        }
    }
    
    public static AppealRepresentation createResponseAppealRepresentation(Appeal appealObj, GradebookUri gradebookUri) {
        AppealRepresentation appealRepresentation = new AppealRepresentation(appealObj);
        
        GradebookUri appealUri = new GradebookUri(gradebookUri.getBaseUri() + "/updateappeal/" + gradebookUri.getId().toString());
        GradebookUri gradeUri = new GradebookUri(gradebookUri.getBaseUri() + "/creategrade/");
        GradebookUri submitUri = new GradebookUri(gradebookUri.getBaseUri() + "/submitappeal/" + gradebookUri.getId().toString());
        GradebookUri updateUri = new GradebookUri(gradebookUri.getBaseUri() + "/updategrade/" + gradebookUri.getId().toString());
        GradebookUri deleteUri = new GradebookUri(gradebookUri.getBaseUri() + "/deleteappeal/" + gradebookUri.getId().toString());
        GradebookUri followUri = new GradebookUri(gradebookUri.getBaseUri() + "/followup/" + gradebookUri.getId().toString());
        
        if(appealObj.getStatus() == AppealStatus.UNGRADED)
        {
            appealRepresentation = new AppealRepresentation(appealObj, new Link(RELATIONS_URI + "grade", gradeUri));
        }
        else if(appealObj.getStatus() == AppealStatus.GRADED)
        {
            appealRepresentation = new AppealRepresentation(appealObj, new Link(RELATIONS_URI + "appeal", appealUri));
        }
        else if(appealObj.getStatus() == AppealStatus.COMPOSED)
        {
            appealRepresentation = new AppealRepresentation(appealObj, new Link(RELATIONS_URI + "submit", submitUri), new Link(RELATIONS_URI + "delete", deleteUri));
        }
        else if(appealObj.getStatus() == AppealStatus.DELETED)
        {
            appealRepresentation = new AppealRepresentation(appealObj);
        }
        else if(appealObj.getStatus() == AppealStatus.SUBMITTED)
        {
            appealRepresentation = new AppealRepresentation(appealObj, new Link(RELATIONS_URI + "update", updateUri), new Link(RELATIONS_URI + "followup", followUri));
        }
        else if(appealObj.getStatus() == AppealStatus.REPLY_SENT)
        {
            appealRepresentation = new AppealRepresentation(appealObj);
        }
        return appealRepresentation;
    }
    
    @Override
    public String toString() {
        try
        {
            JAXBContext context = JAXBContext.newInstance(AppealRepresentation.class);
            Marshaller marshaller = context.createMarshaller();

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(this, stringWriter);

            return stringWriter.toString();
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);
        }
    }
    
    public Appeal getAppeal()
    {
        Appeal appealObj = new Appeal(gradeItem, appeal, marks, comments, status);
        
        return appealObj;
    }
    
    public Link getGradeLink() {
        return getLinkByName(RELATIONS_URI + "grade");
    }
    
    public Link getAppealLink() {
        return getLinkByName(RELATIONS_URI + "appeal");
    }
    
    public Link getSubmitLink() {
        return getLinkByName(RELATIONS_URI + "submit");
    }
    
    public Link getDeleteLink() {
        return getLinkByName(RELATIONS_URI + "delete");
    }
    
    public Link getUpdateLink() {
        return getLinkByName(RELATIONS_URI + "update");
    }
    
    public Link getFollowLink() {
        return getLinkByName(RELATIONS_URI + "followup");
    }
    
    public Link getSelfLink() {
        return getLinkByName("self");
    }
}