package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
//        features = {"C:\\Users\\aleja\\IdeaProjects\\SerenityRestAssured002\\src\\test\\resources\\features\\resgister_user.feature"},
        features = {".\\src\\test\\resources\\features\\resgister_user.feature"},
        glue = {"stepDefinition"},
        plugin = {"pretty"}
)
public class TestRunner  {

}