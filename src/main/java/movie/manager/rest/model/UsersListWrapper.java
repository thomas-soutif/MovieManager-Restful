package movie.manager.rest.model;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
public class UsersListWrapper {
    private List<User> users;

    public UsersListWrapper() {
    }

    public UsersListWrapper(List<User> users) {
        this.users = users;
    }
    
    @XmlElement(name="user")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
