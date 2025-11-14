package test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BMSBase;
import page.BMSPage;

public class BMSTest extends BMSBase
{
  BMSPage ob;
  
  @BeforeTest
  public void verifsetup()
  {
	  setup();
	  ob = new BMSPage(page);
  }
  @Test(priority=0)
  public void VeriFURL()
  {
	  ob.Verifurl();
  }
  @Test(priority=1)
  public void TittleVeif()
  {
	  ob.Veriftittle();
  }
  @Test(priority=2)
  public void VeriFyBrokenLinks() throws Exception
  {
	  ob.VerifBrokenLikn();
  }
  @Test(priority=3)
  public void VerifCheckNavigationFlow() throws Exception
  {
	  ob.CheckNavigationFlow();
  }
/*  @Test(priority=4)
  public void verifLogin() throws Exception
  {
	 ob.login(); 
  }
  @Test(priority=5)
  public void VerifEditProfile() throws Exception
  {
	  ob.editProfile();
  }
  @Test(priority=6)
  public void VerifAddaddress() throws Exception
  {
	  ob.Addaddress();
  }  
  @Test(priority=7)
  public void VerifMRIBook() throws Exception
  {
	  ob.MRIBook();
  }
  @Test(priority=8)
  public void VerifCTBook() throws Exception
  {
	  ob.CTbook();
  } 
  @Test(priority=9)
  public void VerifPETCTBook() throws Exception
  {
	  ob.PETCTBook();
  }
  @Test(priority=10)
  public void VerifEditMobileLogin() throws Exception
  {
	  ob.EditMobileLogin();
  }
  @Test(priority=11)
  public void VerifResendOTP() throws Exception
  {
	  ob.ResendOTP();
  }
  @Test(priority=12)
  public void VerifInvalidOTP() throws Exception
  {
	  ob.InvalidOTP();
  }*/
  @AfterTest
  public void teardown()
  {
	  page.close();
	  browser.close();
	  playwright.close();
  }
}






  
