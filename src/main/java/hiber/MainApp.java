package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            UserService userService = context.getBean(UserService.class);

            userService.add(new User("Igor", "Kuznetsov", "kotamadeo@gmail.com", new Car("Porshe", 911)));
            userService.add(new User("Anna", "Ivanova", "ivanova@list.com", new Car("Honda", 121)));
            userService.add(new User("Alexey", "Efremov", "Sneg1203@mail.ru", new Car("Lamborgini", 12)));
            userService.add(new User("Ivan", "Petrov", "petrov28@rambler.ru", new Car("Vaz", 2108)));

            List<User> users = userService.listUsers();
            for (User user : users) {
                System.out.println(user);
                System.out.println("*********************************");
            }

            try {
                System.out.println(userService.getUserByCar("Porshe", 911));
                System.out.println(userService.getUserByCar("Lamborgini", 12));
                System.out.println(userService.getUserByCar("BMW", 5));
            } catch (NoResultException e) {
                System.out.println("Исключение NoResultException, так как БМВ как сущность не может быть найдена для запроса");
            }


        }
    }
}
