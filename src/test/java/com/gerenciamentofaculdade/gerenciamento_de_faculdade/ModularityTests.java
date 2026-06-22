package com.gerenciamentofaculdade.gerenciamento_de_faculdade;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class ModularityTests {
    static ApplicationModules applicationModules = ApplicationModules
            .of(GerenciamentoDeFaculdadeApplication.class);

    @Test
    void verifyModularityStructure() {
        applicationModules.verify();
    }

    @Test
    void createModuleDocumentation() {
        new Documenter(applicationModules).writeDocumentation();
    }
}
