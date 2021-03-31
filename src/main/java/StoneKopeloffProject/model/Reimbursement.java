package StoneKopeloffProject.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;


@Entity
@Table(name = "Reimbursement")
public class Reimbursement {
    @GeneratedValue
    @Id
    private int id;
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


    public int getExpense_Value(expenseType expenseType) {
        switch (expenseType) {
            case TRAVEL:
                return expenseType.TRAVEL.ordinal();
            case TRAINING:
                return expenseType.TRAINING.ordinal();
            case ENTERTAINMENT:
                return expenseType.ENTERTAINMENT.ordinal();
            case GIFT:
                return expenseType.GIFT.ordinal();
            case CAR:
                return expenseType.CAR.ordinal();
            default:
                System.out.println("Not a valid expense");
                return -1;
        }
    }

    public enum expenseType {
        TRAVEL,
        TRAINING,
        ENTERTAINMENT,
        GIFT,
        CAR

    }


    public int getStatus(Status status) {
        switch (status) {
            case PENDING:
                return Status.PENDING.ordinal();
            case ACCEPTED:
                return Status.ACCEPTED.ordinal();
            case REJECTED:
                return Status.REJECTED.ordinal();
            default:
                System.out.println("Not a valid Status");
                return -1;
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
                "id=" + id +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", resolver=" + resolver +
                ", status_id=" + status_id +
                ", type_id=" + type_id +
                '}';
    }
}
