package org.example.pages.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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
    public void verifyUserOnLoginScreen() {
        boolean isCountryDropdownVisible = dropDownBtn.isDisplayed();
        Assert.assertTrue(isCountryDropdownVisible);
    }

    public void validateUserNameErrorToast(String message) {
        String actualErrorMessage = toastMessage.getText();
        Assert.assertEquals(actualErrorMessage, message);
    }
}
