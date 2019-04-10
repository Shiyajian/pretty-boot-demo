package github.shiyajian.pretty;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({
        "github.shiyajian.pretty.mapper",
        "github.shiyajian.pretty.dao"
})
public class PrettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrettyApplication.class, args);
    }

}

