package tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import Interactions.Post;
//import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class RegisterUser implements Task {
    private static Object userInfo = null;

    public RegisterUser(Object userInfo){
        this.userInfo = userInfo;
    }
    public static Performable witnInfo(Object userInfo){
        return instrumented(RegisterUser.class, userInfo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to("/register").with(RequestSpecification->RequestSpecification
                .contentType(ContentType.JSON)
                .body(userInfo)));
    }
}
