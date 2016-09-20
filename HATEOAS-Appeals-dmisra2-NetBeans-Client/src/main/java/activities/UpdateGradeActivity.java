package activities;

import model.Appeal;
import model.AppealStatus;
import model.Identifier;
import repositories.AppealRepository;
import representation.AppealRepresentation;
import representation.GradebookUri;

public class UpdateGradeActivity {
    public AppealRepresentation updateGrade(Appeal appeal, GradebookUri gradebookUri)
    {
        appeal.setStatus(AppealStatus.REPLY_SENT);
        Identifier identifier = gradebookUri.getId();
        AppealRepository.current().store(identifier, appeal);
        
        return new AppealRepresentation(appeal);
    }
}
