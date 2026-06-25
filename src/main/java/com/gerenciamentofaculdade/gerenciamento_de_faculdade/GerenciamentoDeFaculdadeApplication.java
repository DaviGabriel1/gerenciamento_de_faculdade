package com.gerenciamentofaculdade.gerenciamento_de_faculdade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.modulith.Modulith;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@Modulith(
        systemName = "gerenciamento-faculdade",
        sharedModules = {"config"}
)
@EnableAsync
public class GerenciamentoDeFaculdadeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GerenciamentoDeFaculdadeApplication.class, args);
    }

}
