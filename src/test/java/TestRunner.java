import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features={"src/test/resources/features"},
        glue = {"stepDefs"},
        tags = "not @ignore",
        plugin={"pretty", "html:cucumber-reports/testReport.html", "json:cucumber-reports/jsonReport.json"},
        publish=true
)
public class TestRunner {
}
