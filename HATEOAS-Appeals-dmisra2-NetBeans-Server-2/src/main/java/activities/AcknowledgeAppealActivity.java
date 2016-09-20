package activities;

import model.Appeal;
import model.AppealStatus;
import model.Identifier;
import repositories.AppealRepository;
import representation.AppealRepresentation;
import representation.GradebookUri;
import representation.Link;
import representation.Representation;

public class AcknowledgeAppealActivity {
    public AppealRepresentation ack(Appeal appeal, GradebookUri requestUri)
    {
        Identifier identifier = requestUri.getId();
        appeal.setStatus(AppealStatus.ACKNOWLEDGED);
        
        GradebookUri submitUri = new GradebookUri(requestUri.getBaseUri() + "submitappeal/" + identifier.toString());
        
        return new AppealRepresentation(appeal,
                new Link(Representation.RELATIONS_URI + "submit", submitUri)
        );
    }
}
