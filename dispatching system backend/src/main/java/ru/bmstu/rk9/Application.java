package ru.bmstu.rk9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bmstu.rk9.database.TableManager;

/**
 * Created by farid on 3/24/17.
 */

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }

  public static void main(String... args) {
    TableManager tableManager = new TableManager();
    tableManager.createTables();

    SpringApplication.run(Application.class, args);

    //Loading driver for sap jdbc
    try {
      Class.forName("com.sap.db.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, e.getMessage(), e);
    }
  }
}
