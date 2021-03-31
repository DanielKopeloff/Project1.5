package StoneKopeloffProject.ui;

import StoneKopeloffProject.model.Reimbursement;
import StoneKopeloffProject.model.User;
import StoneKopeloffProject.service.UserService;

public class UserFriendlyPrint {
    public static String printReimbursement(Reimbursement r, boolean showauthor) {
        StringBuilder returnvalue = new StringBuilder();
        if (showauthor) {
            returnvalue.append("|Author: ");
            returnvalue.append(UserService.getInstance().getUserById(r.getAuthor()));
            returnvalue.append(" ");
        }
        returnvalue.append("|Amount: ");
        returnvalue.append(r.getAmount());
        returnvalue.append(" |Type: ");
        switch (r.getType_id()) {
            case 0:
                returnvalue.append("Travel");
                break;
            case 1:
                returnvalue.append("Training");
                break;
            default:
                returnvalue.append("Other");
        }
        returnvalue.append(" |Description: ");
        returnvalue.append(r.getDescription());
        returnvalue.append(" |Submitted Date: ");
        returnvalue.append(r.getSubmitted());
        if (r.getStatus_id() < 1) {
            returnvalue.append(" |Not resolved");
        } else {
            returnvalue.append(" |Resolved on: ");
            returnvalue.append(r.getResolved());
            returnvalue.append(" by ");
            returnvalue.append(UserService.getInstance().getUserById(r.getResolver()));
            returnvalue.append(" |Status: ");
            if (r.getStatus_id() == 1) {
                returnvalue.append("Accepted");
            } else {
                returnvalue.append("Rejected");
            }
        }
        return returnvalue.toString();
    }
}
