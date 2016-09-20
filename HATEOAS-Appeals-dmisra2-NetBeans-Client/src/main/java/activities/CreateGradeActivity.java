package activities;

import model.Appeal;
import model.AppealStatus;
import model.Identifier;
import repositories.AppealRepository;
import representation.AppealRepresentation;
import representation.GradebookUri;
import representation.Link;
import representation.Representation;


public class CreateGradeActivity 
{
    public AppealRepresentation create(Appeal appealObj, GradebookUri requestUri)
    {
        appealObj.setStatus(AppealStatus.GRADED);
        Identifier identifier = AppealRepository.current().store(appealObj);
        
        GradebookUri appealUri = new GradebookUri(requestUri.getBaseUri() + identifier.toString() + "/");
        GradebookUri updateUri = new GradebookUri(requestUri.getBaseUri() + "updateappeal/" + identifier.toString() + "/");
        
        return new AppealRepresentation(appealObj, 
                new Link(Representation.RELATIONS_URI + "get", appealUri),
                new Link(Representation.RELATIONS_URI + "composeappeal", updateUri));
    }
}
