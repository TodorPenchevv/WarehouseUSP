package app.orm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ROLES")
public class Role {
    @Id
    private int id;
    @Enumerated(EnumType.STRING)
    private Roles role;

    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<User>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Role(int id, Roles role) {
        this.id = id;
        this.role = role;
    }

    public Role() {
    }
}
