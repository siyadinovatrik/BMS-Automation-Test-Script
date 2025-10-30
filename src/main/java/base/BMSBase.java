package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BMSBase {

	public Playwright playwright;
    public Browser browser;
    public BrowserContext context;
    public Page page;
 
    
    
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
        new BrowserType.LaunchOptions().setHeadless(false)); 
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://bms.mapskil.com/");
	
    }
}
