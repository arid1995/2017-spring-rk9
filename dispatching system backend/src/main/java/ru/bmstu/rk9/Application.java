package ru.bmstu.rk9;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jndi.JndiObjectFactoryBean;
import ru.bmstu.rk9.database.Credentials;
import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.database.TableManager;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

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

        TableManager tableManager = new TableManager();
        tableManager.createTables();
    }
}
