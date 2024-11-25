package com.saucedemo.tests;

import org.testng.annotations.Test;

import com.saucedemo.core.BaseClass;
import com.saucedemo.pages.Products;
import com.saucedemo.utilities.TestDataProvider;

public class ProductsTest extends BaseClass {
    // dependsOnGroups = { "tests.LoginTest.successful_login" },
    // groups = { "validate_all_products_info" },
    @Test(dataProvider = "dtpProductsInfo", dataProviderClass = TestDataProvider.class, priority = 1)
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