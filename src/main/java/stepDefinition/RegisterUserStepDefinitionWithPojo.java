package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.users.RegisterUserInfo;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.ResponseCode;
import tasks.RegisterUser;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class RegisterUserStepDefinitionWithPojo {

    private final String restApiURI = "http://localhost:5000/api";
    Actor alejandro;

    @Given("alejandro is a client that wants to allow to register his users using pojo")
    public void alejandroIsAClientThatWantsToAllowToRegisterHisUsersUsingPojo() {
        alejandro = Actor.named("Alejandro the tester")
                .whoCan(CallAnApi.at(restApiURI));
    }

    @When("send the related data for the register using pojo")
    public void sendTheRelatedDataForTheRegisterUsingPojo() {
        RegisterUserInfo registerUserInfo = new RegisterUserInfo();
        registerUserInfo.setName("Alejandro Solano");
        registerUserInfo.setJob("teste");
        registerUserInfo.setEmail("eve.holt@reqres.in");
        registerUserInfo.setPassword("pistol");

        alejandro.attemptsTo(RegisterUser.witnInfo(registerUserInfo));
    }

    @Then("have an account registered successfully using pojo")
    public void haveAnAccountRegisteredSuccessfullyUsingPojo() {
        alejandro.should(seeThat("el codigo de respuesta pojo : ", ResponseCode.was(), equalTo(200)));
    }
}
