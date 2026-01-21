package page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import config.ConfigReader;



public class BMSPage {

	  public Page page;
	  public Playwright playwright;
	  public Browser browser;

	    public BMSPage(Page page) {
	        this.page = page;
	    }

	    public BMSPage(Page page, Playwright playwright, Browser browser) {
	        this.page = page;
	        this.playwright = playwright;
	        this.browser = browser;
	    }
	    
	  public void verifyUrl()
	  {
		 String url= page.url();
		 if(url.equals("https://bms.mapskil.com/"))
		 {
			 System.out.println(url + " URL verified");
		 }
		 else
		 {
			 System.out.println("URL not verified");
		 }
	  }
	  public void verifyTitle()
	  {
		 String title=page.title();
		 System.out.println("Page title: " + title);
		 if(title != null && !title.isEmpty())
		 {
			 System.out.println("Title verified: " + title);
		 }
		 else {
			 System.out.println("Title not verified");
		 }
	  }
	  public void verifyBrokenLink() throws Exception
	  {
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
		      page.navigate(ConfigReader.getUatUrl());
		      page.waitForLoadState();
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login/ Sign Up")).click();
		      page.getByPlaceholder("Enter Mobile Number").click();
		      page.getByPlaceholder("Enter Mobile Number").fill(ConfigReader.getTestMobileNumber());
		      page.waitForLoadState();
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send OTP")).click();
		      page.getByRole(AriaRole.TEXTBOX).first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		      page.getByRole(AriaRole.TEXTBOX).first().fill("1");
		      page.getByRole(AriaRole.TEXTBOX).nth(1).fill("2");
		      page.getByRole(AriaRole.TEXTBOX).nth(2).fill("3");
		      page.getByRole(AriaRole.TEXTBOX).nth(3).fill("4");
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
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
	      page.waitForLoadState();
	      assertThat(page.getByRole(AriaRole.LIST)).containsText("Siyad Testt");
	  }
	  public void addAddress() throws Exception
	  {
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
	      page.waitForLoadState();
	      assertThat(page.locator("#root")).containsText("KATHAR TDPA KOCHI Ernakulam Kerala Cochin 685584");
	  }
	  public void removeAddress()
	  {
		  page.waitForLoadState();
	      page.locator(".MuiGrid-root > div > div:nth-child(2) > div:nth-child(2) > button").first().click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")).click();
	  }
	  public void MRIAbdomenBook() throws Exception
	  {

		  page.navigate(ConfigReader.getUatUrl());
		  page.waitForLoadState();
		  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("MRI Scan").setExact(true)).click();
	      page.getByText("Abdomen").click();
	      page.getByPlaceholder("Enter Full Name").fill("SIYADMRIAbdomenTest");
	      page.getByPlaceholder("Enter Phone Number").fill(ConfigReader.getTestMobileNumber());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book an MRI Scan")).first().click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).fill(ConfigReader.getTestOTP());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      assertThat(page.locator("body")).containsText("Booking Confirmed!");
	      page.getByText("View my Booking").first().click();

	      String BookingIDWithHash = page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[2]/div/ul/li[5]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/p[2]").textContent();
	      String BookingID = BookingIDWithHash.replaceAll(".*#(\\d+).*", "$1"); 
	      System.out.println("Extracted Booking ID: " + BookingID);
	     
