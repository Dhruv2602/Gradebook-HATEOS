package activities;

import model.Appeal;
import model.AppealStatus;
import model.Identifier;
import repositories.AppealRepository;
import representation.AppealRepresentation;
import representation.GradebookUri;
import representation.Link;
import representation.Representation;

public class UpdateAppealActivity {
    public AppealRepresentation updateGrade(Appeal appeal, GradebookUri gradebookUri)
    {
        appeal.setStatus(AppealStatus.REPLY_SENT);
        Identifier identifier = gradebookUri.getId();
        AppealRepository.current().store(identifier, appeal);
        
        GradebookUri gradeUri = new GradebookUri(gradebookUri.getBaseUri() + identifier.toString());
        
        return new AppealRepresentation(appeal,
                new Link(Representation.SELF_REL_VALUE, gradeUri)
        );
    }
    
    public AppealRepresentation updateAppeal(Appeal appeal, GradebookUri gradebookUri)
    {
        appeal.setStatus(AppealStatus.COMPOSED);
        Identifier identifier = gradebookUri.getId();
        AppealRepository.current().store(identifier, appeal);
        
        GradebookUri submitUri = new GradebookUri(gradebookUri.getBaseUri() + "submitappeal/" + identifier.toString());
        GradebookUri deleteUri = new GradebookUri(gradebookUri.getBaseUri() + "deleteappeal/" + identifier.toString());
        GradebookUri updateUri = new GradebookUri(gradebookUri.getBaseUri() + "updateappeal/" + identifier.toString());
        
        return new AppealRepresentation(appeal,
                new Link(Representation.RELATIONS_URI + "submit", submitUri),
                new Link(Representation.RELATIONS_URI + "delete", deleteUri),
                new Link(Representation.SELF_REL_VALUE, updateUri)
        );
    }
    
    public AppealRepresentation deleteAppeal(Appeal appeal, GradebookUri gradebookUri)
    {
        appeal.setStatus(AppealStatus.DELETED);
        Identifier identifier = gradebookUri.getId();
        AppealRepository.current().store(identifier, appeal);
        
        return new AppealRepresentation(appeal);
    }
}
