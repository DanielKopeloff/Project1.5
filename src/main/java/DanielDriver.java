import StoneKopeloffProject.dao.ReimbursementDao;
import StoneKopeloffProject.model.Reimbursement;
import StoneKopeloffProject.model.User;
import StoneKopeloffProject.service.ReimbursementService;
import StoneKopeloffProject.service.UserService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;

public class DanielDriver {

    public static void main(String[] args) {


        /*User u1 = new User("John", "Yoko", "john", "lenon", "Nohead@beatles.com", User.Role.AUTHOR.ordinal());

        User u2 = new User("George", "guitar", "George", "Harrison", "fan@beatles.com", User.Role.AUTHOR.ordinal());


        User u3 = new User("SrPaul", "Sir", "Paul", "Mccartney", "best@beatles.com", User.Role.RESOLVER.ordinal());

        User u4 = new User("DaStar", "Drums", "Ringo", "Star", "Backseat@beatles.com", User.Role.RESOLVER.ordinal());

        //Reimbursement r1 = new Reimbursement(234234f , Timestamp.from(Instant.now()) ,Timestamp.from(Instant.now()) ,"Just doing some things" ,  u1, u2 , Reimbursement.Status.ACCEPTED ,Reimbursement.expenseType.TRAVEL);

        Reimbursement r2 = new Reimbursement(234234f, "Wanted to impress the cilent", u3, Reimbursement.expenseType.TRAVEL);
        Reimbursement r3 = new Reimbursement(324f, "Wanted to impress the cilent", u3, Reimbursement.expenseType.TRAVEL);
        Reimbursement r4 = new Reimbursement(2645f, "Wanted to impress the cilent", u4, Reimbursement.expenseType.ENTERTAINMENT);
        Reimbursement r5 = new Reimbursement(100f, "Wanted to impress the cilent", u4, Reimbursement.expenseType.GIFT);


        //ReimbursementService reimbursementService = new ReimbursementService();
//        reimbursementService.addReimbursement(r2);
//        reimbursementService.addReimbursement(r3);
//        reimbursementService.addReimbursement(r4);
//        reimbursementService.addReimbursement(r5);
        reimbursementService.updateReimbursement(r5 ,u3 , Reimbursement.Status.ACCEPTED);
        System.out.println(r5.getId());

        //UserService userService = new UserService();
//        userService.createNewUser(u1);
//        userService.createNewUser(u2);
//        userService.createNewUser(u3);
//        userService.createNewUser(u4);


//        reimbursementService.fetchAllReimbursements();*/
    }
}
