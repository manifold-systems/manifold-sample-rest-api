package restapi;

import com.despegar.http.client.*;
import com.despegar.sparkjava.test.SparkServer;
import manifold.util.JsonUtil;
import org.junit.Test;
import restapi.User;
import spark.servlet.SparkApplication;

import java.time.LocalDate;

import static restapi.UserDao.SUPER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static restapi.User.Gender.*;

/**
 * HTTP tests for the {@link User} REST API
 */
public class UserServerTest implements SparkApplication {
  @org.junit.ClassRule
  public static SparkServer<UserServerTest> testServer = new SparkServer<>(UserServerTest.class, 4567);

  public void init() {
    UserServer.main(new String[0]);
  }

  public void destroy() {
    UserServer.stop();
  }

  @Test
  public void GET_All() throws HttpClientException {
    GetMethod get = testServer.get("/users", false);
    HttpResponse res = testServer.execute(get);
    assertEquals(JsonUtil.toJson(UserDao.getAll()), new String(res.body()));
  }

  @Test
  public void GET_Id() throws HttpClientException {
    GetMethod get = testServer.get("/users/$SUPER", false);
    HttpResponse res = testServer.execute(get);
    assertEquals(JsonUtil.toJson(UserDao.findUser(SUPER)), new String(res.body()));
  }

  @Test
  public void POST_User() throws HttpClientException {
    String id = "scott";
    User newUser = User.builder(id, "mypassword", "Scott")
      .withDob(LocalDate.of(1980, 7, 4))
      .withGender(male)
      .build();
    PostMethod post = testServer.post("/users", newUser.write().toJson(), false);
    HttpResponse res = testServer.execute(post);
    assertEquals(newUser.write().toJson(), new String(res.body()));
  }

  @Test
  public void PUT_User() throws HttpClientException {
    String id = "bob";
    User bob = User.builder(id, "mypassword", "Bob")
      .withDob(LocalDate.of(1982, 7, 4))
      .withGender(male)
      .build();
    PutMethod put = testServer.put("/users/$id", bob.write().toJson(), false);
    HttpResponse res = testServer.execute(put);
    assertEquals(bob.write().toJson(), new String(res.body()));
  }

  @Test
  public void DELETE_Id() throws HttpClientException {
    User foo = User.builder("foo", "blah", "Foo").build();
    UserDao.createUser(foo);
    DeleteMethod delete = testServer.delete("/users/${foo.getId()}", false);
    HttpResponse res = testServer.execute(delete);
    assertEquals(JsonUtil.toJson(foo), new String(res.body()));
  }
}