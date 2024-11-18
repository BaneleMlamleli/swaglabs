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

    WaitsFactory waitsFactory = new WaitsFactory();

    Logger logger = LogManager.getLogger(new Object() {
    }.getClass().getName());

    public Products(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, Products.class);
    }

    /**
     * Validates the information of all products on the page.
     * It checks for the presence of specific product names, descriptions, and
     * prices.
     * It also verifies the functionality of the "Add to Cart" button.
     */
    public void validateAllProductsInfo() {
        String allProducts = allProductsInfo.getText();
        Assert.assertTrue(allProducts.contains("ADD TO CART"));
        Assert.assertTrue(allProducts.contains("Sauce Labs Backpack"));
        Assert.assertTrue(allProducts.contains("Sauce Labs Bolt T-Shirt"));
        Assert.assertTrue(allProducts.contains("Sauce Labs Onesie"));
        Assert.assertTrue(allProducts.contains("Sauce Labs Bike Light"));
        Assert.assertTrue(allProducts.contains("Sauce Labs Fleece Jacket"));
        Assert.assertTrue(allProducts.contains("Test.allTheThings() T-Shirt (Red)"));
        Assert.assertTrue(allProducts.contains("$29.99"));
        Assert.assertTrue(allProducts.contains("$15.99"));
        Assert.assertTrue(allProducts.contains("$7.99"));
        Assert.assertTrue(allProducts.contains("$9.99"));
        Assert.assertTrue(allProducts.contains("$49.99"));
        Assert.assertTrue(allProducts.contains("$15.99"));
        Assert.assertTrue(allProducts.contains(
                "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection."));
        Assert.assertTrue(allProducts.contains(
                "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included."));
        Assert.assertTrue(allProducts.contains(
                "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt."));
        Assert.assertTrue(allProducts.contains(
                "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office."));
        Assert.assertTrue(allProducts.contains(
                "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel."));
        Assert.assertTrue(allProducts.contains(
                "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel."));

        driver.findElement(By.xpath("//div[@class='inventory_list']//div[1]//div[3]//button[1]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//button[contains(text(),\"REMOVE\")]")).isDisplayed());
    }

    /**
     * Validates the information of an individual product by navigating to its
     * details page.
     * It checks for the presence of specific product name, description, and price.
     * It also verifies the functionality of the "Add to Cart" and "Remove" buttons.
     */
    public void validateIndividualProductsInfo() {
        driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Backpack']")).click();

        WebElement btnBack = driver.findElement(By.xpath("//button[@class='inventory_details_back_button']"));
        WebElement imgBackpack = driver.findElement(By.xpath("//img[@class='inventory_details_img']"));
        WebElement btnAddToCart = driver.findElement(By.xpath("//button[@class='btn_primary btn_inventory']"));
        WebElement btnRemove = driver.findElement(By.xpath("//button[contains(text(),\"REMOVE\")]"));
        WebElement itemName = driver.findElement(By.xpath("//div[@class='inventory_details_name']"));
        WebElement itemDesc = driver.findElement(By.xpath("//div[@class='inventory_details_desc']"));
        WebElement itemPrice = driver.findElement(By.xpath("//div[@class='inventory_details_price']"));

        // verify image is displayed
        Boolean p = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete "
                + "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0",
                imgBackpack);
        Assert.assertTrue(p, "Error with item image");

        String name = itemName.getText();
        String desc = itemDesc.getText();
        String price = itemPrice.getText();
        Assert.assertTrue(itemName.isDisplayed() && name.contains("Sauce Labs Backpack"), "Error with item name");
        Assert.assertTrue(itemDesc.isDisplayed() && desc.contains(
                "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection."),
                "Error with item description");
        Assert.assertTrue(itemPrice.isDisplayed() && price.contains("$29.99"), "Error with price");

        Assert.assertTrue(btnBack.isDisplayed(), "'Back' button not displayed");

        btnAddToCart.click();
        Assert.assertTrue(btnRemove.isDisplayed());

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
            System.out.println("The page under test has " + productsImageList.size() + " images");
            for (WebElement img : productsImageList) {
                if (img != null) {
                    if (img.getAttribute("naturalWidth").equals("0")) {
                        System.out.println(img.getAttribute("outerHTML") + " is broken.");
                        brokenImageCounter++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println("The page " + driver.getCurrentUrl() + " has " + brokenImageCounter + " broken images");
    }

    /**
     * Validates that social media images (Twitter, Facebook, LinkedIn) are
     * displayed.
     */
    public void socialMediaImages() {
        waitsFactory.explicitWait(imgTwitter);
        waitsFactory.explicitWait(imgFacebook);
        waitsFactory.explicitWait(imgLinkedin);
        Assert.assertTrue(imgTwitter.isDisplayed(), "Twitter image not displayed");
        Assert.assertTrue(imgFacebook.isDisplayed(), "Facebook image not displayed");
        Assert.assertTrue(imgLinkedin.isDisplayed(), "LinkedIn image not displayed");
    }

}