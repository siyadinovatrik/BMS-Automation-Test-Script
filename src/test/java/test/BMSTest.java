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
 /* @Test(priority=1)
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
  @Test(priority=4)
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
   public void verifRemoveAddress()
   {
	  ob.removeAddress();
   }*/
  @Test(priority=8)
  public void VerifMRIAbdomenBook() throws Exception
  {
	  ob.MRIAbdomenBook();
  }
  @Test(priority=9)
  public void VerifMRIBrainBook()
  {
	  ob.MRIBrainBook();
  }
/*  @Test(priority=10)
  public void VerifCervicalMRIBook()
  {
	  ob.CervicalSpineBook();
  }
  @Test(priority=11)
  public void VerifKneeMRIBook()
  {
	  ob.KneeMRIBook();
  }*/
  @Test(priority=12)
  public void VerifCTBook() throws Exception
  {
	  ob.CTbook();
  } 
/*  @Test(priority=13)
  public void VerifCTAdmonBook() throws Exception
  {
	  ob.CTAdbomenBook();
  }
  @Test(priority=14)
  public void verifCTBrainBook()
  {
	  ob.CTBrainScanBook();
  }
  @Test(priority=15)
  public void VeriCTChestBook()
  {
	  ob.CTChestScanBook();
  }
  @Test(priority=16)
  public void VerifNeckBook()
  {
	  ob.CTNeckScanBook();
  }
  @Test(priority=17)
  public void VerifCTPNSScanBook()
  {
	  ob.CTPNSSCanBook();
  }
  @Test(priority=18)
  public void VerifPETCTBook() throws Exception
  {
	  ob.PETCTBook();
  }
/*  @Test(priority=19)
  public void VerifEditMobileLogin() throws Exception
  {
	  ob.EditMobileLogin();
  }
  @Test(priority=20)
  public void VerifResendOTP() throws Exception
  {
	  ob.ResendOTP();
  }
  @Test(priority=21)
  public void VerifInvalidOTP() throws Exception
  {
	  ob.InvalidOTP();
  }
  @Test(priority=22)
  public void VerifMasterHealthchckup() throws Exception
  {
	  ob.BookMasterHealthcheckup();
  }
  @Test(priority=23)
  public void VerifXRAYBook() throws Exception
  {
	  ob.XRAYBook();
  }
  @Test(priority=24)
  public void VerifUSGBook() throws Exception
  {
	  ob.bookUSG();
  }
  @Test(priority=25)
  public void verifEchobook() throws Exception
  {
	  ob.bookECHO();
  }*/
  @AfterTest
  public void teardown()
  {
	  page.close();
	  browser.close();
	  playwright.close();
  }
}






  
