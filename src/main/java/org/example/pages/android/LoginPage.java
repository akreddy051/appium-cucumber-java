package org.example.pages.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class LoginPage {
    private final AppiumDriver driver;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private WebElement dropDownBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement maleRadioOption;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleRadioOption;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement letsShopBtn;

    @AndroidFindBy(className = "android.widget.Toast")
    private WebElement toastMessage;

    //BHIM Locators
    @AndroidFindBy(accessibility = "Passcode Keyboard")
    private WebElement passcodeKeyboard;

    @AndroidFindBy(id = "in.org.npci.upi:id/btn_sdk_btn_ignore")
    private WebElement skip;



    //Actions

    public void selectCountry(String country) {
        dropDownBtn.click();
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"" + country + "\"]")).click();
    }

    public void enterUserName(String name) {
        nameField.sendKeys(name);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            maleRadioOption.click();
        } else {
            femaleRadioOption.click();
        }
    }

    public void clickOnLetsShop() {
        letsShopBtn.click();
    }

    //Validations
//    public void verifyUserOnLoginScreen() {
//        boolean isCountryDropdownVisible = dropDownBtn.isDisplayed();
//        Assert.assertTrue(isCountryDropdownVisible);
//    }

    public void verifyUserOnLoginScreen() {
        System.out.println("sessionId : " +driver.getSessionId());
//        Map<String,Object> args = new HashMap<>();
//        args.put("package","in.org.npci.upi");
//        args.put("intent","in.org.npci.upi/in.org.npci.upiapp.HomeActivity");
//        driver.executeScript("mobile: startActivity",args);
//        driver.executeScript("mobile: startActivity",args);
//        driver.executeScript("mobile: startActivity",args);
//        driver.executeScript("mobile: startActivity",args);
//        driver.executeScript("mobile: startActivity",args);
//        driver.executeScript("mobile: startActivity",args);
//        driver.executeScript("mobile: startActivity",args);
//        driver.executeScript("mobile: startActivity",args);

        skip.click();
        skip.click();
        skip.click();
        boolean isKeyBoardDisplayed = passcodeKeyboard.isDisplayed();
        Assert.assertTrue(isKeyBoardDisplayed);
    }

    public void validateUserNameErrorToast(String message) {
        String actualErrorMessage = toastMessage.getText();
        Assert.assertEquals(actualErrorMessage, message);
    }

    public void enterPasscodeOnBHIM(String passcode) {
        char[] digits = passcode.toCharArray();
        for(char digit: digits){
            WebElement digitEle = driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc=\""+digit+"\"])[1]"));
            digitEle.click();
        }
    }
}
