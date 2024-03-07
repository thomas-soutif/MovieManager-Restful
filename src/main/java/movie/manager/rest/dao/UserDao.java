package movie.manager.rest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import movie.manager.rest.model.User;

public enum UserDao {
    instance;

    private Map<String, User> userDatabase = new HashMap<>();

    private UserDao() {
        User user1 = new User("user1", "password1");
        user1.setRole(User.Role.STAFF);
        userDatabase.put("user1", user1);

        User user2 = new User("user2", "password2");
        user2.setRole(User.Role.PROVIDER);
        userDatabase.put("user2", user2);

        User user3 = new User("user3", "password3");
        user3.setRole(User.Role.MEMBER);
        userDatabase.put("user3", user3);
    }

    public User getUser(String username) {
        return userDatabase.get(username);
    }

    public void addUser(User user) {
        userDatabase.put(user.getUsername(), user);
    }

    public boolean userExists(String username) {
        return userDatabase.containsKey(username);
    }
    
    public Map<String, User> getModel(){
        return userDatabase;
    }
    
    public List<User> getAllUsers(){
    	List<User> users = new ArrayList<User>();
        users.addAll(instance.getModel().values());
        return users;
    }

}
