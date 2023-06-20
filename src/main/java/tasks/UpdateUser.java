package tasks;

import Interactions.Put;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdateUser implements Task {
    private static Object userInfo = null;

    public UpdateUser(Object userInfo) {
        this.userInfo = userInfo;
    }
    public static Performable witnInfo(Object userInfo){
        return instrumented(UpdateUser.class, userInfo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Put.to("/users/2").with(RequestSpecification->RequestSpecification
                .contentType(ContentType.JSON)
                .body(userInfo)));
    }
}
