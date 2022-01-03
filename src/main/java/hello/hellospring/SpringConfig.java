package hello.hellospring;

import hello.hellospring.post.JdbcPostRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcPostRepo jdbcPostRepo(){
        return new JdbcPostRepo(dataSource);
    }
}