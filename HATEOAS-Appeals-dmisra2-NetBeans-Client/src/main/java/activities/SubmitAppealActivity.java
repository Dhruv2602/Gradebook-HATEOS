package activities;

import model.Appeal;
import model.AppealStatus;
import model.Identifier;
import repositories.AppealRepository;
import representation.AppealRepresentation;
import representation.GradebookUri;
import representation.Link;
import representation.Representation;

public class SubmitAppealActivity {
    public AppealRepresentation submitAppeal(Appeal appeal, GradebookUri gradebookUri)
    {
        appeal.setStatus(AppealStatus.SUBMITTED);
        Identifier identifier = gradebookUri.getId();
        AppealRepository.current().store(identifier, appeal);
        
        GradebookUri gradeUri = new GradebookUri(gradebookUri.getBaseUri() + "updategrade/" + identifier.toString());
        GradebookUri followUri = new GradebookUri(gradebookUri.getBaseUri() + "followup/" + identifier.toString());
        
        return new AppealRepresentation(appeal,
                new Link(Representation.RELATIONS_URI + "updategrade", gradeUri),
                new Link(Representation.RELATIONS_URI + "followup", followUri)
        );
    }
}
