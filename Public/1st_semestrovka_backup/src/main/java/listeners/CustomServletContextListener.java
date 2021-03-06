package listeners;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repositories.UsersRepository;
import repositories.UsersRepositoryImpl;
import services.signIn.SignInService;
import services.signIn.SignInServiceImpl;
import services.signUp.SignUpService;
import services.signUp.SignUpServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomServletContextListener implements ServletContextListener {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "123456";
    private static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        UsersRepository usersRepository = new UsersRepositoryImpl(dataSource);
        SignUpService signUpService = new SignUpServiceImpl(usersRepository);
        SignInService signInService = new SignInServiceImpl(usersRepository);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signInService", signInService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

