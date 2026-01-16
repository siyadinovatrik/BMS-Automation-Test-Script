package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.ElementHandle;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CommonUtils {
    
    public static void verifyUrl(Page page, String expectedUrl) {
        String actualUrl = page.url();
        if (actualUrl.equals(expectedUrl)) {
            System.out.println("URL verified: " + actualUrl);
        } else {
            System.out.println("URL mismatch. Expected: " + 
                expectedUrl + ", Actual: " + actualUrl);
        }
    }
    
    public static void verifyTitle(Page page, String expectedTitle) {
        String actualTitle = page.title();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Title verified: " + actualTitle);
        }
        else {
            System.out.println("Title mismatch. Expected: " + 
                expectedTitle + ", Actual: " + actualTitle);
        }
    }
    
    public static void checkBrokenLinks(Page page) throws Exception {
        page.waitForLoadState();
        List<ElementHandle> links = page.querySelectorAll("a");
        System.out.println("Total links found: " + links.size());
        
        for (ElementHandle link : links) {
            String url = link.getAttribute("href");
            if (url == null || url.isEmpty() || 
                url.startsWith("#") || url.startsWith("javascript")) {
                continue;
            }
            checkLinkStatus(url);
        }
    }
    
    private static void checkLinkStatus(String url) {
        try {
            HttpURLConnection connection = 
                (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            
            int statusCode = connection.getResponseCode();
            if (statusCode >= 400) {
                System.out.println("Broken link: " + url + 
                    " | Status: " + statusCode);
            } else {
                System.out.println("Valid link: " + url + 
                    " | Status: " + statusCode);
            }
            connection.disconnect();
        } catch (Exception e) {
            System.out.println("Exception checking: " + url + 
                " | Message: " + e.getMessage());
        }
    }
}