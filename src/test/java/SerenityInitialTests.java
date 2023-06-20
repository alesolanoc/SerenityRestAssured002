import facts.ColorPlans;
import models.users.Datum;
import models.users.RegisterUserInfo;
import models.users.UsersUpdate;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import questions.GetUsersQuestion;
import questions.ResponseCode;
import tasks.DeleteUser;
import tasks.GetUsers;
import tasks.RegisterUser;
import tasks.UpdateUser;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SerenityRunner.class)
public class SerenityInitialTests {
    private final String restApiURI = "http://localhost:5000/api";
    @Test
    public void getUsersTest(){
        Actor alejandro = Actor.named("Alejandro the tester")
                .whoCan(CallAnApi.at(restApiURI));

 //       alejandro.attemptsTo(Get.resource("/users?page=2"));
 //       alejandro.attemptsTo(GetUsers.fromPage(1));
        alejandro.attemptsTo(GetUsers.fromPage(1));

 //       assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);
        alejandro.should(seeThat("el codigo de respuesta : ", ResponseCode.was(), equalTo(200)));

        Datum user = new GetUsersQuestion().answeredBy(alejandro).getData().stream().filter(x ->x.getId() == 4).findFirst().orElse(null);
        alejandro.should(seeThat("usuario no es nulo", act->user, notNullValue()),
                        seeThat("El email del usuarui",act -> user.getEmail(),equalTo("eve.holt@reqres.in")));
    }

    @Test
    public void registerUserTest(){
        Actor alejandro = Actor.named("Alejandro the tester")
                .whoCan(CallAnApi.at(restApiURI));
        String registerUserInfo = "{\n" +
                "\t\"name\": \"Alejandro Solano\",\n" +
                "\t\"job\": \"tester\",\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        alejandro.attemptsTo(RegisterUser.witnInfo(registerUserInfo));
        alejandro.should(seeThat("el codigo de respuesta : ", ResponseCode.was(), equalTo(200)));

    }

    @Test
    public void registerUserPojoTest(){
        Actor alejandro = Actor.named("Alejandro the tester")
                .whoCan(CallAnApi.at(restApiURI));
        RegisterUserInfo registerUserInfo = new RegisterUserInfo();
        registerUserInfo.setName("Alejandro Solano");
        registerUserInfo.setJob("teste");
        registerUserInfo.setEmail("eve.holt@reqres.in");
        registerUserInfo.setPassword("pistol");

        alejandro.attemptsTo(RegisterUser.witnInfo(registerUserInfo));
        alejandro.should(seeThat("el codigo de respuesta pojo : ", ResponseCode.was(), equalTo(200)));

    }

    @Test
    public void updateUserPojoTest(){
        Actor alejandro = Actor.named("Alejandro the tester")
                .whoCan(CallAnApi.at(restApiURI));
        RegisterUserInfo registerUserInfo = new RegisterUserInfo();
        registerUserInfo.setName("Alejandro Solanor");
        registerUserInfo.setJob("tester");
        registerUserInfo.setEmail("eve.holt@reqres.in");
        registerUserInfo.setPassword("pistol");

        alejandro.attemptsTo(UpdateUser.witnInfo(registerUserInfo));
        alejandro.should(seeThat("el codigo de respuesta pojo : ", ResponseCode.was(), equalTo(200)));

        String name = new GetUsersQuestion().answeredBy1(alejandro).getName().toString();
        String update = new GetUsersQuestion().answeredBy1(alejandro).getUpdatedAt().toString();
        alejandro.should(seeThat("usuario no es nuloo",act->name.equals("Alejandro Solanor")),
                seeThat("usuario no es nuloo",act->update.contains("2023")));
    }

    @Test
    public void deleteUserTest(){
        Actor alejandro = Actor.named("Alejandro the tester")
                .whoCan(CallAnApi.at(restApiURI));
        alejandro.attemptsTo(DeleteUser.witnInfo(2));
        alejandro.should(seeThat("el codigo de respuesta de borrado : ", ResponseCode.was(), equalTo(204)));
    }

    @Test
    public void factTest(){
        Actor alejandro = Actor.named("Alejandro the tester")
                .whoCan(CallAnApi.at(restApiURI));
        alejandro.has(ColorPlans.toViewColors());
    }

}
