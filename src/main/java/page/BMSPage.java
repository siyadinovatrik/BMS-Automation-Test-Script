package page;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

import org.testng.Assert;


public class BMSPage {

	  public Page page;
	    
	    public BMSPage(Page page) {
	        this.page = page;
	    }
	    
	  public void Verifurl()
	  {
		 String URl= page.url();
		 if(URl.equals("https://bms.mapskil.com/"))
		 {
			 System.out.println(URl + "URl verified");
		 }
		 else
		 {
			 System.out.println("URL not verified");
		 }
	  }
	  public void Veriftittle()
	  {
		 String Tittle=page.title();
		 System.out.println(Tittle);
		 if(Tittle.equals(Tittle))
		 {
			 System.out.println("Tittle verified");
		 }
		 else {
			 System.out.println("Tittle not verified");
		 }
	  }
	  public void VerifBrokenLikn() throws Exception
	  {
		  Thread.sleep(3000);
		  page.waitForLoadState();
		  List<ElementHandle> links = page.querySelectorAll("a");
	        System.out.println(" Total links found: " + links.size());

	        for (ElementHandle link : links) {
	            String url = link.getAttribute("href");

	            if (url == null || url.isEmpty() || url.startsWith("#") || url.startsWith("javascript")) {
	                continue;
	            }

	            checkLink(url);
	        }
	    }

	    private void checkLink(String url) {
	        try {
	            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	            connection.setRequestMethod("HEAD");
	            connection.connect();

	            int statusCode = connection.getResponseCode();

	            if (statusCode >= 400) {
	                System.out.println(" Broken link: " + url + " | Status: " + statusCode);
	            } else {
	                System.out.println(" Valid link: " + url + " | Status: " + statusCode);
	            }

	            connection.disconnect();
	        } catch (Exception e) {
	            System.out.println(" Exception while checking: " + url + " | Message: " + e.getMessage());
	        }
	    }
	
