package mk.ukim.finki.pidp.distsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication()
public class DistSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistSysApplication.class, args);
    }

}
