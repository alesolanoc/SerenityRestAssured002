package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.ResponseCode;
import tasks.RegisterUser;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class RegisterUserStepDefinition {
    private final String restApiURI = "http://192.168.100.222:5000/api";
    Actor alejandro;

    @Given("alejandro is a client that wants to allow to register his users")
    public void alejandroIsAClientThatWantsToAllowToRegisterHisUsers() {
        alejandro = Actor.named("Alejandro the tester")
                .whoCan(CallAnApi.at(restApiURI));// Write code here that turns the phrase above into concrete actions

    }

    @When("send the related data for the register")
    public void sendTheRelatedDataForTheRegister() {
        String registerUserInfo = "{\n" +
                "\t\"name\": \"Alejandro Solano c\",\n" +
                "\t\"job\": \"testerc\",\n" +
                "    \"email\": \"charles.morris@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        alejandro.attemptsTo(RegisterUser.witnInfo(registerUserInfo));

    }

    @Then("have an account registered successfully")
    public void haveAnAccountRegisteredSuccessfully() {
        alejandro.should(seeThat("el codigo de respuesta : ", ResponseCode.was(), equalTo(200)));

    }

}
