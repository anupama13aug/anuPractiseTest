package org.anutestframework.Tests;

import org.anutestframework.TestComponents.BaseTest;
import org.anutestframework.TestComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginErrorValidation extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginErrorValidation() throws IOException, InterruptedException {

        loginPage.loginApplication("anuTest@gmail.com", "Welcome123");
        Assert.assertEquals( loginPage.getErrorMessage(), "Incorrect email or password.");

    }

}