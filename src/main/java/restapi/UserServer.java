package restapi;

import manifold.json.rt.Json;
import spark.Spark;

import static spark.Spark.*;

/**
 * A simple REST API for the {@link User} JSON Schema type.
 * <p/>
 * This example demonstrates how with Manifold your JSON Schema files are your API. Your JSON Schema files
 * are the <i>Single Source of Truth</i>, no generated classes to keep in sync!.
 * <p/>
 * Learn more about building
 * <a href="http://manifold.systems/docs.html#json-and-json-schema">JSON Schema & YAML APIs</a> with Manifold.
 */
public class UserServer {
  public static void main(String[] args) {
    port(4567);
    UserDao.init();

    // GET all Users
    get("/users", (req, res) -> UserDao.getAll(), Json::toJson);

    // GET User by Id
    get("/users/:id", (req, res) -> {
      String id = req.params(":id");
      User user = UserDao.findUser(id); // <~~~ The User.json file **is** the User type! No code gen, no POJOs.
      if (user != null) {
        return user.write().toJson(); // <~~~ Manifold JSON Schema types provide a powerful fluent API!
      }
      res.status(400);
      return ResponseError.create("No user with id '$id' found") // <~~~ String interpolation!
        .write().toJson();
    });

    // POST new User
    post("/users", (req, res) ->
      UserDao.createUser(User.load().fromJson(req.body())).write().toJson());

    // Update existing User
    put("/users/:id", (req, res) ->
      UserDao.updateUser(req.params(":id"), User.load().fromJson(req.body())).write().toJson());

    // Delete User by Id
    delete("/users/:id", (req, res) -> {
      String id = req.params(":id");
      User user = UserDao.deleteUser(id);
      if (user != null) {
        return user.write().toJson();
      }
      res.status(400);
      return ResponseError.create("No user with id '$id' found").write().toJson();
    });

    // Error response for IllegalStateException
    exception(IllegalArgumentException.class, (e, req, res) -> {
      res.status(400);
      res.body(ResponseError.create(e.getMessage()).write().toJson()); // <~~~ The ResponseError.json file!
    });

    after((req, res) -> res.type("application/json"));
  }

  public static void stop() {
    Spark.stop();
  }
}