import com.solutions.denisovich.config.SpringConfig;
import com.solutions.denisovich.service.UserService;
import com.solutions.denisovich.service.implementations.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = (UserServiceImpl) context.getBean("userServiceImpl");
        System.out.println(userService.findAll());
    }
}


