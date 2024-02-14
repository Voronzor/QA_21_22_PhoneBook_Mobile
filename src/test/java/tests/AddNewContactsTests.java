package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthentificationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactsTests extends AppiumConfig {

    @BeforeClass
    public void preCondition(){
        new AuthentificationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("vorronkovkirill@gmail.com").password("Leet1337!").build())
                .submitLogin();
    }
    @Test
    public void createNewContactSuccess(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Wow"+i)
                .email("Wow"+i+"@gmail.com")
                .phone("65987456"+i)
                .address("NY")
                .description("Friend")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(),contact.getLastName());
    }

    @Test
    public void createNewContactSuccessReq(){

    }

    @Test
    public void createContactWithEmptyName(){
        Contact contact = Contact.builder()
                .lastName("Dov")
                .email("dow@gmail.com")
                .phone("659874560099")
                .address("NY")
                .description("Empty name")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{name=must not be blank}");
    }

    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver)
                .logout();

    }
}
