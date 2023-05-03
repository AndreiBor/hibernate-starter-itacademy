package by.itacademy.hibernate.util;


import by.itacademy.hibernate.entity.Chat;
import by.itacademy.hibernate.entity.User;
import by.itacademy.hibernate.entity.UserChat;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.time.Instant;

@UtilityClass
public class HibernateUtil {
    public static Configuration getConfig() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        return configuration;
    }
}
