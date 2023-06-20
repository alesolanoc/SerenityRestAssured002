package Interactions;

import net.serenitybdd.screenplay.rest.interactions.RestInteraction;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.abilities.CallAnApi.as;

public class Post extends RestInteraction {
    private final java.lang.String resource;

    public Post(String resource) { /* compiled code */
        this.resource = resource;
    }

    @net.thucydides.core.annotations.Step("{0} executes a POST on the resource #resource")
    public <T extends net.serenitybdd.screenplay.Actor> void performAs(T actor) {
        rest().log().all().post(as(actor).resolve(resource)).then().log().all();
    }

    public static Post to(java.lang.String resource) { return instrumented(Post.class,resource); }

}
