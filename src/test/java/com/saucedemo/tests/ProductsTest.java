package com.saucedemo.tests;

import org.testng.annotations.Test;

import com.saucedemo.core.BaseClass;
import com.saucedemo.pages.Products;
import com.saucedemo.utilities.TestDataProvider;

public class ProductsTest extends BaseClass {
    // dependsOnGroups = { "tests.LoginTest.successful_login" },
    // groups = { "validate_all_products_info" },
    @Test(dataProvider = "dtpProductsInfo", dataProviderClass = TestDataProvider.class)
    public void validateAllProductInfo(String productName, String description, String price) {
        new Products(driver).allProductsInfo(productName, description, price);
    }

    @Test
    public void validateButtonTextChange() {
        new Products(driver).validateRemoveButton();
    }

    @Test(dataProvider = "dtpIndividualProductsLocators", dataProviderClass = TestDataProvider.class)
    public void validateIndividualProductsLocator(String linkLocator) {
        new Products(driver).individualProductsInfo(linkLocator);
    }

    @Test
    public void productsImages() {
        new Products(driver).validateProductsImages();
    }

    @Test
    public void verifySocialMediaImages() {
        new Products(driver).socialMediaImages();
    }

}