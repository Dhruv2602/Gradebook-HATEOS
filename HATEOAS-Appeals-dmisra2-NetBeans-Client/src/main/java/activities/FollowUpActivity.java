package activities;

import model.Appeal;
import model.AppealStatus;
import model.Identifier;
import representation.AppealRepresentation;
import representation.GradebookUri;
import representation.Link;
import representation.Representation;

public class FollowUpActivity {
    public AppealRepresentation followUp(Appeal appeal, GradebookUri gradebookUri)
    {
        appeal.setStatus(AppealStatus.SUBMITTED);
        Identifier identifier = gradebookUri.getId();
        GradebookUri gradeUri = new GradebookUri(gradebookUri.getBaseUri() + "updategrade/" + identifier.toString());
        return new AppealRepresentation(appeal, new Link(Representation.RELATIONS_URI + "updategrade", gradeUri));
    }
}
