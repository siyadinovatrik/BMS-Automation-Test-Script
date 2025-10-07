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
  @AfterTest
  public void teardown()
  {
	  page.close();
	  browser.close();
	  playwright.close();
  }
}






  
