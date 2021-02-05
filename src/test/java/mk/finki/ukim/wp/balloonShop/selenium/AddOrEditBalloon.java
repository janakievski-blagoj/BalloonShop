package mk.finki.ukim.wp.balloonShop.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditBalloon extends AbstractPage{
    
    private WebElement name;
    private WebElement description;
    private WebElement manufacturer;
    private WebElement balloonType;
    private WebElement submit;

    public AddOrEditBalloon(WebDriver driver) {
        super(driver);
    }

    public static BalloonsPage addProduct(WebDriver driver, String name, String description, String manufacturer, String balloonType) {
        get(driver, "/balloons/add-form");
        AddOrEditBalloon addOrEditBalloon = PageFactory.initElements(driver, AddOrEditBalloon.class);
        addOrEditBalloon.name.sendKeys(name);
        addOrEditBalloon.description.sendKeys(description);
        addOrEditBalloon.manufacturer.click();
        addOrEditBalloon.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();
        addOrEditBalloon.balloonType.click();
        addOrEditBalloon.balloonType.findElement(By.xpath("//option[. = '" + balloonType + "']")).click();

        addOrEditBalloon.submit.click();
        return PageFactory.initElements(driver, BalloonsPage.class);
    }

    public static BalloonsPage editProduct(WebDriver driver, WebElement editButton, String name, String description, String manufacturer, String balloonType) {
        editButton.click();
        System.out.println(driver.getCurrentUrl());
        AddOrEditBalloon addOrEditBalloon = PageFactory.initElements(driver, AddOrEditBalloon.class);
        addOrEditBalloon.name.sendKeys(name);
        addOrEditBalloon.description.sendKeys(description);
        addOrEditBalloon.manufacturer.click();
        addOrEditBalloon.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();
        addOrEditBalloon.balloonType.click();
        addOrEditBalloon.balloonType.findElement(By.xpath("//option[. = '" + balloonType + "']")).click();

        addOrEditBalloon.submit.click();
        return PageFactory.initElements(driver, BalloonsPage.class);
    }


}

