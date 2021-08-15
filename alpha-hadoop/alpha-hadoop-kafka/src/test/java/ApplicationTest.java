import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author tanghaiyang on 2017/12/26.
 */
@SpringBootApplication
@ActiveProfiles(profiles = "")
@EnableJpaRepositories({"com.geektcp.alpha"})
@EntityScan({"com.geektcp.alpha"})
@ComponentScan({"com.geektcp.alpha"})
public class ApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class, args);
    }
}
