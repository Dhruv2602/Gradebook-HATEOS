package activities;

import model.Appeal;
import model.Identifier;
import repositories.AppealRepository;
import representation.AppealRepresentation;
import representation.GradebookUri;
import representation.Link;
import representation.Representation;

public class CreateAppealActivity {
    public AppealRepresentation create(Appeal appeal, GradebookUri requestUri)
    {
        Identifier identifier = AppealRepository.current().store(appeal);
        
        GradebookUri submitUri = new GradebookUri(requestUri.getBaseUri() + identifier.toString());
        GradebookUri updateUri = new GradebookUri(requestUri.getBaseUri() + identifier.toString());
        GradebookUri deleteUri = new GradebookUri(requestUri.getBaseUri() + identifier.toString());
        
        return new AppealRepresentation(appeal,
                new Link(Representation.RELATIONS_URI + "submit", submitUri),
                new Link(Representation.RELATIONS_URI + "delete", deleteUri),
                new Link(Representation.RELATIONS_URI + "update", updateUri)
        );
    }
}
