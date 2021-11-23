package ar.edu.unq.desapp.grupoD022021.backenddesappapi.architectureTest;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures.LayeredArchitecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

@AnalyzeClasses(packages = "ar.edu.unq.desapp.grupoD022021.backenddesappapi", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchitectureTest {

    @ArchTest
    public static final ArchRule layerRule =
            layeredArchitecture()
                // Define layers
                .layer("WebService").definedBy("ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice")
                .layer("Service").definedBy("ar.edu.unq.desapp.grupoD022021.backenddesappapi.service")
                .layer("Repositories").definedBy("ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories")
                    .layer("Utility").definedBy("ar.edu.unq.desapp.grupoD022021.backenddesappapi.utility")
                    .layer("Security").definedBy("ar.edu.unq.desapp.grupoD022021.backenddesappapi.security.jwt")
                    .layer("Aspect").definedBy("ar.edu.unq.desapp.grupoD022021.backenddesappapi.aspect")
                    // Add constraints
                    .whereLayer("WebService").mayNotBeAccessedByAnyLayer()
                    .whereLayer("Service").mayOnlyBeAccessedByLayers("WebService", "Utility", "Security")
                .whereLayer("Repositories").mayOnlyBeAccessedByLayers("Service", "Aspect");

    @ArchTest
    public static final ArchRule controllerNameRule =
            classes().that().haveSimpleNameEndingWith("Controller").should().resideInAPackage("ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice");

    @ArchTest
    public static final ArchRule serviceNameRule =
            classes().that().haveSimpleNameEndingWith("Service").should().resideInAPackage("ar.edu.unq.desapp.grupoD022021.backenddesappapi.service");

    @ArchTest
    public static final ArchRule repositoriesNameRule =
            classes().that().haveSimpleNameEndingWith("Repository").should().resideInAPackage("ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories");

}
