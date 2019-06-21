package task_7.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import task_7.shop.DAO.implement.BucketDAOImpl;
import task_7.shop.DAO.implement.StockDAOImpl;
import task_7.shop.DAO.implement.UserDAOImpl;
import task_7.shop.service.implement.BucketServiceImpl;
import task_7.shop.service.implement.StockServiceImpl;
import task_7.shop.service.implement.UserServiceImpl;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
@Configuration
@PropertySource("classpath:properties/application.properties")
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan("task_7.shop.*")
public class ApplicationConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(getDataSource());
        return transactionManager;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        return jdbcTemplate;
    }

    @Bean
    public BucketServiceImpl getBucketService() {
        BucketServiceImpl bucketServiceImpl = new BucketServiceImpl();
        return bucketServiceImpl;
    }

    @Bean
    public StockServiceImpl getStockService() {
        StockServiceImpl stockServiceImpl = new StockServiceImpl();
        return stockServiceImpl;
    }

    @Bean
    public UserServiceImpl getUserService() {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        return userServiceImpl;
    }

    @Bean
    public UserDAOImpl getUserDAO() {
        UserDAOImpl userDAO = new UserDAOImpl();
        return userDAO;
    }

    @Bean
    public StockDAOImpl getStockDAO() {
        StockDAOImpl stockDAO = new StockDAOImpl();
        return stockDAO;
    }

    @Bean
    public BucketDAOImpl getBucketDAO() {
        BucketDAOImpl bucketDAO = new BucketDAOImpl();
        return bucketDAO;
    }
}
