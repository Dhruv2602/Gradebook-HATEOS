package model;

import javax.xml.bind.annotation.XmlElement;
import representation.Representation;

public class Appeal
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
    
    public Appeal(String gradeItem, String appeal, String marks, String comments, AppealStatus status)
    {
        this.gradeItem = gradeItem;
        this.appeal = appeal;
        this.marks = marks;
        this.comments = comments;
        this.status = status;
    }
    
    public String getGradeItem()
    {
        return gradeItem;
    }
    
    public String getAppeal()
    {
        return appeal;
    }
    
    public String getMarks()
    {
        return marks;
    }
    
    public String getComments()
    {
        return comments;
    }
    
    public AppealStatus getStatus()
    {
        return status;
    }
    
    public void setGradeItem(String gradeItem)
    {
        this.gradeItem = gradeItem;
    }
    
    public void setStatus(AppealStatus status)
    {
        this.status = status;
    }
    
    public void setAppeal(String appeal)
    {
        this.appeal = appeal;
    }
    
    public void setMarks(String marks)
    {
        this.marks = marks;
    }
    
    public void setComments(String comments)
    {
        this.comments = comments;
    }
}
