import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/Cucumber/Features/purchasingProcesses.feature",
        plugin = {"pretty", "html:out.html"})

public class CucumberTest {
}