	      adminBookingIdVerification(BookingID);

	  }

	  public void adminBookingIdVerification(String BookingID) {
		  BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
	              .setChannel("msedge")
	              .setHeadless(ConfigReader.isHeadless());

	      try (Browser edgeBrowser = playwright.chromium().launch(launchOptions);
	           BrowserContext edgeContext = edgeBrowser.newContext();
	           Page adminPage = edgeContext.newPage()) {


	          adminPage.navigate(ConfigReader.getAdminUrl());
	          adminPage.waitForLoadState();

	          adminPage.getByPlaceholder("Enter Phone").fill(ConfigReader.getAdminTestMobileNumber());
	          adminPage.getByPlaceholder("Enter Password").fill(ConfigReader.getAdminTestpasswd());
	          adminPage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

	          adminPage.waitForLoadState();
	          adminPage.waitForTimeout(3000); 

	          String adminBookingText = adminPage.locator("tbody").textContent();
	          System.out.println("Admin booking data: " + adminBookingText);

	          assertThat(adminPage.locator("tbody")).containsText(BookingID);
	          System.out.println(" Booking ID " + BookingID + " verified in admin dashboard");

	      } catch (Exception e) {
	          System.err.println(" Admin dashboard verification failed: " + e.getMessage());
	          throw new RuntimeException("Admin verification failed", e);
	      }
	  }
	    
	  public void MRIBrainBook()
	  {
		     page.navigate(ConfigReader.getUatUrl());
		     page.waitForLoadState();
		     page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("MRI Scan").setExact(true)).click();
		      page.getByText("Brain/Head").click();
		      page.getByPlaceholder("Enter Full Name").fill("SIYADMRIBRAINTEST");
		      page.getByPlaceholder("Enter Phone Number").fill(ConfigReader.getTestMobileNumber());
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book an MRI Scan")).first().click();
		      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).fill(ConfigReader.getTestOTP());
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
		      assertThat(page.locator("body")).containsText("Booking Confirmed!");
		      page.getByText("View my Booking").first().click();

		      String brainBookingIDWithHash = page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[2]/div/ul/li[5]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/p[2]").textContent();
		      String BookingID = brainBookingIDWithHash.replaceAll(".*#(\\d+).*", "$1"); 
		      System.out.println("Extracted Booking ID: " + BookingID);
		     
		      adminBookingIdVerification(BookingID);

		     
	  }
	  public void CervicalSpineBook()
	  {
		  page.navigate(ConfigReader.getUatUrl());
		  page.waitForLoadState();
	      page.locator("#root > div:nth-child(3) > div > div > div").first().click();
	      page.getByText("Cervical Spine", new Page.GetByTextOptions().setExact(true)).click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Premium MRICervical Spine Scans");
	      page.getByPlaceholder("Enter Full Name").fill("SIYADMRICRVICAL");
	      page.getByPlaceholder("Enter Phone Number").fill(ConfigReader.getTestMobileNumber());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book an MRI Scan")).first().click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).fill(ConfigReader.getTestOTP());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      assertThat(page.locator("body")).containsText("Booking Confirmed!");
	      page.getByText("View my Booking").first().click();

		  String CervicalSpineBookingIDWithHash = page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[2]/div/ul/li[5]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/p[2]").textContent();
		  String BookingID = CervicalSpineBookingIDWithHash.replaceAll(".*#(\\d+).*", "$1"); 
		  System.out.println("Extracted Booking ID: " + BookingID);
		 
		  adminBookingIdVerification(BookingID);

		  
	  }
	  public void KneeMRIBook()
	  {
		  page.waitForLoadState();
		  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("MRI Scan").setExact(true)).click();
	      page.getByText("Knee").click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Premium MRIKnee Scans");
	      page.locator(".MuiStack-root > div > div > svg").first().click();
	      page.getByPlaceholder("Enter Full Name").fill("SiyadMRIKNNEE");
	      page.getByPlaceholder("Enter Phone Number").fill(ConfigReader.getTestMobileNumber());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book an MRI Scan")).first().click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).fill(ConfigReader.getTestOTP());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      assertThat(page.locator("body")).containsText("Booking Confirmed!");
	      page.getByText("View my Booking").first().click();
	      
	      String KneeMRIBookingIDWithHash = page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[2]/div/ul/li[5]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/p[2]").textContent();
		  String BookingID = KneeMRIBookingIDWithHash.replaceAll(".*#(\\d+).*", "$1"); 
		  System.out.println("Extracted Booking ID: " + BookingID);
		 
		  adminBookingIdVerification(BookingID);

	  }
	  public void LSSpineMRIBook()
	  {
		  page.waitForLoadState();
		  page.locator("#root > div:nth-child(3) > div > div > div").first().click();
	      page.getByText("LS Spine").click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Premium MRILS Spine Scans");
	      page.getByPlaceholder("Enter Full Name").fill("SIYADMRILSSPAINE");
	      page.locator(".MuiStack-root > div > div:nth-child(2)").first().click();
	      page.getByPlaceholder("Enter Phone Number").fill(ConfigReader.getTestMobileNumber());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book an MRI Scan")).first().click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).fill(ConfigReader.getTestOTP());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      assertThat(page.locator("body")).containsText("Booking Confirmed!");
          page.getByText("View my Booking").first().click();
 	      
	      String LSSPineMRIBookingIDWithHash = page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[2]/div/ul/li[5]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/p[2]").textContent();
		  String BookingID = LSSPineMRIBookingIDWithHash.replaceAll(".*#(\\d+).*", "$1"); 
		  System.out.println("Extracted Booking ID: " + BookingID);
		 
		  adminBookingIdVerification(BookingID);

	      
	  }
	  public void MRIShoulderBook()
	  {
		  page.waitForLoadState();
		  page.locator(".MuiBox-root > svg").first().click();
	      page.getByText("Shoulder", new Page.GetByTextOptions().setExact(true)).click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Premium MRIShoulder Scans");
	      page.getByPlaceholder("Enter Full Name").fill("SIYADMRISHOULDER");
	      page.getByPlaceholder("Enter Phone Number").fill(ConfigReader.getTestMobileNumber());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book an MRI Scan")).first().click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).fill(ConfigReader.getTestOTP());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      assertThat(page.locator("body")).containsText("Booking Confirmed!");
          page.getByText("View my Booking").first().click();
 	      
	      String ShoulderMRIBookingIDWithHash = page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[2]/div/ul/li[5]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/p[2]").textContent();
		  String BookingID = ShoulderMRIBookingIDWithHash.replaceAll(".*#(\\d+).*", "$1"); 
		  System.out.println("Extracted Booking ID: " + BookingID);
		 
		  adminBookingIdVerification(BookingID);	      
	  }
	  public void CTbook() throws Exception
	  {
		  page.navigate(ConfigReader.getUatUrl());
		  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("CT Scan")).click();
	      page.getByText("CT Scans", new Page.GetByTextOptions().setExact(true)).first().click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Premium CT Scans");
	      page.getByPlaceholder("Enter Full Name").fill("SIYADCTSCANTest");
	      page.getByPlaceholder("Enter Phone Number").fill(ConfigReader.getTestMobileNumber());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book a CT Scan")).first().click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).fill(ConfigReader.getTestOTP());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      assertThat(page.locator("body")).containsText("Booking Confirmed!");
          page.getByText("View my Booking").first().click();
 	      
	      String CTBookingIDWithHash = page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[2]/div/ul/li[5]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/p[2]").textContent();
		  String BookingID = CTBookingIDWithHash.replaceAll(".*#(\\d+).*", "$1"); 
		  System.out.println("Extracted Booking ID: " + BookingID);
		 
		  adminBookingIdVerification(BookingID);
	  }
	  public void CTAdbomenBook() throws Exception
	  {
		  page.navigate(ConfigReader.getUatUrl());
		  page.waitForLoadState();
		  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("CT Scan").setExact(true)).click();
	      page.getByText("Abdomen & Pelvis", new Page.GetByTextOptions().setExact(true)).click();
	      page.getByPlaceholder("Enter Full Name").fill("CTAbdomenTest");
	      page.getByPlaceholder("Enter Phone Number").click();
	      page.getByPlaceholder("Enter Phone Number").fill(ConfigReader.getTestMobileNumber());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book a CT Scan")).first().click();
	      page.locator(".MuiStack-root > div:nth-child(3)").first().click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).fill(ConfigReader.getTestOTP());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      assertThat(page.locator("body")).containsText("Booking Confirmed!");
          page.getByText("View my Booking").first().click();
 	      
	      String CTAbdomenBookingIDWithHash = page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[2]/div/ul/li[5]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/p[2]").textContent();
		  String BookingID = CTAbdomenBookingIDWithHash.replaceAll(".*#(\\d+).*", "$1"); 
		  System.out.println("Extracted Booking ID: " + BookingID);
		 
		  adminBookingIdVerification(BookingID);
	    }
	  
	  public void CTBrainScanBook() throws Exception
	  {
		  page.navigate(ConfigReader.getUatUrl());
		  page.waitForLoadState();
		  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("CT Scan").setExact(true)).click();
		  page.getByText("Brain", new Page.GetByTextOptions().setExact(true)).click();
	      page.getByPlaceholder("Enter Full Name").fill("SIYADCTBRAIN");
	      page.locator(".MuiStack-root > div > div:nth-child(2)").first().click();
	      page.getByPlaceholder("Enter Phone Number").fill(ConfigReader.getTestMobileNumber());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book a CT Scan")).first().click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).fill(ConfigReader.getTestOTP());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      assertThat(page.locator("body")).containsText("Booking Confirmed!");
          page.getByText("View my Booking").first().click();

	      String CTBrainBookingIDWithHash = page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[2]/div/ul/li[5]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/p[2]").textContent();
		  String BookingID = CTBrainBookingIDWithHash.replaceAll(".*#(\\d+).*", "$1"); 
		  System.out.println("Extracted Booking ID: " + BookingID);
		 
		  adminBookingIdVerification(BookingID);
	  }
	  public void CTChestScanBook() throws Exception
	  {
		  page.navigate(ConfigReader.getUatUrl());
          page.waitForLoadState();
		  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("CT Scan").setExact(true)).click();
	      page.getByText("Chest", new Page.GetByTextOptions().setExact(true)).click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Premium CTChest Scans");
	      page.getByPlaceholder("Enter Full Name").fill("SIYADCTCHEST");
	      page.getByPlaceholder("Enter Phone Number").fill(ConfigReader.getTestMobileNumber());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book a CT Scan")).first().click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).fill(ConfigReader.getTestOTP());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      assertThat(page.locator("body")).containsText("Booking Confirmed!");
	      page.getByText("View my Booking").first().click();

	      String CTChestBookingIDWithHash = page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[2]/div/ul/li[5]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/p[2]").textContent();
		  String BookingID = CTChestBookingIDWithHash.replaceAll(".*#(\\d+).*", "$1"); 
		  System.out.println("Extracted Booking ID: " + BookingID);
		 
		  adminBookingIdVerification(BookingID);
          
	  }
	  public void CTNeckScanBook() throws Exception
	  {
		  page.navigate(ConfigReader.getUatUrl());
		  page.waitForLoadState();
		  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("CT Scan").setExact(true)).click();
	      page.getByText("Neck", new Page.GetByTextOptions().setExact(true)).click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Premium CTNeck Scans");
	      page.getByPlaceholder("Enter Full Name").click();
	      page.getByPlaceholder("Enter Full Name").fill("SIYADCTNECK");
	      page.getByPlaceholder("Enter Phone Number").fill(ConfigReader.getTestMobileNumber());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book a CT Scan")).first().click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).fill(ConfigReader.getTestOTP());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      assertThat(page.locator("body")).containsText("Booking Confirmed!");
	      page.getByText("View my Booking").first().click();

	      String CTNeckBookingIDWithHash = page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[2]/div/ul/li[5]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/p[2]").textContent();
		  String BookingID = CTNeckBookingIDWithHash.replaceAll(".*#(\\d+).*", "$1"); 
		  System.out.println("Extracted Booking ID: " + BookingID);
		 
		  adminBookingIdVerification(BookingID);
	  }
	  public void CTPNSSCanBook() throws Exception
	  {
		  page.navigate(ConfigReader.getUatUrl());
          page.waitForLoadState();
		    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("CT Scan").setExact(true)).click();
		      page.getByText("PNS (Paranasal Sinuses)").click();
		      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Premium CTPNS (Paranasal Sinuses) Scans");
		      page.getByPlaceholder("Enter Full Name").click();
		      page.getByPlaceholder("Enter Full Name").fill("SIYADCTPNS");
		      page.getByPlaceholder("Enter Phone Number").fill(ConfigReader.getTestMobileNumber());
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book a CT Scan")).first().click();
		      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).fill(ConfigReader.getTestOTP());
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
		      page.waitForLoadState();
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
		      assertThat(page.locator("body")).containsText("Booking Confirmed!");
		      page.getByText("View my Booking").first().click();

		      String CTPNSBookingIDWithHash = page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[2]/div/ul/li[5]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/p[2]").textContent();
			  String BookingID = CTPNSBookingIDWithHash.replaceAll(".*#(\\d+).*", "$1"); 
			  System.out.println("Extracted Booking ID: " + BookingID);
			 
			  adminBookingIdVerification(BookingID);
		     }
	  
	  public void PETCTBook() throws Exception
	  {
		  page.navigate(ConfigReader.getUatUrl());
		  page.waitForLoadState();
          page.evaluate("window.scroll({ top: document.body.scrollHeight, behavior: 'smooth' })");
		  page.locator("//*[@id=\"root\"]/main/div[2]/div/div[3]/div[1]/div[1]/div[3]/p").click();
		  page.waitForLoadState();
	      page.getByPlaceholder("Enter Full Name").fill("SIYADDPETTEST");
	      page.getByPlaceholder("Enter Phone Number").fill(ConfigReader.getTestMobileNumber());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book a PET-CT Scan")).click();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter OTP")).fill(ConfigReader.getTestOTP());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.getByPlaceholder("Select date").first().fill("2025-10-30");
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Select time")).click();
	      page.getByText("12:30 PM").click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      assertThat(page.locator("body")).containsText("Booking Confirmed!");
	      page.getByText("View my Booking").first().click();

	      String PETCTBookingIDWithHash = page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[2]/div/ul/li[5]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/p[2]").textContent();
		  String BookingID = PETCTBookingIDWithHash.replaceAll(".*#(\\d+).*", "$1"); 
		  System.out.println("Extracted Booking ID: " + BookingID);
		 
		  adminBookingIdVerification(BookingID);
	     
	  }
	  public void EditMobileLogin() throws Exception
	  {
		  page.navigate(ConfigReader.getUatUrl());
		  page.waitForLoadState();
		  page.locator("//*[@id=\"root\"]/header/div[2]/div/div/div[2]/div[4]/button").click();
		  page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[1]/div/div[2]/div[3]/div/div[2]/div/button").click();
		   page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login/ Sign Up")).click();
		      page.getByPlaceholder("Enter Mobile Number").click();
		      page.getByPlaceholder("Enter Mobile Number").fill(ConfigReader.getTestMobileNumber());
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send OTP")).click();
		      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit Mobile Number")).click();
		      assertThat(page.getByPlaceholder("Enter Mobile Number")).isVisible();
	  }
	  public void ResendOTP() throws Exception
	  {
		  page.waitForLoadState();
		  page.getByPlaceholder("Enter Mobile Number").click();
	      page.getByPlaceholder("Enter Mobile Number").fill(ConfigReader.getTestMobileNumber());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send OTP")).click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Resend OTP")).waitFor(new Locator.WaitForOptions() .setState(WaitForSelectorState.VISIBLE).setTimeout(30_000));

	    	assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Resend OTP"))).isVisible();

	  }
	  public void InvalidOTP() throws Exception
	  {
		  page.navigate(ConfigReader.getUatUrl());
		  page.waitForLoadState();
		  page.locator("//*[@id=\"root\"]/header/div[2]/div/div/div[2]/div[4]/button").click();
		  page.waitForLoadState();
		  page.getByPlaceholder("Enter Mobile Number").click();
	      page.getByPlaceholder("Enter Mobile Number").fill(ConfigReader.getTestMobileNumber());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send OTP")).click();
	      page.getByRole(AriaRole.TEXTBOX).first().fill("0");
	      page.getByRole(AriaRole.TEXTBOX).nth(1).fill("0");
	      page.getByRole(AriaRole.TEXTBOX).nth(2).fill("0");
	      page.getByRole(AriaRole.TEXTBOX).nth(3).fill("0");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
	      page.waitForSelector("text=OTP Verification Failed", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
	  }
	  public void CheckNavigationFlow() throws Exception
	  {
		  page.navigate(ConfigReader.getUatUrl());
		  page.waitForLoadState();
		  page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("https://bms-prod-2024.s3.ap-south-1.amazonaws.com/assets/mri.svg")).click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Premium MRI Scans");
	      page.getByText("BOOK MY SCANS").nth(1).click();
	      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("https://bms-prod-2024.s3.ap-south-1.amazonaws.com/assets/CT.svg")).click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Premium CT Scans");
	      page.getByText("BOOK MY SCANS").nth(1).click();
	      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("https://bms-prod-2024.s3.ap-south-1.amazonaws.com/assets/PET.svg")).click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Premium PET-CT Scans");
	      page.getByText("BOOK MY SCANS").nth(1).click();
	      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("https://bms-prod-2024.s3.ap-south-1.amazonaws.com/assets/MHC.svg")).click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Master Health Checkup");
	      page.getByText("BOOK MY SCANS").nth(1).click();
	      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("https://bms-prod-2024.s3.ap-south-1.amazonaws.com/assets/HC.svg")).click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Health Checkups");
	      page.getByText("BOOK MY SCANS").nth(1).click();
	      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("https://bms-prod-2024.s3.ap-south-1.amazonaws.com/assets/compareLab.svg")).click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Compare And Book");
	      page.getByText("BOOK MY SCANS").nth(1).click();
	      page.waitForLoadState();
	      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("https://bms-prod-2024.s3.ap-south-1.amazonaws.com/assets/HC.svg")).click();
	      page.waitForLoadState();
	      page.getByText("Preventive").nth(1).scrollIntoViewIfNeeded();
	      page.getByText("Preventive").nth(1).click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Preventive Packages");
	      page.navigate("https://bmsuat.inovatrik.com/bangalore/health-checkup");
	      page.getByText("Senior").nth(1).scrollIntoViewIfNeeded();
	      page.getByText("Senior").first().click();
	      assertThat(page.getByRole(AriaRole.MAIN)).containsText("Senior Packages");
	      page.goBack();
	      
	    }	 
	  public void BookSlverMasterHealthcheckup() throws Exception
	  {

		  page.navigate(ConfigReader.getUatUrl());
		  page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Master Health Checkup")).click();
	      page.waitForLoadState();
	      page.locator("#packages-section > div.MuiBox-root.css-1ovdh1d > div.MuiBox-root.css-1s6xsst > p").scrollIntoViewIfNeeded();
	      page.locator("//*[@id=\"packages-section\"]/div[1]/div[2]/div/div[1]/div/div[3]/div/button").click();
	      page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.getByPlaceholder("Name").click();
	      page.getByPlaceholder("Name").fill("SIYADMHC1");
	      page.getByPlaceholder("Email").fill(ConfigReader.getTestEmail());
	      page.getByPlaceholder("Mobile Number").fill(ConfigReader.getTestMobileNumber());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Generate OTP")).click();
	      page.getByPlaceholder("OTP").fill(ConfigReader.getTestOTP());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Verify OTP")).click();
	      page.getByPlaceholder("Age").fill("22");
	      page.getByText("Select Gender").click();
	      page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Male").setExact(true)).click();
	      page.getByPlaceholder("Address").fill("scdcdcdcs");
	      page.getByPlaceholder("Pincode").click();
	      page.getByPlaceholder("Pincode").fill("464646");
	      page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Proceed$"))).click();
	      page.locator(".PrivateSwitchBase-input").first().check();
	      page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Review booking$"))).click();
	      page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm Booking")).click();
	      assertThat(page.locator("body")).containsText("Master Health Checkup - Silver");
	      page.getByLabel("Pay Later, Enquire Now").check();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm Booking")).click();
	      page.getByText("View my Booking").first().click();

	  }
	  public void BookGoldMHCBook() throws Exception
	  {
		  page.navigate(ConfigReader.getUatUrl());
		  page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Master Health Checkup")).click();
	      page.waitForLoadState();
	      page.locator("#packages-section > div.MuiBox-root.css-1ovdh1d > div.MuiBox-root.css-1s6xsst > p").scrollIntoViewIfNeeded();
          page.locator("//*[@id=\"packages-section\"]/div[1]/div[2]/div/div[2]/div/div[3]/div/button").click();
          page.waitForLoadState();
          
          page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.getByPlaceholder("Name").fill("SIYADMHC2");
	      page.getByPlaceholder("Email").fill(ConfigReader.getTestEmail());
	      page.getByPlaceholder("Mobile Number").fill(ConfigReader.getTestMobileNumber());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Generate OTP")).click();
	      page.getByPlaceholder("OTP").fill(ConfigReader.getTestOTP());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Verify OTP")).click();
	      page.getByPlaceholder("Age").fill("22");
	      page.getByText("Select Gender").click();
	      page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Male").setExact(true)).click();
	      page.getByPlaceholder("Address").fill("scdcdcdcs");
	      page.getByPlaceholder("Pincode").click();
	      page.getByPlaceholder("Pincode").fill("464646");
	      page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Proceed$"))).click();
	      page.locator(".PrivateSwitchBase-input").first().check();
	      page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Review booking$"))).click();
	      page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm Booking")).click();
	      page.waitForLoadState();
	      assertThat(page.locator("body")).containsText("Master Health Checkup - Gold");
	      page.getByLabel("Pay Later, Enquire Now").check();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm Booking")).click();
	      page.getByText("View my Booking").first().click();
	  }
	  public void BookPlatinumMHCBook() throws Exception
	  {
		  page.navigate(ConfigReader.getUatUrl());
		  page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Master Health Checkup")).click();
	      page.waitForLoadState();
	      page.locator("#packages-section > div.MuiBox-root.css-1ovdh1d > div.MuiBox-root.css-1s6xsst > p").scrollIntoViewIfNeeded();
	      page.locator("//*[@id=\"packages-section\"]/div[1]/div[2]/div/div[3]/div/div[3]/div/button").click();
	      page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.getByPlaceholder("Name").fill("SIYADMHC2");
	      page.getByPlaceholder("Email").fill(ConfigReader.getTestEmail());
	      page.getByPlaceholder("Mobile Number").fill(ConfigReader.getTestMobileNumber());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Generate OTP")).click();
	      page.getByPlaceholder("OTP").fill(ConfigReader.getTestOTP());
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Verify OTP")).click();
	      page.getByPlaceholder("Age").fill("22");
	      page.getByText("Select Gender").click();
	      page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Male").setExact(true)).click();
	      page.getByPlaceholder("Address").fill("scdcdcdcs");
	      page.getByPlaceholder("Pincode").click();
	      page.getByPlaceholder("Pincode").fill("464646");
	      page.waitForLoadState();
	      
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
	      page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Proceed$"))).click();
	      page.locator(".PrivateSwitchBase-input").first().check();
	      page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Review booking$"))).click();
	      page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm Booking")).click();
	      page.waitForLoadState();
	      assertThat(page.locator("body")).containsText("Master Health Checkup - Platinum");
	      page.getByLabel("Pay Later, Enquire Now").check();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm Booking")).click();
	      page.getByText("View my Booking").first().click();
	  }
	   
	  public void XRAYBook() throws Exception
	  {
		  page.navigate(ConfigReader.getUatUrl());
		   page.waitForLoadState();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Scans")).click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("X-Ray")).first().click();
	      page.getByLabel("Aarthi Scans & Labs").first().scrollIntoViewIfNeeded();
          page.locator("#root > main > div.MuiStack-root.t3-main-container.css-1xbe40y > div.MuiStack-root.css-aiww9g > div.MuiStack-root.css-pdv2i8 > div > div > div > div.MuiStack-root.css-cy91f0 > div.MuiStack-root.css-17bqjow > div.MuiStack-root.css-c98prh > div > div:nth-child(1) > div > div > div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-lg-12.css-ucashc > div.MuiStack-root.css-ooasvr > div > button > p > p").first().click();
	      page.waitForLoadState();
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Full Name")).fill("SIYADTEST");
	      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Phone Number")).fill(ConfigReader.getTestMobileNumber());
	      page.getByPlaceholder("Select time slot").last().click();
	      page.waitForLoadState();
	      Locator slot = page.locator("body > div.MuiBox-root.css-1dvep9k > div.MuiBox-root.css-19p0syp");
	      slot.first().click();

	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Get OTP")).click();
	      page.getByRole(AriaRole.SPINBUTTON).first().fill("1");
	      page.getByRole(AriaRole.SPINBUTTON).nth(1).fill("2");
	      page.getByRole(AriaRole.SPINBUTTON).nth(2).fill("3");
	      page.getByRole(AriaRole.SPINBUTTON).nth(3).fill("4");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Verify OTP")).click();
	      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("IMAGES.APP_BAR.CART_LOGO")).click();
	      assertThat(page.locator("body")).containsText("XRAY");
	      
	    }
	  public void bookUSG() throws Exception
	  {
		  page.navigate(ConfigReader.getUatUrl());
		  page.waitForLoadState();
		  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Scans")).click();
		  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Ultrasound-(USG)")).click();
		  page.waitForLoadState();
	      page.getByLabel("Focus Diagnostics").first().scrollIntoViewIfNeeded();
          page.locator("#root > main > div.MuiStack-root.t3-main-container.css-1xbe40y > div.MuiStack-root.css-aiww9g > div.MuiStack-root.css-pdv2i8 > div > div > div > div.MuiStack-root.css-cy91f0 > div.MuiStack-root.css-17bqjow > div.MuiStack-root.css-c98prh > div > div:nth-child(1) > div > div > div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-lg-12.css-ucashc > div.MuiStack-root.css-ooasvr > div > button").click();
          page.waitForLoadState();
          page.getByPlaceholder("Select date").fill("2026-02-28");
         
         Locator slot2=page.locator("body > div.MuiBox-root.css-16mjwcv > div.MuiBox-root.css-19p0syp");
         slot2.first().click();
         page.waitForLoadState();
         page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart")).click();
         page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("IMAGES.APP_BAR.CART_LOGO")).click();
         assertThat(page.locator("body")).containsText("USG");
	      page.waitForLoadState();
	  }
	  public void bookECG() throws Exception
	  {
		  page.waitForLoadState();
		  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Scans")).click();
		  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("ECG")).first().click();
		  page.getByLabel("Focus Diagnostics").first().scrollIntoViewIfNeeded();
          page.locator("#root > main > div.MuiStack-root.t3-main-container.css-1xbe40y > div.MuiStack-root.css-aiww9g > div.MuiStack-root.css-pdv2i8 > div > div > div > div.MuiStack-root.css-cy91f0 > div.MuiStack-root.css-17bqjow > div.MuiStack-root.css-c98prh > div > div:nth-child(1) > div > div > div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-lg-12.css-ucashc > div.MuiStack-root.css-ooasvr > div > button").click();
	      page.getByPlaceholder("Select date").fill("2026-02-28");
	      page.waitForLoadState();
	      Locator slot3=page.locator("body > div.MuiBox-root.css-16mjwcv > div.MuiBox-root.css-19p0syp");
	        slot3.first().click();
	        
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart")).click();
	      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("IMAGES.APP_BAR.CART_LOGO")).click();
	      assertThat(page.locator("body")).containsText("DIGITAL ECG");
	      page.locator("div:nth-child(13) > div:nth-child(3) > div > div > div > div > div > .MuiButtonBase-root").first().click();
	  }
	  public void bookECHO() throws Exception
	  {

		  page.navigate(ConfigReader.getUatUrl());
		  page.waitForLoadState();
		  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Scans")).hover();
		  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("ECHO")).first().click();
	      page.getByLabel("Apollo Clinic").first().scrollIntoViewIfNeeded();
          page.locator("#root > main > div.MuiStack-root.t3-main-container.css-1xbe40y > div.MuiStack-root.css-aiww9g > div.MuiStack-root.css-pdv2i8 > div > div > div > div.MuiStack-root.css-cy91f0 > div.MuiStack-root.css-17bqjow > div.MuiStack-root.css-c98prh > div > div:nth-child(1) > div > div > div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-lg-12.css-ucashc > div.MuiStack-root.css-ooasvr > div > button").click();
	      page.getByPlaceholder("Select date").fill("2025-12-04");
	      page.getByPlaceholder("Select time slot").click();
	      page.getByText("9:00 AM").click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Get OTP")).click();
	      page.getByRole(AriaRole.SPINBUTTON).first().fill("1");
	      page.getByRole(AriaRole.SPINBUTTON).nth(1).fill("2");
	      page.getByRole(AriaRole.SPINBUTTON).nth(2).fill("3");
	      page.getByRole(AriaRole.SPINBUTTON).nth(3).fill("4");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Verify OTP")).click();
	      page.waitForLoadState();
	      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("IMAGES.APP_BAR.CART_LOGO")).click();
	      assertThat(page.locator("body")).containsText("2D ECHO");
	      page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Pay Now")).check();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm Booking")).click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed To Pay")).click();
	      page.waitForLoadState();
	      page.getByText("Net Banking").click();
	      page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Test bank$"))).first().click();
	      page.waitForLoadState();
	      page.locator("#net-banking-list-TESTPGNB-pop").getByTestId("netbanking-proceed-btn-TESTPGNB").click();
	      page.getByPlaceholder("Enter payu as username").click();
	      page.getByPlaceholder("Enter payu as username").fill("payu");
	      page.locator("#CredForm div").filter(new Locator.FilterOptions().setHasText("Password Kindly enter valid")).locator("div").click();
	      page.getByPlaceholder("Enter payu as password").fill("payu");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Simulate Success Response")).click();
	      assertThat(page.locator("body")).containsText("Payment Success Your payment was successful! Please do not click the back button. Kindly click 'Go to Home' to complete the payment process. Go to Home");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Go to Home")).click();
	    }
	  
	  public void BookEEG()
	  {
		  
	  }
	  public void bookMammogram()
	  {
		  
	  }
	  public void bookTreadmilTest()
	  {
		  
	  }
	  public void healthcheckup()
	  {
		  
	  }
	  public void preventiveBook()
	  {
		  
	  }
 }
  



 










