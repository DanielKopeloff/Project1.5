package StoneKopeloffProject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class numgenerator {

    @Id
    @GeneratedValue
    private int id;

    public numgenerator(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


