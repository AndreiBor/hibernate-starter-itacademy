package by.itacademy.hibernate;

import by.itacademy.hibernate.entity.Company;
import by.itacademy.hibernate.entity.User;
import lombok.Cleanup;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;


class HibernateRunnerTest {
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
        Configuration configuration = new Configuration();
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
        session.getTransaction().commit();
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