package com.mackittipat.springjdbc;

import com.mackittipat.springjdbc.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Slf4j
@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("CREATE TABLE customer ( id INT, name VARCHAR(20) )");
        log.info("Created customer table");

        jdbcTemplate.execute("INSERT INTO customer (id, name) VALUES (1, 'Mac')");
        log.info("Inserted customer table");

        List<Customer> customerList = jdbcTemplate.query(
                "SELECT id, name FROM customer",
                (rs, rowNum) -> new Customer(rs.getInt("id"), rs.getString("name"))
        );

        customerList.forEach(c -> {
            log.info("Query : " + c.toString());
        });
    }
}
