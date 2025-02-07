package com.saucedemo.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.saucedemo.utilities.WaitsFactory;

/**
 * The Products class represents the products page of the e-commerce
 * application.
 * It provides methods to interact with and validate the products and their
 * information on the page.
 * 
 * <p>
 * This class contains methods to validate all products information, individual
 * products, product images, and social media images:
 * </p>
 * <ul>
 * <li>{@link #validateAllProductsInfo()}</li>
 * <li>{@link #validateIndividualProductsInfo()}</li>
 * <li>{@link #validateProductsImages()}</li>
 * <li>{@link #socialMediaImages()}</li>
 * </ul>
 * 
 * @author Banele Mlamleli
 * @version 1.0
 * 
 */
public class Products {

    WebDriver driver;

    @FindBy(xpath = "//div[@class='app_logo']")
    WebElement imgAppLogo;

    @FindBy(xpath = "//button[normalize-space()='Open Menu']")
    WebElement burgerMenu;

    @FindBy(xpath = "//div[@class='peek']")
    WebElement imgPeek;

    @FindBy(xpath = "//div[@class='product_label']")
    WebElement lblProducts;

    @FindBy(xpath = "//span[@class='fa-layers-counter shopping_cart_badge']")
    WebElement amountCartBadge;

    @FindBy(xpath = "//div[@class='inventory_list']")
    WebElement allProductsInfo;

    @FindBy(xpath = "//img[@class='inventory_item_img']")
    List<WebElement> productsImageList;

    @FindBy(xpath = "//li[@class='social_twitter']")
    WebElement imgTwitter;

    @FindBy(xpath = "//li[@class='social_facebook']")
    WebElement imgFacebook;

    @FindBy(xpath = "//li[@class='social_linkedin']")
    WebElement imgLinkedin;

    WaitsFactory waitsFactory;

    Logger logger = LogManager.getLogger(new Object() {
    }.getClass().getName());

    public Products(WebDriver driver) {
        logger.info("**** Executing constructor for Products class ****");
        this.driver = driver;
        waitsFactory = new WaitsFactory(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Validates the information of all products on the page.
     * It checks for the presence of specific product names, descriptions, and
     * prices.
     * It also verifies the functionality of the "Add to Cart" button.
     */
    public void allProductsInfo(String prodName, String prodDesc, String price) {
        logger.info("**** Executing allProductsInfo method in the Products class ****");
        String allProducts = allProductsInfo.getText();
        Assert.assertTrue(allProducts.contains("ADD TO CART"));
        Assert.assertTrue(allProducts.contains(prodName));
        Assert.assertTrue(allProducts.contains(prodDesc));
        Assert.assertTrue(allProducts.contains(price));
    }

    /**
     * Verify the button 'ADD TO CART' is clicked and the button text changes to
     * 'REMOVE'
     */
    public void validateRemoveButton() {
        logger.info("**** Executing validateRemoveButton method in the Products class ****");
        driver.findElement(By.xpath("//div[@class='inventory_list']//div[1]//div[3]//button[1]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//button[contains(text(),\"REMOVE\")]")).isDisplayed());
    }

    /**
     * Validates the information of an individual product by navigating to its
     * details page.
     * It checks for the presence of specific product name, description, and price.
     * It also verifies the functionality of the "Add to Cart" and "Remove" buttons.
     */
    public void individualProductsInfo(String locator) {
        logger.info("**** Executing individualProductsInfo method in the Products class ****");
        try {
            System.out.println("Locator: " + locator);
            driver.findElement(By.xpath(locator)).click();

            WebElement btnBack = driver.findElement(By.xpath("//button[@class='inventory_details_back_button']"));
            WebElement imgBackpack = driver.findElement(By.xpath("//img[@class='inventory_details_img']"));
            WebElement btnAddToCart = driver.findElement(By.xpath("//button[@class='btn_primary btn_inventory']"));
            WebElement itemName = driver.findElement(By.xpath("//div[@class='inventory_details_name']"));
            WebElement itemDesc = driver.findElement(By.xpath("//div[@class='inventory_details_desc']"));
            WebElement itemPrice = driver.findElement(By.xpath("//div[@class='inventory_details_price']"));

            btnAddToCart.click();
            Assert.assertTrue(btnAddToCart.isDisplayed(), "Error, ADD TO CART button not displayed");
            WebElement btnRemove = driver.findElement(By.xpath("//button[contains(text(),\"REMOVE\")]"));
            Assert.assertTrue(btnRemove.isDisplayed());

            // verify image is displayed
            Boolean p = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete "
                    + "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0",
                    imgBackpack);
            Assert.assertTrue(p, "Error with item image");
            Assert.assertTrue(itemName.isDisplayed(), "Error with item name");
            Assert.assertTrue(itemDesc.isDisplayed(), "Error with item description");
            Assert.assertTrue(itemPrice.isDisplayed(), "Error with price");
            Assert.assertTrue(btnBack.isDisplayed(), "'Back' button not displayed");

            btnBack.click();

        } catch (Exception e) {
            logger.error(e.getMessage() + ". Error returned in the method '" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "'");
        }
    }

    /**
     * Validates that all product images on the page are not broken.
     * It checks the 'naturalWidth' attribute of each image to ensure it is properly
     * loaded.
     */
    public void validateProductsImages() {
        Integer brokenImageCounter = 0;
        try {
            brokenImageCounter = 0;
            logger.info("The 'Products' page has " + productsImageList.size() + " valid images for each product");
            for (WebElement img : productsImageList) {
                if (img != null) {
                    if (img.getAttribute("naturalWidth").equals("0")) {
                        logger.info(
                                img.getAttribute("outerHTML") + " is broken in the method validateProductsImages.");
                        brokenImageCounter++;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("'" + e.getMessage() + "' in method '" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "'");
            e.printStackTrace();
        }

        if (brokenImageCounter > 0) {
            logger.warn("The page " + driver.getCurrentUrl() + " has " + brokenImageCounter + " broken images");
        }
    }

    /**
     * Validates that social media images (Twitter, Facebook, LinkedIn) are
     * displayed.
     */
    public void socialMediaImages() {
        logger.info("**** Executing socialMediaImages method in the Products class ****");
        waitsFactory.explicitWait(imgTwitter);
        waitsFactory.explicitWait(imgFacebook);
        waitsFactory.explicitWait(imgLinkedin);
        Assert.assertTrue(imgTwitter.isDisplayed(), "Twitter footer image not displayed");
        Assert.assertTrue(imgFacebook.isDisplayed(), "Facebook footer image not displayed");
        Assert.assertTrue(imgLinkedin.isDisplayed(), "LinkedIn footer image not displayed");
    }

}