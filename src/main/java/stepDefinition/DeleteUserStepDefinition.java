package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.ResponseCode;
import tasks.DeleteUser;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteUserStepDefinition {
    Actor alejandro;
    private final String restApiURI = "http://localhost:5000/api";

    @Given("alejandro is a client that wants to allow to Delete his users")
    public void alejandro_is_a_client_that_wants_to_allow_to_delete_his_users() {
        alejandro = Actor.named("Alejandro the tester")
                .whoCan(CallAnApi.at(restApiURI));
    }

    @When("send the related data for the delete")
    public void send_the_related_data_for_the_delete() {
        alejandro.attemptsTo(DeleteUser.witnInfo(2));
    }

    @Then("have an account deleted successfully")
    public void have_an_account_deleted_successfully() {
        alejandro.should(seeThat("el codigo de respuesta de borrado : ", ResponseCode.was(), equalTo(204)));
    }

}
