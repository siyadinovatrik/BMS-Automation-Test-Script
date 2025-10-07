package page;

import com.microsoft.playwright.Page;

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
}



