package Interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import net.thucydides.core.annotations.Step;

public class Put extends RestInteraction {
    private final String resource;

    public Put(String resource) {
        this.resource = resource;
    }

    @Step("{0} executes a PUT on the resource #resource")
    public <T extends Actor> void performAs(T actor) {
        this.rest().log().all().put(CallAnApi.as(actor).resolve(this.resource), new Object[0]).then().log().all();
    }

    public static Put to(String resource) {
        return (Put) Tasks.instrumented(Put.class, new Object[]{resource});
    }
}
