package page;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import utils.WaitUtils;

public class BasePage {
    protected Page page;
    
    public BasePage(Page page) {
        this.page = page;
    }
    
    protected void waitForPageLoad() {
        WaitUtils.waitForPageLoad(page);
    }
    
    protected void clickButton(String buttonName) {
        page.getByRole(AriaRole.BUTTON, 
            new Page.GetByRoleOptions().setName(buttonName)).click();
    }
    
    protected void fillInput(String placeholder, String value) {
        page.getByPlaceholder(placeholder).fill(value);
    }
    
    protected void waitForTimeout(int milliseconds) {
        WaitUtils.waitForTimeout(milliseconds);
    }
}