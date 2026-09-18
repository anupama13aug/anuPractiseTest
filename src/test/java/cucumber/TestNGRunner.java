package cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/java/cucumber/",
        glue = "org.anutestframework.stepDefinition",
        plugin = {"html:target/cucumber-reports.html"},
        monochrome = true
)
public class TestNGRunner extends AbstractTestNGCucumberTests {


}
