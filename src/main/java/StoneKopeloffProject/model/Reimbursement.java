package StoneKopeloffProject.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;


@Entity
@Table(name = "reimbursement")
public class Reimbursement {
    @GeneratedValue
    @Id
    private int id;
    @Column(unique = true ,nullable = false)
    private int ReimbursementID;
    @Column
    private float amount;
    private Timestamp submitted;
    private Timestamp resolved;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private User author;
    @OneToOne(cascade = CascadeType.ALL)
    private User resolver;
    private int status_id;
    private int type_id;

    public Reimbursement() {
        //No-arg constructor
    }

    public Reimbursement(int id, float amount, Timestamp submitted, Timestamp resolved, String description, User author, User resolver, int status_id, int type_id) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.status_id = status_id;
        this.type_id = type_id;
    }


    public Reimbursement(float amount, String description, User author, expenseType type_id) {
        this.amount = amount;
        this.submitted = Timestamp.from(Instant.now());
        this.description = description;
        this.author = author;
        this.status_id = Status.PENDING.ordinal();
        this.type_id = type_id.ordinal();
    }

    public int getReimbursementID() {
        return ReimbursementID;
    }

    public void setReimbursementID(int reimbursementID) {
        ReimbursementID = reimbursementID;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getResolver() {
        return resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }


    public static expenseType getExpense_Value(int typeID) {
        switch (typeID) {
            case 0:
                return expenseType.TRAVEL;
            case 1:
                return expenseType.TRAINING;
            case 2:
                return expenseType.ENTERTAINMENT;
            case 3:
                return expenseType.GIFT;
            case 4:
                return expenseType.CAR;
            case 5:
                return expenseType.OTHER;
            default:
                System.out.println("Not a valid expense");
                return null;
        }
    }

    public enum expenseType {
        TRAVEL,
        TRAINING,
        ENTERTAINMENT,
        GIFT,
        CAR,
        OTHER

    }


    public Status getStatus(int status) {
        switch (status) {
            case 0:
                return Status.PENDING;
            case 1:
                return Status.ACCEPTED;
            case 2:
                return Status.REJECTED;
            default:
                System.out.println("Not a valid Status");
                return null;
        }
    }

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                " ReimbursementID=" + ReimbursementID +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", resolver=" + resolver +
                ", status_id=" + getStatus(status_id) +
                ", type_id=" + getExpense_Value(type_id) +
                '}';
    }
}
