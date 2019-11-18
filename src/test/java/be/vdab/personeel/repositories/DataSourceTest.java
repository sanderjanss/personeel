package be.vdab.personeel.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;
    @Test
    public void getConnection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
        }
    }
}