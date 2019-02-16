package restapi;

import manifold.api.json.IJsonList;
import manifold.api.json.Requester;
import java.time.LocalDate;

import static restapi.User.Gender.male;

/**
 * Demonstrates using the {@code request()} method for JSON REST API clients.
 */
public class UserClient {
  public static void main(String[] args) {
    Requester<User> req = User.request("http://localhost:4567/users");

    // Get all Users via HTTP GET
    IJsonList<User> users = req.getMany();

    // Add a User with HTTP POST
    User user = User.builder("scott", "mypassword", "Scott")
      .withGender(male)
      .build();
    req.postOne(user);

    // Get a User with HTTP GET
    String id = user.getId();
    user = req.getOne("/$id");

    // Update a User with HTTP PUT
    user.setDob(LocalDate.of(1980, 7, 7));
    req.putOne("/$id", user);

    // Delete a User with HTTP DELETE
    req.delete("/$id");
  }
}
