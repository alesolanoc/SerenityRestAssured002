package facts;

import Interactions.Get;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.facts.Fact;

import java.util.List;
import java.util.HashMap;

public class ColorPlans implements Fact {
    private String planInfo;
    public static ColorPlans toViewColors(){
        return new ColorPlans();
    }
    @Override
    public void setup(Actor actor){
        actor.attemptsTo(Get.resource("/plans"));
        List<HashMap<String, String>> plans = SerenityRest.lastResponse().path("data");
        actor.remember("plans",plans);
        planInfo = plans.toString();
    }
    public String toString(){
        return "Los colores son: " + planInfo;
    }
}
