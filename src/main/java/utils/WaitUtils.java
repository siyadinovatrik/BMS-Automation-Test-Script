package utils;

import com.microsoft.playwright.Page;
public class WaitUtils {
    
    public static void waitForPageLoad(Page page) {
        page.waitForLoadState();
    }
    
    public static void waitForTimeout(int milliseconds) {
        try {
            Thread.sleep(milliseconds); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static void waitForElement(Page page, String selector) {
        page.waitForSelector(selector, 
            new Page.WaitForSelectorOptions()
                .setTimeout(10000));
    }
}