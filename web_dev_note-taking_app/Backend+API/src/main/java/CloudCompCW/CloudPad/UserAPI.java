package CloudCompCW.CloudPad;

import CloudCompCW.NotePadManager.NotepadManager;
import CloudCompCW.UserManager.User;
import CloudCompCW.UserManager.UserManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserAPI {

    @GetMapping(value = "/usersList")
    public static ArrayList<User> userList()
    {
        return UserManager.getUsers();
    }

    @PutMapping(value = "/login")

    public static boolean login(Integer id)
    {
        System.out.println("logging in  user of id: " + id);
        if (UserManager.login(id)) NotepadManager.getInstance();
        else return false;
        return true;
    }

    @PutMapping(value = "/addUser")
    public static void addUser(String firstname, String lastname)
    {
        System.out.printf("adding user named %s %s%n", firstname, lastname);
        UserManager.addUser(firstname, lastname);
    }

    @DeleteMapping(value = "/deleteUser")
    public static void deleteUser(Integer id)
    {
        System.out.println("deleteing user of id :" + id);
        UserManager.deleteUser(id);
    }

    @GetMapping(value = "/userByID")
    public static User getUserByID(Integer id)
    {
        System.out.println("getting user of id: " + id);
        return UserManager.getUserById(id);
    }

    @GetMapping(value = "/currentUser")
    public static User getCurrentUser()
    {
        System.out.println("getting current user of id: " + getCurrentUser());
        return UserManager.getCurrentUser();
    }

    @PostMapping(value = "/reset")
    public static void reset()
    {
        System.out.println("resetting user");
        UserManager.reset();
        NotepadManager.reset();
    }

    @GetMapping("")
    public static String baseResponse()
    {
        System.out.println("base response user");
        return "<h1>User API endpoint</h1>";
    }
}

