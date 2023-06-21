package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.users.RegisterUserInfo;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.GetUsersQuestion;
import questions.ResponseCode;
import tasks.UpdateUser;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUserStepDefinition {
    Actor alejandro;
    private final String restApiURI = "http://192.168.100.222:5000/api";
    private String name;
    private String update;

    @Given("alejandro is a client that wants to allow to update his users")
    public void alejandroIsAClientThatWantsToAllowToUpdateHisUsers() {
        alejandro = Actor.named("Alejandro the tester")
                .whoCan(CallAnApi.at(restApiURI));
    }

    @When("send the related data for the update")
    public void sendTheRelatedDataForTheUpdate() {
        RegisterUserInfo registerUserInfo = new RegisterUserInfo();
        registerUserInfo.setName("Alejandro Solanor");
        registerUserInfo.setJob("tester");
        registerUserInfo.setEmail("eve.holt@reqres.in");
        registerUserInfo.setPassword("pistol");

        alejandro.attemptsTo(UpdateUser.witnInfo(registerUserInfo));
        alejandro.should(seeThat("el codigo de respuesta pojo : ", ResponseCode.was(), equalTo(200)));

        name = new GetUsersQuestion().answeredBy1(alejandro).getName().toString();
        update = new GetUsersQuestion().answeredBy1(alejandro).getUpdatedAt().toString();
    }

    @Then("have an account updated successfully")
    public void haveAnAccountUpdatedSuccessfully() {
        alejandro.should(seeThat("usuario no es nuloo",act->name.equals("Alejandro Solanor")),
                seeThat("usuario no es nuloo",act->update.contains("2023")));
    }

}
