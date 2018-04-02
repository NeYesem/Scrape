import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;

public class Scrape {

	public static void main(String[] args) throws InterruptedException {
		//				System.setProperty("webdriver.chrome.driver", "F:\\UNIVERSITY\\CS-491_492\\Code\\chromedriver.exe");
		//				WebDriver driver = new ChromeDriver();
		HtmlUnitDriver driver = new HtmlUnitDriver();
		//				driver.setJavascriptEnabled(true);	
		Actions action = new Actions(driver);
		int pageNumber = 65;
		int count = 0;
		String meals[] = new String[10];
		String images[] = new String[10];
		String URL ="http://www.nefisyemektarifleri.com/kategori/tarifler/diyet/gluten-diyeti-tarifleri/";
		driver.get(URL);	
		System.out.println(driver.getTitle());
		WebElement element = driver.findElement(By.xpath("//div[@id='blog-archives']//div[@id='item-body']"));
		List<WebElement> nextPage = driver.findElements(By.xpath("//a[@class='nextpostslink']"));
		System.out.println(nextPage.get(0).getAttribute("href"));
		List<WebElement> posts = element.findElements(By.xpath("div[@class='post']"));
		System.out.println(posts.size());
		WebElement temp;
		for(int i=0;i<posts.size();i++) {
			temp = posts.get(i).findElement(By.xpath("*/a[1]"));
			//System.out.println(temp.getAttribute("href"));
			meals[i] = temp.getAttribute("href");
		}
		for(int i=0;i<2;i++) {
			driver.get(meals[i]);
			element = driver.findElement(By.xpath("//article"));
			images[i] = element.findElement(By.xpath("//img[contains(@class,'thumbnail')]")).getAttribute("src");
			System.out.println(element.findElement(By.xpath("//div[@class='entry']//div[contains(@class,'content')]")).getText());
		}
		System.out.println(nextPage.size());
		driver.close();
	}
}
