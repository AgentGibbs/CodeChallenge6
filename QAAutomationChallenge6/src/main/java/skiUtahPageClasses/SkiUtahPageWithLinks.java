package skiUtahPageClasses;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SkiUtahPageWithLinks {

	// member variables
	private WebDriver driver;
	private ArrayList<String> visitedLinks;
	public ArrayList<String> allURLs;
	
	private URL target;

	// Constructors
	public SkiUtahPageWithLinks(WebDriver driver) {
		this.driver = driver;
		allURLs = new ArrayList<String>();
		visitedLinks = new ArrayList<String>();	
	}

	// methods

	public void findAllLinksOnPage() {
		List<WebElement> linksOnPage = driver.findElements(By.tagName("a"));

		for (int index = 0; index < linksOnPage.size(); index += 1)
		{
			WebElement elementToCheck = linksOnPage.get(index);
			String linkUrl = elementToCheck.getAttribute("href");
			if (isUrlOkay(linkUrl) == true) {
				allURLs.add(linkUrl.toLowerCase());
			} // end if
		} // end for

	}

	private boolean isUrlOkay(String linkUrl) {
		boolean isOK = true;
		
		// already been visited
		if (visitedLinks.contains(linkUrl.toLowerCase()) == true) {
			isOK = false;
		}
		// already on the list
		if (allURLs.contains(linkUrl.toLowerCase()) == true) {
			isOK = false;
		}
		// out of domain
		if (linkUrl.toLowerCase().contains("skiutah.com") == false) {
			isOK = false;
		}
		return isOK;
	}

	public void navigateToLink(String url) {
		visitedLinks.add(url);
		System.out.println("href: " + url);
		driver.get(url);
		convertToUrlObject(url);
		System.out.println("Response Code: " + getHttpResponseCode());
		findAllLinksOnPage();

	}

	private void convertToUrlObject(String url) {
		try {
			target = new URL(url);
		}

		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	private int getHttpResponseCode() {
		int httpCode = 00;
		if (target != null) {
			try {
				HttpURLConnection connection = (HttpURLConnection) target.openConnection();
				httpCode = connection.getResponseCode();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return httpCode;
	}

}
