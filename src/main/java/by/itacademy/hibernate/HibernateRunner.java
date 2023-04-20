package by.itacademy.hibernate;


import by.itacademy.hibernate.convertor.BirthdayConvertor;
import by.itacademy.hibernate.entity.Birthday;
import by.itacademy.hibernate.entity.Role;
import by.itacademy.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
//        configuration.addAnnotatedClass(User.class);
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
//        configuration.addAttributeConverter(new BirthdayConvertor(), true);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = User.builder()
                    .username("ivan5@gmail.com")
                    .firstname("Pavel")
                    .lastname("Ivanov")
                    .birthDate(new Birthday(LocalDate.of(2000, 1, 19)))
                    .role(Role.ADMIN)
                    .build();

            User user1 = session.get(User.class, "ivan1@gmail.com");
            System.out.println(user1);
            session.getTransaction().commit();
        }
    }
}
