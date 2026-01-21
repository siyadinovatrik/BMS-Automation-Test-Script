package test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BMSBase;

import org.testng.Assert;
import page.BMSPage;

public class BMSTest extends BMSBase {
    BMSPage ob;
    
    @BeforeTest
    public void verifsetup() {
        setup();
        ob = new BMSPage(page, playwright, browser);
    }
    
    @Test(priority = 0, groups = {"smoke", "sanity", "regression"})
    public void verifyUrlTest() {
        ob.verifyUrl();
    }

    @Test(priority = 1, groups = {"smoke", "sanity", "regression"})
    public void verifyTitleTest() {
        ob.verifyTitle();
    }

    @Test(priority = 2, groups = {"smoke", "regression"})
    public void verifyBrokenLinksTest() throws Exception {
        ob.verifyBrokenLink();
    }

    @Test(priority = 3, groups = {"smoke", "regression"})
    public void verifyCheckNavigationFlow() throws Exception {
        ob.CheckNavigationFlow();
    }

    @Test(priority = 4, groups = {"smoke", "sanity", "profile", "regression"})
    public void verifyLogin() throws Exception {
        ob.login();
    }
    
    @Test(priority = 5, groups = {"profile", "regression"})
    public void verifyEditProfile() throws Exception {
        ob.editProfile();
    }

    @Test(priority = 6, groups = {"sanity", "profile", "regression"})
    public void verifyAddAddress() throws Exception {
        ob.addAddress();
    }

    @Test(priority = 7, groups = {"profile", "regression"})
    public void verifyRemoveAddress() {
        ob.removeAddress();
    }
    
    @Test(priority = 8, groups = {"sanity", "booking", "regression"})
    public void verifyMRIAbdomenBook() throws Exception {
        ob.MRIAbdomenBook();
    }

    @Test(priority = 9, groups = {"sanity", "booking", "regression"})
    public void verifyMRIBrainBook() {
        ob.MRIBrainBook();
    }

    @Test(priority = 10, groups = {"booking", "regression"})
    public void verifyCervicalMRIBook() {
        ob.CervicalSpineBook();
    }

    @Test(priority = 11, groups = {"booking", "regression"})
    public void verifyKneeMRIBook() {
        ob.KneeMRIBook();
    }

    @Test(priority = 12, groups = {"sanity", "booking", "regression"})
    public void verifyCTBook() throws Exception {
        ob.CTbook();
    }
    
    @Test(priority = 13, groups = {"booking", "regression"})
    public void verifyCTAbdomenBook() throws Exception {
        ob.CTAdbomenBook();
    }

    @Test(priority = 14, groups = {"booking", "regression"})
    public void verifyCTBrainBook() throws Exception {
        ob.CTBrainScanBook();
    }

    @Test(priority = 15, groups = {"booking", "regression"})
    public void verifyCTChestBook() throws Exception {
        ob.CTChestScanBook();
    }

    @Test(priority = 16, groups = {"booking", "regression"})
    public void verifyNeckBook() throws Exception {
        ob.CTNeckScanBook();
    }

    @Test(priority = 17, groups = {"booking", "regression"})
    public void verifyCTPNSScanBook() throws Exception {
        ob.CTPNSSCanBook();
    }

    @Test(priority = 18, groups = {"booking", "regression"})
    public void verifyPETCTBook() throws Exception {
        ob.PETCTBook();
    }
    
    @Test(priority = 19, groups = {"profile", "regression"})
    public void verifyEditMobileLogin() throws Exception {
        ob.EditMobileLogin();
    }

    @Test(priority = 20, groups = {"profile", "regression"})
    public void verifyResendOTP() throws Exception {
        ob.ResendOTP();
    }

    @Test(priority = 21, groups = {"profile", "regression"})
    public void verifyInvalidOTP() throws Exception {
        ob.InvalidOTP(); 
        String toastText = page.textContent("text=OTP Verification Failed");
        Assert.assertEquals(toastText.trim(), "OTP Verification Failed",
                "Toast message text does not match expected value.");
    } 
    
    @Test(priority = 22, groups = {"healthcheckup", "regression"})
    public void verifyMasterHealthCheckup() throws Exception {
        ob.BookSlverMasterHealthcheckup();
    }

    @Test(priority = 23, groups = {"healthcheckup", "regression"})
    public void verifyGoldMHCBook() throws Exception {
        ob.BookGoldMHCBook();
    }
    @Test(priority= 24, groups = {"healthcheckup","regression"})
    public void VerifplatinumMHCBook() throws Exception
    {
    	ob.BookPlatinumMHCBook();
    }
    @Test(priority = 25, groups = {"booking", "regression"})
    public void verifyXRAYBook() throws Exception {
        ob.XRAYBook();
    }

    @Test(priority = 26, groups = {"booking", "regression"})
    public void verifyUSGBook() throws Exception {
        ob.bookUSG();
    }

    @Test(priority = 27, groups = {"booking", "regression"})
    public void verifyECGBook() throws Exception {
        ob.bookECG();
    }

  /* * @Test(priority = 28, groups = {"regression"})
    public void testScreenshotCapture() throws Exception {
        // Navigate to a page first to ensure page is properly initialized
        page.navigate(ConfigReader.getUatUrl());
        page.waitForLoadState();

        // Now throw intentional failure to test screenshot capture
        throw new AssertionError("Intentional failure to test screenshot capture");
    }*/

    @AfterTest
    public void teardown() {
        tearDown();
    }
}