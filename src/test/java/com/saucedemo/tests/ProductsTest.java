package com.saucedemo.tests;

import org.testng.annotations.Test;

import com.saucedemo.core.BaseClass;
import com.saucedemo.pages.Login;
import com.saucedemo.pages.Products;
import com.saucedemo.utilities.ConfigReader;
import com.saucedemo.utilities.TestDataProvider;

public class ProductsTest extends BaseClass {

    @Test
    public void login() {
        String correctUsername = new ConfigReader().getProperty("standard_user");
        String correctPassword = new ConfigReader().getProperty("password");
        Login login = new Login(driver);
        login.loginPage(correctUsername, correctPassword);
    }

    @Test(dependsOnMethods = { "login" }, dataProvider = "dtpProductsInfo", dataProviderClass = TestDataProvider.class, groups = "validate_all_products_info", priority = 1)
    public void validateAllProductInfo(String productName, String description, String price) {
        new Products(driver).allProductsInfo(productName, description, price);
    }

    @Test(dataProvider = "dtpIndividualProductsLocators", dataProviderClass = TestDataProvider.class, priority = 2)
    public void validateIndividualProductsLocator(String linkLocator) {
        new Products(driver).individualProductsInfo(linkLocator.toString());
    }

    @Test(priority = 3)
    public void validateButtonTextChange() {
        new Products(driver).validateRemoveButton();
    }

    @Test(priority = 4)
    public void productsImages() {
        new Products(driver).validateProductsImages();
    }

    @Test(priority = 5)
    public void verifySocialMediaImages() {
        new Products(driver).socialMediaImages();
    }

}