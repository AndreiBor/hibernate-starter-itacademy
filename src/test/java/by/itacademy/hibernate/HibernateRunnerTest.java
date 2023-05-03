package by.itacademy.hibernate;

import by.itacademy.hibernate.entity.*;
import by.itacademy.hibernate.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.Instant;


class HibernateRunnerTest {
    @Test
    public void checkInheritance() {
        @Cleanup var sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = Company.builder()
                .name("Yandex")
                .build();
        session.save(company);

        var programmer = Programmer.builder()
                .username("ivan@gmail.com")
                .language(Language.JAVA)
                .company(company)
                .build();
        session.save(programmer);

        var manager = Manager.builder()
                .username("pavel@gmail.com")
                .company(company)
                .project("Java Enterprise")
                .build();
        session.save(manager);

        session.flush();
        session.clear();

        var programmer1 = session.get(Programmer.class, 1L);
        var manager1 = session.get(User.class, 2L);

        System.out.println();

        session.getTransaction().commit();
    }

    @Test
    public void checkH2() {
        @Cleanup var sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = Company.builder()
                .name("Yandex")
                .build();

        session.save(company);

        session.getTransaction().commit();
    }

    @Test
    public void checkManyToMany() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        @Cleanup var sessionFactory = configuration.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var user = session.get(User.class, 7L);
        var chat = session.get(Chat.class, 1L);
/*
        var userChat = UserChat.builder()
                .createdAt(Instant.now())
                .createdBy(user.getUsername())
                .build();

        userChat.setUser(user);
        userChat.setChat(chat);

        session.save(userChat);*/
        session.getTransaction().commit();
    }

    @Test
    public void checkOneToOne() {
        /*Configuration configuration = new Configuration();
        configuration.configure();
        @Cleanup var sessionFactory = configuration.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var user = User.builder()
                .username("test2@gmail.com")
                .build();
        System.out.println(session.get(User.class, 7L));
        /*var profile = Profile.builder()
                .language("ru")
                .street("Skryganova 14")
                .build();
        profile.setUser(user);
        session.save(user);
        session.getTransaction().commit();*/
    }

    @Test
    public void checkInitEx() {
        Company company = null;
        Configuration configuration = new Configuration();
        configuration.configure();
        @Cleanup var sessionFactory = configuration.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        company = session.getReference(Company.class, 1);

        session.getTransaction().commit();
        session.close();
        var users = company.getUsers();
        System.out.println(users.size());
    }


    @Test
    public void deleteCompany() {
        Configuration configuration = new Configuration();
        configuration.configure();
        @Cleanup var sessionFactory = configuration.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var user = session.get(User.class, 4L);
        session.delete(user);

        session.getTransaction().commit();
    }


    @Test
    public void addUserToNewCompany() {
        /*Configuration configuration = new Configuration();
        configuration.configure();
        @Cleanup var sessionFactory = configuration.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = Company.builder()
                .name("Integral")
                .build();

        var user = User.builder()
                .username("int@mail.com")
                .build();

        company.addUser(user);

        session.save(company);
        session.getTransaction().commit();*/
    }

    @Test
    public void oneToMany() {
        Configuration configuration = new Configuration();
        configuration.configure();
        @Cleanup var sessionFactory = configuration.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = session.get(Company.class, 1);
        System.out.println(company.getUsers());

        session.getTransaction().commit();

    }

    @Test
    void checkApi() throws SQLException, IllegalAccessException {
    /*    User user = User.builder()
                .username("ivan1@gmail.com")
                .firstname("Ivan")
                .lastname("Ivanov")
                .birthDate(new Birthday(LocalDate.of(2000, 1, 19)))
                .build();

        String sql = """
                insert into 
                %s
                (%s)
                values
                (%s)
                """;
        String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
                .orElse(user.getClass().getName());

        Field[] declaredFields = user.getClass().getDeclaredFields();

        String columnNames = Arrays.stream(declaredFields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName())).collect(Collectors.joining(", "));

        String columnValues = Arrays.stream(declaredFields)
                .map(field -> "?")
                .collect(Collectors.joining(", "));

        System.out.println(sql.formatted(tableName, columnNames, columnValues));
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "root");
        PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName, columnNames, columnValues));

        for (int i = 0; i < declaredFields.length; i++) {
            declaredFields[i].setAccessible(true);
            preparedStatement.setObject(i + 1, declaredFields[i].get(user));
        }

        System.out.println(preparedStatement);

        preparedStatement.executeUpdate();
        connection.close();
*/
    }
}