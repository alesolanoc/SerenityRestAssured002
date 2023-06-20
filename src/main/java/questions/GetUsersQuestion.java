package questions;

import models.users.Users;
import models.users.UsersUpdate;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetUsersQuestion implements Question {

    @Override
    public Users answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(Users.class);
    }

    public UsersUpdate answeredBy1(Actor actor) {
        return SerenityRest.lastResponse().as(UsersUpdate.class);
    }
}