	  public void login() throws Exception
	  {
		      page.waitForLoadState();
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login/ Sign Up")).click();
		      page.getByPlaceholder("Enter Mobile Number").click();
		      page.getByPlaceholder("Enter Mobile Number").fill("9778350198");
		      page.waitForLoadState();
		      Thread.sleep(3000);
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send OTP")).click();
		      Thread.sleep(2000);
		      page.getByRole(AriaRole.TEXTBOX).first().fill("1");
		      page.getByRole(AriaRole.TEXTBOX).nth(1).fill("2");
		      page.getByRole(AriaRole.TEXTBOX).nth(2).fill("3");
		      page.getByRole(AriaRole.TEXTBOX).nth(3).fill("4");
		      Thread.sleep(2000);
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
		      Thread.sleep(3000);
		      assertThat(page.getByRole(AriaRole.BANNER)).containsText("My Account");
	  }
	  public void editProfile() throws Exception
	  {
		  page.waitForLoadState();
		  page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/div/div[1]/div[2]/button").click();
		  page.waitForLoadState();
	      page.getByPlaceholder("Enter Name").click();
	      page.getByPlaceholder("Enter Name").fill("Siyad Testt");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
	      Thread.sleep(2000);
	      page.waitForLoadState();
	      assertThat(page.getByRole(AriaRole.LIST)).containsText("Siyad Testt");
	  }
	  public void Addaddress() throws Exception
	  {
		  Thread.sleep(2000);
		  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add Address")).click();
	      page.getByPlaceholder("Flat /Unit No. & Apartment or").click();
	      page.getByPlaceholder("Flat /Unit No. & Apartment or").fill("KATHAR TDPA KOCHI Ernakulam");
	      page.getByRole(AriaRole.COMBOBOX).first().click();
	      page.getByText("Cochin", new Page.GetByTextOptions().setExact(true)).click();
	      page.getByPlaceholder("Enter Pincode").click();
	      page.getByPlaceholder("Enter Pincode").fill("685584");
	      page.getByRole(AriaRole.COMBOBOX).nth(1).click();
	      page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Kerala")).click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
	      Thread.sleep(2000);
	      page.waitForLoadState();
	      assertThat(page.locator("#root")).containsText("KATHAR TDPA KOCHI Ernakulam Kerala Cochin 685584");
	  }
	  public void removeAddress()
	  {
		  page.waitForLoadState();
	      page.locator(".MuiGrid-root > div > div:nth-child(2) > div:nth-child(2) > button").first().click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")).click();
	  }
	  public void MRIBook() throws Exception
	  {
		  page.waitForLoadState();
		 Thread.sleep(2000);
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("MRI Scan").setExact(true)).click();
	      Thread.sleep(2000);
	      page.getByPlaceholder("Enter Full Name").click();
	      page.getByPlaceholder("Enter Full Name").fill("SIYADTEST");
	      page.getByPlaceholder("Enter Phone Number").click();
	      page.getByPlaceholder("Enter Phone Number").fill("9778350197");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book an MRI Scan")).click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).fill("1234");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.getByPlaceholder("Select date").first().fill("2025-10-31");
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Select time")).click();
	      page.getByText("11:45 AM").click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      Thread.sleep(2000);
	      assertThat(page.locator("body")).containsText("Booking Confirmed!");
	      page.locator("button").filter(new Locator.FilterOptions().setHasText("Back to Home")).first().click();
	    }
	  public void CTbook() throws Exception
	  {
		  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("CT Scan")).click();
		  page.waitForLoadState();
	      page.getByPlaceholder("Enter Full Name").click();
	      page.getByPlaceholder("Enter Full Name").fill("SIYADCTTEST");
	      page.getByPlaceholder("Enter Phone Number").click();
	      page.getByPlaceholder("Enter Phone Number").fill("9778350197");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book a CT Scan")).click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).fill("1234");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.getByPlaceholder("Select date").first().fill("2025-10-31");
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Select time")).click();
	      page.getByText("12:00 PM").click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      Thread.sleep(2000);
	      assertThat(page.locator("body")).containsText("Booking Confirmed!");
	      page.locator("button").filter(new Locator.FilterOptions().setHasText("Back to Home")).first().click();
	  }
	  public void PETCTBook() throws Exception
	  {
		  page.navigate("https://bms.mapskil.com/");
		  page.waitForLoadState();
          page.evaluate("window.scroll({ top: document.body.scrollHeight, behavior: 'smooth' })");
          Thread.sleep(2000);
		  page.locator("//*[@id=\"root\"]/main/div[2]/div/div[3]/div[1]/div[1]/div[3]/p").click();
		  page.waitForLoadState();
	      page.getByPlaceholder("Enter Full Name").click();
	      page.getByPlaceholder("Enter Full Name").fill("SIYADDPETTEST");
	      page.getByPlaceholder("Enter Phone Number").click();
	      page.getByPlaceholder("Enter Phone Number").fill("9778350197");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book a PET-CT Scan")).click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).fill("1234");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.getByPlaceholder("Select date").first().fill("2025-10-30");
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Select time")).click();
	      page.getByText("12:30 PM").click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      Thread.sleep(2000);
	      assertThat(page.locator("body")).containsText("Booking Confirmed!");
	      page.getByText("View my Booking").first().click();
	  }
	  public void EditMobileLogin() throws Exception
	  {
		  Thread.sleep(2000);
		  page.navigate("https://bms.mapskil.com/");
		  page.waitForLoadState();
		  page.locator("//*[@id=\"root\"]/header/div[2]/div/div/div[2]/div[3]/button").click();
		  Thread.sleep(2000);
		  page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[1]/div/div[2]/div[3]/div/div[2]/div/button").click();
		  Thread.sleep(3000);
		   page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login/ Sign Up")).click();
		      page.getByPlaceholder("Enter Mobile Number").click();
		      page.getByPlaceholder("Enter Mobile Number").fill("9778350198");
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send OTP")).click();
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit Mobile Number")).click();
		      assertThat(page.getByPlaceholder("Enter Mobile Number")).isVisible();
	  }
	  public void ResendOTP() throws Exception
	  {
		  page.waitForLoadState();
		  page.getByPlaceholder("Enter Mobile Number").click();
	      page.getByPlaceholder("Enter Mobile Number").fill("9778350198");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send OTP")).click();
	      Thread.sleep(35000);
	      assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Resend OTP"))).isVisible();
	  }
	  public void InvalidOTP() throws Exception
	  {
		  page.reload();
		  page.waitForLoadState();
		  Thread.sleep(2000);
		  page.locator("//*[@id=\"root\"]/header/div[2]/div/div/div[2]/div[3]/button").click();
		  page.waitForLoadState();
		  page.getByPlaceholder("Enter Mobile Number").click();
	      page.getByPlaceholder("Enter Mobile Number").fill("9778350198");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send OTP")).click();
	      Thread.sleep(2000);
	      page.getByRole(AriaRole.TEXTBOX).first().fill("0");
	      page.getByRole(AriaRole.TEXTBOX).nth(1).fill("0");
	      page.getByRole(AriaRole.TEXTBOX).nth(2).fill("0");
	      page.getByRole(AriaRole.TEXTBOX).nth(3).fill("0");
	      Thread.sleep(2000);
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
	      Thread.sleep(1000);
	      String toastText = page.textContent("text=OTP Verification Failed");
	      Assert.assertEquals(toastText.trim(), "OTP Verification Failed",
	              "Toast message text does not match expected value.");
	      
	  }
	  public void CheckNavigationFlow() throws Exception
	  {
		  page.navigate("https://bmsuat.inovatrik.com/");
		  page.waitForLoadState();
		  page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("https://bms-prod-2024.s3.ap-south-1.amazonaws.com/assets/mri.svg")).click();
		  Thread.sleep(2000);
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Premium MRI Scans");
	      page.getByText("BOOK MY SCANS").nth(1).click();
	      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("https://bms-prod-2024.s3.ap-south-1.amazonaws.com/assets/CT.svg")).click();
		  Thread.sleep(2000);
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Premium CT Scans");
	      page.getByText("BOOK MY SCANS").nth(1).click();
	      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("https://bms-prod-2024.s3.ap-south-1.amazonaws.com/assets/PET.svg")).click();
		  Thread.sleep(2000);
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Premium PET-CT Scans");
	      page.getByText("BOOK MY SCANS").nth(1).click();
	      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("https://bms-prod-2024.s3.ap-south-1.amazonaws.com/assets/MHC.svg")).click();
		  Thread.sleep(2000);
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Master Health Checkup");
	      page.getByText("BOOK MY SCANS").nth(1).click();
	      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("https://bms-prod-2024.s3.ap-south-1.amazonaws.com/assets/HC.svg")).click();
		  Thread.sleep(2000);
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Health Checkups");
	      page.getByText("BOOK MY SCANS").nth(1).click();
	      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("https://bms-prod-2024.s3.ap-south-1.amazonaws.com/assets/compareLab.svg")).click();
		  Thread.sleep(2000);
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Compare And Book");
	      page.getByText("BOOK MY SCANS").nth(1).click();
	      page.waitForLoadState();
	      Thread.sleep(2000);
	      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("https://bms-prod-2024.s3.ap-south-1.amazonaws.com/assets/HC.svg")).click();
	      page.waitForLoadState();
	      page.getByText("Preventive").nth(1).scrollIntoViewIfNeeded();
	      page.getByText("Preventive").nth(1).click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Preventive Packages");
	      Thread.sleep(2000);
	      page.navigate("https://bmsuat.inovatrik.com/bangalore/health-checkup");
	      page.getByText("Senior").nth(1).scrollIntoViewIfNeeded();
	      page.getByText("Senior").first().click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Senior Packages");
	      Thread.sleep(2000);
	      page.goBack();
	      
	    }
	  public void BookMasterHealthcheckup() throws Exception
	  {
		  Thread.sleep(2000);
		  page.navigate("https://bmsuat.inovatrik.com/");
		  page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Master Health Checkup")).click();
	      page.getByText("View Package").nth(1).scrollIntoViewIfNeeded();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("View Package")).first().click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.waitForLoadState();
	      page.getByPlaceholder("Name").fill("siyaDTESTMAster");
	      page.getByPlaceholder("Mobile Number").fill("9778350198");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Generate OTP")).click();
	      page.waitForLoadState();
	      Thread.sleep(2000);
	      page.getByPlaceholder("OTP").fill("1234");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Verify OTP")).click();
	      page.waitForLoadState();
	      page.getByPlaceholder("Age").fill("22");
	      page.getByText("Select Gender").click();
	      Thread.sleep(2000);
	      page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Male").setExact(true)).click();
	      page.getByPlaceholder("Address").fill("ABCSDDDC");
	      page.getByPlaceholder("Pincode").fill("685585");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("ADD MORE MEMBERS")).click();
	      page.waitForLoadState();
	      page.getByPlaceholder("Name").fill("RARA");
	      page.getByPlaceholder("Age").fill("222");
	      page.getByText("Select Gender").click();
	      Thread.sleep(2000);
	      page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Female")).click();
	      page.getByRole(AriaRole.CHECKBOX).first().check();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
	      page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      Thread.sleep(2000);
	      page.getByPlaceholder("Select date").fill("2025-11-27");
	      page.locator("body > div.MuiModal-root.css-8ndowl > div.MuiStack-root.css-j7qwjs > div > div > div.MuiStack-root.css-1lw5nbc > div.MuiStack-root.css-un51iw > div.MuiStack-root.css-hiw9ve > div.MuiStack-root.css-v0zhh > div > div:nth-child(2) > div > p").click();
	      page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Review booking")).click();
	      Thread.sleep(2000);
	      page.getByLabel("Pay Now").check();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm Booking")).click();
	      Thread.sleep(2000);
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed To Pay")).click();
	      page.waitForLoadState();
	      page.locator("li").filter(new Locator.FilterOptions().setHasText("Net Banking")).click();
	      page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Test bank$"))).first().click();
	      page.locator("#net-banking-list-TESTPGNB-pop").getByTestId("netbanking-proceed-btn-TESTPGNB").click();
	      page.getByPlaceholder("Enter payu as username").click();
	      page.getByPlaceholder("Enter payu as username").fill("payu");
	      page.locator("#CredForm div").filter(new Locator.FilterOptions().setHasText("Password Kindly enter valid")).locator("div").click();
	      page.getByPlaceholder("Enter payu as password").fill("payu");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Simulate Success Response")).click();
	      assertThat(page.getByRole(AriaRole.PARAGRAPH)).containsText("Your payment was successful!");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Go to Home")).click();
	  }
	  public void XRAYBook() throws Exception
	  {
		  page.navigate("https://bmsuat.inovatrik.com/");
		   page.waitForLoadState();
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login/ Sign Up")).click();
		      page.getByPlaceholder("Enter Mobile Number").fill("9778350198");
		      page.waitForLoadState();
		      Thread.sleep(3000);
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send OTP")).click();
		      Thread.sleep(2000);
		      page.getByRole(AriaRole.TEXTBOX).first().fill("1");
		      page.getByRole(AriaRole.TEXTBOX).nth(1).fill("2");
		      page.getByRole(AriaRole.TEXTBOX).nth(2).fill("3");
		      page.getByRole(AriaRole.TEXTBOX).nth(3).fill("4");
		      Thread.sleep(2000);
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
	      page.getByText("BOOK MY SCANS").nth(1).click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Scans")).hover();
          Thread.sleep(2000);
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("X-Ray")).first().click();
	      page.getByLabel("Aarthi Scans & Labs").first().scrollIntoViewIfNeeded();
          page.locator("#root > main > div.MuiStack-root.t3-main-container.css-1xbe40y > div.MuiStack-root.css-aiww9g > div.MuiStack-root.css-pdv2i8 > div > div > div > div.MuiStack-root.css-cy91f0 > div.MuiStack-root.css-17bqjow > div.MuiStack-root.css-c98prh > div > div:nth-child(1) > div > div > div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-lg-12.css-ucashc > div.MuiStack-root.css-ooasvr > div > button > p > p").first().click();
	      page.waitForLoadState();
	      page.getByPlaceholder("Select time slot").click();
	      Thread.sleep(2000);
	      page.locator("body > div.MuiBox-root.css-1dvep9k > div.MuiBox-root.css-19p0syp > div:nth-child(2)").click();
	      page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Get OTP")).click();
	      Thread.sleep(2000);
	      page.getByRole(AriaRole.SPINBUTTON).first().fill("1");
	      page.getByRole(AriaRole.SPINBUTTON).nth(1).fill("2");
	      page.getByRole(AriaRole.SPINBUTTON).nth(2).fill("3");
	      page.getByRole(AriaRole.SPINBUTTON).nth(3).fill("4");
	      page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Verify OTP")).click();
	      Thread.sleep(2000);
	      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("IMAGES.APP_BAR.CART_LOGO")).click();
	      Thread.sleep(2000);
	      assertThat(page.locator("body > div:nth-child(13) > div.MuiStack-root.css-j7qwjs > div > div > div > div.MuiStack-root.css-1deewkp > div.MuiStack-root.css-14chpeu > div > div.MuiStack-root.css-1yhhhfm > div.MuiStack-root.css-ba97s9 > div > div > div.MuiStack-root.css-em1apr > p.MuiTypography-root.MuiTypography-body1.body.css-1sjy5zq")).containsText("Aarthi");
	      page.getByRole(AriaRole.BUTTON).first().click();
      }
 }
  














