package activities;

import model.Appeal;
import model.Identifier;
import repositories.AppealRepository;
import representation.AppealRepresentation;
import representation.GradebookUri;

public class ReadAppealActivity
{
    public AppealRepresentation retrieveByUri(GradebookUri gradebookUri) 
    {
        Identifier identifier  = gradebookUri.getId();
        
        Appeal appeal = AppealRepository.current().get(identifier);
        
        if(appeal == null) {
            throw new NoSuchAppealException();
        }
        
        return AppealRepresentation.createResponseAppealRepresentation(appeal, gradebookUri);
    }
}
