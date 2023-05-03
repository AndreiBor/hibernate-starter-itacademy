package by.itacademy.hibernate;


import by.itacademy.hibernate.convertor.BirthdayConvertor;
import by.itacademy.hibernate.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        /*Configuration configuration = new Configuration();
        configuration.configure();

        Company company = Company.builder()
                .name("Google2")
                .build();

        User user = User.builder()
                .username("ivan2@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Pavel")
                        .lastname("Ivanov")
                        .birthDate(new Birthday(LocalDate.of(2000, 1, 19)))
                        .build())
                .role(Role.ADMIN)
                .company(company)
                .build();

        log.info("User object is transient state {}", user);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            try (Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();

                User user1 = session1.get(User.class, 2L);
//                session1.saveOrUpdate(company);
                session1.saveOrUpdate(user);
//                session1.evict(user1);
//                System.out.println(user1.getCompany().getName());
                session1.getTransaction().commit();
            }
        } catch (Exception e) {
            log.error("Exception occured", e);
            throw e;
        }*/
    }
}
