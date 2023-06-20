package Interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import net.thucydides.core.annotations.Step;

public class Delete extends RestInteraction {
    private final String resource;

    public Delete(String resource) {
        this.resource = resource;
    }

    @Step("{0} executes a DELETE on the resource #resource")
    public <T extends Actor> void performAs(T actor) {
        this.rest().log().all().delete(CallAnApi.as(actor).resolve(this.resource), new Object[0]).then().log().all();
    }

    public static Delete from(String resource) {
        return (Delete) Tasks.instrumented(Delete.class, new Object[]{resource});
    }
}

