package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import config.ConfigReader;
import listeners.ExtentReporterNG;

public class BMSBase {

    public Playwright playwright;
    public Browser browser;
    public BrowserContext context;
    public Page page;
    
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions()
                .setHeadless(ConfigReader.isHeadless())); 
        context = browser.newContext();
        context.addInitScript("document.body.style.zoom='75%'");
        page = context.newPage();
        page.navigate(ConfigReader.getBaseUrl());
        ExtentReporterNG.setPage(page);
    }
    
    public void tearDown() {
        if (page != null) {
            page.close();
        }
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}