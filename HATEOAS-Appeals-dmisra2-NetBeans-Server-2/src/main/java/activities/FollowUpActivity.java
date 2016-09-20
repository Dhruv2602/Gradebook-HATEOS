package activities;

import model.Appeal;
import model.AppealStatus;
import model.Identifier;
import repositories.AppealRepository;
import representation.AppealRepresentation;
import representation.GradebookUri;
import representation.Link;
import representation.Representation;

public class FollowUpActivity {
    public AppealRepresentation followUp(GradebookUri gradebookUri)
    {
        Identifier identifier = gradebookUri.getId();
        Appeal appeal = AppealRepository.current().get(identifier);
        appeal.setStatus(AppealStatus.SUBMITTED);
        GradebookUri gradeUri = new GradebookUri(gradebookUri.getBaseUri() + "updategrade/" + identifier.toString() + "/");
        return new AppealRepresentation(appeal, new Link(Representation.RELATIONS_URI + "update", gradeUri));
    }
}
