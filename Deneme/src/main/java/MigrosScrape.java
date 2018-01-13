import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MigrosScrape {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "F:\\UNIVERSITY\\CS-491_492\\Code\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Actions action = new Actions(driver);
		int pageNumber = 1;
		driver.get("https://www.sanalmarket.com.tr/kweb/searchInShop.do?btnUstArama.x=0&btnUstArama.y=0&searchKeyword=glutensiz&kangurumMigrosSearch=true&shopId=1");
		for(int j =0;j<pageNumber;j++) {
		//		HtmlUnitDriver driver = new HtmlUnitDriver();
		//		driver.setJavascriptEnabled(true);		
		Thread.sleep(1000);
		System.out.println(driver.getTitle());
		WebElement element = driver.findElement(By.xpath("*//div[@class='icerikGenelLayout']//div[3]"));
		WebElement element2 = element.findElement(By.xpath("//table[@id='example']//tbody"));
		List<WebElement> elements = element2.findElements(By.xpath("//tr"));
		for(int i =0;i<elements.size();i++) {
			String temp = elements.get(i).getText();
			if(!temp.trim().isEmpty()) {
				String[] splited = temp.split("\\n");
				System.out.println(splited[0]+"\n"+splited[1]+"\n"+splited[2]+"\n"+splited[3]+"\n"+splited[4]);
			}
		}

		element = element.findElement(By.xpath("//div[@class='ustBilgi3Orta']"));
		WebElement element3 = element.findElement(By.xpath("//select"));
		pageNumber = Integer.parseInt(element3.getAttribute("childElementCount"));	
		List<WebElement> enabled = element.findElements(By.xpath("//span[@class='paginate_enabled_next']"));	
		if(enabled.size()>0)
			action.click(enabled.get(0)).perform();
		//		System.out.println(element.getText());

		
		}
		driver.close();
	}

}
