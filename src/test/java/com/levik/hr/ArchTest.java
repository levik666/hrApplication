package com.levik.hr;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.levik.hr");

        noClasses()
            .that()
            .resideInAnyPackage("com.levik.hr.service..")
            .or()
            .resideInAnyPackage("com.levik.hr.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.levik.hr.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
