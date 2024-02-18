package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
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

    @FindBy(xpath = "//*[@content-desc='More options']")
    List<MobileElement> menuOptionsList;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement logoutButton;

    @FindBy(xpath = "//*[@content-desc='add']")
    MobileElement plusButton;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName'")
    List<MobileElement> contactNameList;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<MobileElement> contactList;

    @FindBy(id = "android:id/button1")
    MobileElement OkBtn;

    @FindBy(id="com.sheygam.contactapp:id/emptyTxt")
    MobileElement noContactsHereTextView;
    int countBefore;
    int countAfter;

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

    public AuthentificationScreen logout2(){
        if(isElementDisplayed(menuOptions)) {
            menuOptions.click();
            logoutButton.click();
        }
        return new AuthentificationScreen(driver);
    }

    public AuthentificationScreen logout3(){
        if(isElementPresentInList(menuOptionsList)) {
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

    public ContactListScreen deleteFirstContact(){
        isActivityTitleDisplayed("Contact list");
        countBefore=contactList.size();
        System.out.println(countBefore);
        MobileElement first = contactList.get(0);
        Rectangle rectangle =first.getRect();
        int xFrom = rectangle.getX()+rectangle.getWidth()/8;
        int y=rectangle.getY()+rectangle.getHeight()/2;
        int xTo = rectangle.getX()+(rectangle.getWidth()/8)*7;
        TouchAction<?> touchAction= new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xFrom,y))
                .moveTo(PointOption.point(xTo,y))
                .release().perform();
        should(OkBtn,8);
        OkBtn.click();
        shouldLessOne(contactList,countBefore);
        countAfter=contactList.size();
        System.out.println(countAfter);

        return this;
    }

    public ContactListScreen isListSizeLessOnOne(){
        Assert.assertEquals(countBefore-countAfter,1);
        return this;
    }

    public ContactListScreen removeAllContacts(){
        System.out.println("contact list" + contactList.size());
        pause(1000);
        while(driver.findElements(By.xpath("//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")).size()>0){
            deleteFirstContact();
        }
        return this;
    }

    public ContactListScreen isNoContactsHere(){
        isShouldHave(noContactsHereTextView, "No Contacts. Add One more!",10);
        return this;
    }


}

