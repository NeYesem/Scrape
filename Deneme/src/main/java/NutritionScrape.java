import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;

public class NutritionScrape {

	public static void main(String[] args) throws InterruptedException {
//				System.setProperty("webdriver.chrome.driver", "F:\\UNIVERSITY\\CS-491_492\\Code\\chromedriver.exe");
//				WebDriver driver = new ChromeDriver();
		HtmlUnitDriver driver = new HtmlUnitDriver();
//				driver.setJavascriptEnabled(true);	
		Actions action = new Actions(driver);
		int pageNumber = 1;
		String URL_main ="http://www.turkomp.gov.tr";
		String URL ="http://www.turkomp.gov.tr/database?type=foods";
		driver.get(URL);	
			Thread.sleep(1000);
			System.out.println(driver.getTitle());
			WebElement element = driver.findElement(By.xpath("*//table[@id='mydatalist']"));
			WebElement element2 = element.findElement(By.xpath("//tbody"));
			List<WebElement> elements = element2.findElements(By.xpath("//tr"));
			List<String> links = new ArrayList<String>();
			WebElement temp;
			for(int i =1;i<4;i++) {
//				Thread.sleep(1000);
				temp = elements.get(i);
				System.out.println(temp.getText());
				temp = temp.findElement(By.xpath("td[1]//a"));
//				links.set(i, temp.getAttribute("href"));
				driver.get(temp.getAttribute("href"));				
				temp = driver.findElement(By.xpath("*//table[@id='foodResultlist']/tbody"));
				String[] splited = temp.getAttribute("innerText").split("\\s*\\r?\\n\\s*");
				for (int j = 1;j<splited.length;j++) {
					System.out.print(splited[j]);
					if(j%5==0)
						System.out.println();
				}
				driver.navigate().back();
			}

		
		System.out.println("Done");
		driver.close();
	}
}
