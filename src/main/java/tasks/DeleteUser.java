package tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import Interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteUser implements Task {
    private static int id;
    public static Performable witnInfo(int id){
        return instrumented(DeleteUser.class, id);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        int id = 2;
        actor.attemptsTo(Delete.from("/users/{id})")
                .with(requestSpecification -> requestSpecification.contentType(ContentType.JSON)
                        .request().pathParam("id",id)));
    }
}
