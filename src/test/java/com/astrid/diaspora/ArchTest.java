package com.astrid.diaspora;

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
            .importPackages("com.astrid.diaspora");

        noClasses()
            .that()
                .resideInAnyPackage("com.astrid.diaspora.service..")
            .or()
                .resideInAnyPackage("com.astrid.diaspora.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.astrid.diaspora.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
