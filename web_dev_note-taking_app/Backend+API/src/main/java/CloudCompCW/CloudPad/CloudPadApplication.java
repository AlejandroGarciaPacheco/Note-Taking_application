package CloudCompCW.CloudPad;

import CloudCompCW.UserManager.UserManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
public class CloudPadApplication {

    static UserManager userManager;

    public static void main(String[] args)
    {
        UserManager.getInstance();
        SpringApplication.run(CloudPadApplication.class, args);
    }

    @GetMapping("/")
    public String hello()
    {
        return "Hello world!";
    }

}
