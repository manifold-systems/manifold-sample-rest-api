package test;

import manifold.json.rt.api.IJsonList;
import manifold.json.rt.api.Requester;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import restapi.User;
import restapi.UserServer;

import java.time.LocalDate;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static restapi.User.Gender.*;

/**
 * HTTP tests for the {@link User} REST API
 */
public class UserServerTest {
    private static final Requester<User> req = User.request("http://localhost:4567/users");

    @BeforeClass
    public static void init() {
        UserServer.main(new String[0]);
    }

    @AfterClass
    public static void destroy() {
        UserServer.stop();
    }

    @Test
    public void GET_All() {
        // Get all Users via HTTP GET
        IJsonList<User> users = req.getMany();
        assertFalse(users.isEmpty());
    }

    @Test
    public void POST_and_GET_Id() {
        User user = User.builder("scott", "mypassword", "Scott")
                .withGender(male)
                .build();
        Object o = req.postOne(user);
        User result = (User) req.getOne("/${user.getId()}");
        assertEquals(result, user);
    }

    @Test
    public void PUT_User() {
        String id = "bob";
        User bob = User.builder(id, "mypassword", "Bob")
                .withDob(LocalDate.of(1982, 7, 4))
                .withGender(male)
                .build();
        User result = (User) req.putOne("/$id", bob);
        assertEquals(bob, result);
    }

    @Test
    public void POST_and_DELETE_Id() {
        User foo = User.create("foo", "blah", "Foo");
        Object o = req.postOne(foo);
        User result = (User) req.delete("/${foo.getId()}");
        assertEquals(result, foo);
    }
}