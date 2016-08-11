package seleniumLaunchers;

import java.util.logging.Level;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import skiUtahPageClasses.*;

public class SeleniumLauncher {
	public HtmlUnitDriver driver;
	private SkiUtahPageWithLinks page;
	public SeleniumLauncher() {
		
		driver = new HtmlUnitDriver();
		driver.setJavascriptEnabled(true);
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF); 

		page = new SkiUtahPageWithLinks(driver);
		page.navigateToLink("http://www.skiutah.com");
		page.findAllLinksOnPage();
		int urlListIndex = 0;
		int max = page.allURLs.size();
		while(urlListIndex <max==true)
		{
			page.navigateToLink(page.allURLs.get(urlListIndex));
			urlListIndex = urlListIndex +1;
			max = page.allURLs.size();
			
		}
		driver.quit();
		
		
	}

	
	
}
