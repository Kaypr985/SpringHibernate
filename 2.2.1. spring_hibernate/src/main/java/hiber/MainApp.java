package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("BMW", 2023);
      Car car2 = new Car("Toyota", 2022);
      Car car3 = new Car("Jaguar", 2019);

      userService.add(new User("Islam", "Kayipbekov", "islam@mail.ru", car1));
      userService.add(new User("Elana", "Kayipbekova", "elana@mail.ru", car2));
      userService.add(new User("Jason", "Statham", "jason@mail.ru", car3));

      System.out.println("Все пользователи");
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }

      System.out.println("\n--- Поиск пользователя по машине Toyota 2018 ---");
      User foundUser = userService.getUserByCarModelAndSeries("Toyota", 2022);
      if (foundUser != null) {
         System.out.println("Найден: " + foundUser);
      } else {
         System.out.println("Пользователь с такой машиной не найден.");
      }

      context.close();
   }
}
