package by.itacademy.hibernate;

import by.itacademy.hibernate.entity.Birthday;
import by.itacademy.hibernate.entity.User;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HibernateRunnerTest {
    @Test
    void checkApi() throws SQLException, IllegalAccessException {
        User user = User.builder()
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

    }
}