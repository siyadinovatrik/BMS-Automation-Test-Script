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
        ob = new BMSPage(page);
    }
    
    @Test(priority = 0)
    public void verifyUrlTest() {
        ob.verifyUrl();
    }
    
    @Test(priority = 1)
    public void verifyTitleTest() {
        ob.verifyTitle();
    }
    
    @Test(priority = 2)
    public void verifyBrokenLinksTest() throws Exception {
        ob.verifyBrokenLink();
    }
    
    @Test(priority = 3)
    public void verifyCheckNavigationFlow() throws Exception {
        ob.CheckNavigationFlow();
    }
    
    @Test(priority = 4)
    public void verifyLogin() throws Exception {
        ob.login();
    }
    
    @Test(priority = 5)
    public void verifyEditProfile() throws Exception {
        ob.editProfile();
    }
    
    @Test(priority = 6)
    public void verifyAddAddress() throws Exception {
        ob.addAddress();
    }
    
    @Test(priority = 7)
    public void verifyRemoveAddress() {
        ob.removeAddress();
    }
    
    @Test(priority = 8)
    public void verifyMRIAbdomenBook() throws Exception {
        ob.MRIAbdomenBook();
    }
    
    @Test(priority = 9)
    public void verifyMRIBrainBook() {
        ob.MRIBrainBook();
    }
    
    @Test(priority = 10)
    public void verifyCervicalMRIBook() {
        ob.CervicalSpineBook();
    }
    
    @Test(priority = 11)
    public void verifyKneeMRIBook() {
        ob.KneeMRIBook();
    }
    
    @Test(priority = 12)
    public void verifyCTBook() throws Exception {
        ob.CTbook();
    }
    
    @Test(priority = 13)
    public void verifyCTAbdomenBook() throws Exception {
        ob.CTAdbomenBook();
    }
    
    @Test(priority = 14)
    public void verifyCTBrainBook() throws Exception {
        ob.CTBrainScanBook();
    }
    
    @Test(priority = 15)
    public void verifyCTChestBook() throws Exception {
        ob.CTChestScanBook();
    }
    
    @Test(priority = 16)
    public void verifyNeckBook() throws Exception {
        ob.CTNeckScanBook();
    }
    
    @Test(priority = 17)
    public void verifyCTPNSScanBook() throws Exception {
        ob.CTPNSSCanBook();
    }
    
    @Test(priority = 18)
    public void verifyPETCTBook() throws Exception {
        ob.PETCTBook();
    }
    
    @Test(priority = 19)
    public void verifyEditMobileLogin() throws Exception {
        ob.EditMobileLogin();
    }
    
    @Test(priority = 20)
    public void verifyResendOTP() throws Exception {
        ob.ResendOTP();
    }
    
    @Test(priority = 21)
    public void verifyInvalidOTP() throws Exception {
        ob.InvalidOTP();
        String toastText = page.textContent("text=OTP Verification Failed");
        Assert.assertEquals(toastText.trim(), "OTP Verification Failed",
                "Toast message text does not match expected value.");
    }
    
    @Test(priority = 22)
    public void verifyMasterHealthCheckup() throws Exception {
        ob.BookSlverMasterHealthcheckup();
    }
    
    @Test(priority = 23)
    public void verifyGoldMHCBook() throws Exception {
        ob.BookGoldMHCBook();
    }
    
    @Test(priority = 24)
    public void verifyXRAYBook() throws Exception {
        ob.XRAYBook();
    }
    
    @Test(priority = 25)
    public void verifyUSGBook() throws Exception {
        ob.bookUSG();
    }
    
    @Test(priority = 26)
   public void verifyECGBook() throws Exception {
        ob.bookECG();
    }

    @Test(priority = 27)
    public void testScreenshotCapture() throws Exception {
        throw new AssertionError("Intentional failure to test screenshot capture");
    }

    @AfterTest
    public void teardown() {
        tearDown();
    }
}