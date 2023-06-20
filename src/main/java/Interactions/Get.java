package Interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import net.thucydides.core.annotations.Step;

public class Get extends RestInteraction {
    private final String resource;

    public Get(String resource) {
        this.resource = resource;
    }

    @Step("{0} executes a GET on the resource #resource")
    public <T extends Actor> void performAs(T actor) {
        this.rest().log().all().get(CallAnApi.as(actor).resolve(this.resource), new Object[0]).then().log().all();
    }

    public static Get resource(String resource) {
        return (Get) Tasks.instrumented(Get.class, new Object[]{resource});
    }
}
