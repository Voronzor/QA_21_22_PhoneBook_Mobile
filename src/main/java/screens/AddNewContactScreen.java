package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Contact;
import org.openqa.selenium.support.FindBy;

import javax.xml.xpath.XPath;

public class AddNewContactScreen extends BaseScreen{
    public AddNewContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.sheygam.contactapp:id/inputName")
    MobileElement nameEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputLastName")
    MobileElement lastNameEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    MobileElement emailEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputPhone")
    MobileElement phoneEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputAddress")
    MobileElement addressEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputDesc")
    MobileElement descriptionEditText;
    @FindBy(id = "com.sheygam.contactapp:id/createBtn")
    MobileElement createButton;

    public AddNewContactScreen fillContactForm(Contact contact){
        should(nameEditText, 5);
        type(nameEditText, contact.getName());
        type(lastNameEditText, contact.getLastName());
        type(emailEditText,contact.getEmail());
        type(phoneEditText, contact.getPhone());
        type(addressEditText, contact.getAddress());
        type(descriptionEditText, contact.getDescription());
        return this;
    }

    public ContactListScreen submitContactForm(){
        createButton.click();
        return new ContactListScreen(driver);
    }

    public AddNewContactScreen submitContactFormNegative(){
        createButton.click();
        return this;
    }

    public AddNewContactScreen isErrorContainsText(String text){
        checkAlertText(text);
        return this;
    }

//    public ContactListScreen createContact(Contact contact){
//        should(nameEditText, 5);
//        type(nameEditText, contact.getName());
//        type(lastNameEditText, contact.getLastName());
//        type(emailEditText,contact.getEmail());
//        type(phoneEditText, contact.getPhone());
//        type(addressEditText, contact.getAddress());
//        type(descriptionEditText, contact.getDescription());
//        createButton.click();
//        return new ContactListScreen(driver);
//    }

}

