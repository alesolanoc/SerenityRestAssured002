package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.users.Datum;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.GetUsersQuestion;
import questions.ResponseCode;
import tasks.GetUsers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class DisplayUsersStepDefinition {
    Actor alejandro;
    private final String restApiURI = "http://localhost:5000/api";

    @Given("alejandro is a client that wants to allow to lists his users")
    public void alejandroIsAClientThatWantsToAllowToListsHisUsers() {
        alejandro = Actor.named("Alejandro the tester")
                .whoCan(CallAnApi.at(restApiURI));
    }

    @When("send the related data for the list")
    public void sendTheRelatedDataForTheList() {
        alejandro.attemptsTo(GetUsers.fromPage(1));

        //       assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);
        alejandro.should(seeThat("el codigo de respuesta : ", ResponseCode.was(), equalTo(200)));
    }

    @Then("have displayed the list successfully")
    public void haveDisplayedTheListSuccessfully() {
        Datum user = new GetUsersQuestion().answeredBy(alejandro).getData().stream().filter(x ->x.getId() == 4).findFirst().orElse(null);
        alejandro.should(seeThat("usuario no es nulo", act->user, notNullValue()),
                seeThat("El email del usuarui",act -> user.getEmail(),equalTo("eve.holt@reqres.in")));
    }
}
