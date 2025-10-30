package page;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


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
		  page.navigate("https://bms.mapskil.com/");
		  page.waitForLoadState();
		  page.locator("//*[@id=\"root\"]/header/div[2]/div/div/div[2]/div[4]/button/p").click();
		  page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[1]/div/div[2]/div[3]/div/div[2]/div/button/p").click();
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
  }














