package restapi;

import restapi.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A simple, in-memory data access class for the {@link User} REST API.
 */
public class UserDao {
  private static final Map<String, User> USERS = new ConcurrentHashMap<>();
  public static final String SUPER = "super";

  public static List<User> getAll() {
    return USERS.values().toList();
  }

  public static User findUser(String id) {
    return USERS.get(id);
  }

  public static User createUser(User user) {
    if( USERS.containsKey(user.getId())) {
      throw new IllegalArgumentException("A user with id '${user.getId()}' already exists.");
    }
    USERS.put(user.getId(), user);
    return user;
  }

  public static User updateUser(String id, User user) {
    USERS.put(id, user);
    return user;
  }

  public static User deleteUser(String id) {
    if (SUPER.equals(id)) {
      throw new IllegalArgumentException("Cannot delete '$id'");
    }
    return USERS.remove(id);
  }

  public static void init() {
    // initialize with 'super' user
    UserDao.createUser(User.create(SUPER, "password", SUPER));
  }
}
