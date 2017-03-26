package ru.bmstu.rk9;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import ru.bmstu.rk9.database.Credentials;
import ru.bmstu.rk9.database.Database;

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
        SpringApplication.run(Application.class, args);

        //Loading driver for sap jdbc
        try {
            Class.forName("com.sap.db.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Flyway flyway = new Flyway();
        flyway.setDataSource(Database.dataSource);
        flyway.migrate();
    }
}
