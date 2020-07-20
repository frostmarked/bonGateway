package com.bonlimousin.gateway;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.bonlimousin.gateway");

        noClasses()
            .that()
                .resideInAnyPackage("com.bonlimousin.gateway.service..")
            .or()
                .resideInAnyPackage("com.bonlimousin.gateway.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.bonlimousin.gateway.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
