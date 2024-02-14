package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityTextView;

    @FindBy(xpath = "//*[@content-desc='More options']")
    MobileElement menuOptions;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement logoutButton;

    @FindBy(xpath = "//*[@content-desc='add']")
    MobileElement plusButton;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName'")
    List<MobileElement> contactNameList;

    public boolean isActivityTitleDisplayed(String text){
        //return activityTextView.getText().contains("Contact List");
        return isShouldHave(activityTextView, text, 10);
    }

    public AuthentificationScreen logout(){
        if(activityTextView.getText().equals("Contact list")) {
            menuOptions.click();
            logoutButton.click();
        }
        return new AuthentificationScreen(driver);
    }

    public ContactListScreen isAccountOpened(){
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }

    public AddNewContactScreen openContactForm(){
        if(activityTextView.getText().equals("Contact list")) {
            should(plusButton, 5);
            plusButton.click();
        }
        return new AddNewContactScreen(driver);
    }

    public ContactListScreen isContactAddedByName(String name, String lastName){
        //List<MobileElement> list = driver.findElements(By.xpath("com.sheygam.contactapp:id/rowName")); variety of search
        isShouldHave(activityTextView,"Contact list", 10);
        boolean isPresent = false;
        for (MobileElement element:contactNameList){
            if(element.getText().equals(name+ " "+lastName)){
                isPresent = true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
        return this;
    }

}

