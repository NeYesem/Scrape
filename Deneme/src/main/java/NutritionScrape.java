import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NutritionScrape {

	public static void main(String[] args) throws InterruptedException, ParseException, IOException {
//				System.setProperty("webdriver.chrome.driver", "F:\\UNIVERSITY\\CS-491_492\\Code\\chromedriver.exe");
//				WebDriver driver = new ChromeDriver();
		HtmlUnitDriver driver = new HtmlUnitDriver();
//				driver.setJavascriptEnabled(true);	
		Actions action = new Actions(driver);
		int count = 3;
		String URL_main ="http://www.turkomp.gov.tr";
		String URL ="http://www.turkomp.gov.tr/database?type=foods";
		driver.get(URL);	
			Thread.sleep(1000);
			System.out.println(driver.getTitle());
			WebElement element = driver.findElement(By.xpath("*//table[@id='mydatalist']"));
			WebElement element2 = element.findElement(By.xpath("//tbody"));
			List<WebElement> elements = element2.findElements(By.xpath("//tr"));
			List<String> links = new ArrayList<String>();
			//
			List<String> categories = new ArrayList<String>();
			List<WebElement> elements2;
			//
			List<Nutrition> nutritions;
			List<NutritionList> foods = new ArrayList<NutritionList>();
			String name;
			String prev = "abc";
			WebElement temp;
			double value;
			NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
			Number number;
			for(int i =1;i<elements.size();i++) {
				nutritions = new ArrayList<Nutrition>();
//				Thread.sleep(1000);
				temp = elements.get(i);
				//
				elements2 = temp.findElements(By.xpath("td[3]/a"));
				for(int j=0;j<elements2.size();j++) {
					if(!categories.contains(elements2.get(j).getText().replaceAll("» ", "")))
							categories.add(elements2.get(j).getText().replaceAll("» ", ""));
				}
				/*name = temp.findElement(By.xpath("td[1]")).getAttribute("innerText");
				name= name.replaceAll("\\s+","");
				//System.out.println(name);
				temp = temp.findElement(By.xpath("td[1]//a"));
//				links.set(i, temp.getAttribute("href"));
				driver.get(temp.getAttribute("href"));				
				temp = driver.findElement(By.xpath("burada yýldýz var//table[@id='foodResultlist']/tbody"));
				String[] splitted = temp.getAttribute("innerText").split("\\s*\\r?\\n\\s*");
				for (int j = 1;j<splitted.length;j = j+5) {
					if(splitted[j].length()<3) {
						count=2;
					}
					if(!(prev.substring(0,count).equalsIgnoreCase(splitted[j].substring(0, count))) ) {
				    number = format.parse(splitted[j+2]);
				    value = number.doubleValue();
					nutritions.add(new Nutrition(splitted[j],splitted[j+1], value));
					prev = splitted[j].substring(0, count);
					}
				}
				foods.add(new NutritionList(name, nutritions));
				driver.navigate().back();*/
			}


			/*try (Writer writer = new FileWriter("nutritions.json")) {
			    Gson gson2 = new GsonBuilder().create();
			    gson2.toJson(foods, writer);
			}*/
			System.out.println(categories.toString());
		System.out.println("Done"+elements.size());
		driver.close();
	}
}
