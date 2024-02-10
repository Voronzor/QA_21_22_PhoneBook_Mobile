package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthentificationScreen;

public class LoginSecondTest extends AppiumConfig {
    @Test
    public void loginSuccess() {
        new AuthentificationScreen(driver)
                .fillEmail("vorronkovkirill@gmail.com")
                .fillPassword("Leet1337!")
                .submitLogin()
                .isAccountOpened()
                .logout();

    }

    @Test
    public void loginSuccessModel() {
        new AuthentificationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("vorronkovkirill@gmail.com")
                        .password("Leet1337!").build())
                .submitLogin()
                .isAccountOpened()
                .logout();


    }

    @Test
    public void loginWrongEmail(){
        new AuthentificationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("vorronkovkirillgmail.com")
                        .password("Leet1337!").build())
                .submitLoginNegative()
                .isErrorMessageHasText("Login or Password incorrect");
    }

}