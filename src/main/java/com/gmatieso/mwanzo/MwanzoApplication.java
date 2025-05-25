package com.gmatieso.mwanzo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan({"com.gmatieso.mwanzo.membership.entity"})
public class MwanzoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MwanzoApplication.class, args);
    }

}
