package org.example.pages.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProductsPage {
    private final AppiumDriver driver;

    public ProductsPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement pageTitle;

    //validations
    public void verifyUserNavToProductsPage(){
        String actualTitleText = pageTitle.getText();
        Assert.assertEquals(actualTitleText,"Products");
    }

}
