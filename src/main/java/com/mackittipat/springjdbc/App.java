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
        log.info("Running");

        List<Customer> customerList = jdbcTemplate.query(
                "SELECT id, name FROM customer",
                (rs, rowNum) -> new Customer(rs.getInt("id"), rs.getInt("name"))
        );

        customerList.forEach(c -> {
            log.info(">>> " + c.toString());
        });
    }
}
