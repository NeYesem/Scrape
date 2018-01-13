import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;

public class VildanScrape {

	public static void main(String[] args) throws InterruptedException {
		//				System.setProperty("webdriver.chrome.driver", "F:\\UNIVERSITY\\CS-491_492\\Code\\chromedriver.exe");
		//				WebDriver driver = new ChromeDriver();
		HtmlUnitDriver driver = new HtmlUnitDriver();
		//				driver.setJavascriptEnabled(true);	
		Actions action = new Actions(driver);
		int pageNumber = 65;
		int count = 0;
		String URL ="http://glutensizmutfak.blogspot.com.tr/2017/";
		driver.get(URL);	
		System.out.println(driver.getTitle());
		for(int j = 0;j<1;j++) {
			List<WebElement> elements = driver.findElements(By.xpath("*//div[@class='blog-posts hfeed']//div[@class='post-outer']"));
			WebElement temp;
			System.out.println("NEW PAGE: "+elements.size());
			for(int i =0;i<elements.size();i++) {
				//				Thread.sleep(1000);
				count++;
				if(count<3) {
					temp = elements.get(i);
					System.out.println("Next Article\n"+temp.findElement(By.xpath("//meta[@itemprop='image_url']")).getAttribute("outerHTML"));
				}
			}

			List<WebElement> nextPage = driver.findElements(By.xpath("*//div[@id='blog-pager']//span[@id='blog-pager-older-link']//a"));
			if(nextPage.size()>0) {
				action.click(nextPage.get(0)).build().perform();
				System.out.println(driver.getCurrentUrl());
			}
			else break;

		}
		System.out.println("Done "+ count);
		driver.close();
	}
}
