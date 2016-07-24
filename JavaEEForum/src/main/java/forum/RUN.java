package forum;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import forum.configs.CustomSuccessHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

@SpringBootApplication
public class RUN {
    public static void main(String[] args) {
        SpringApplication.run(RUN.class, args);
    }

    @Bean
    public EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("forum");
    }


    @Bean
    public EntityManager createEntityManager() {
        return this.createEntityManagerFactory().createEntityManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public DataSource dataSource() {
        return new MysqlDataSource();
    }

    @Bean
    public CustomSuccessHandler createCustomSuccessHandler(){
        return new CustomSuccessHandler();
    }

}

