package com.tomomoto.tacocloud;

import com.tomomoto.tacocloud.dao.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TacoCloudMvnApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudMvnApplication.class, args);
    }

}
