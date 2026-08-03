package org.anutestframework.pageobjects;

import org.anutestframework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCataloguePage extends AbstractComponent {

    WebDriver driver;

    public ProductCataloguePage(WebDriver driver){

        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    WebElement product ;

    @FindBy(css = ".mb-3")
    List<WebElement> products ;


}
