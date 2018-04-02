import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Category {

	public static void main(String[] args) throws InterruptedException, IOException {
		//				System.setProperty("webdriver.chrome.driver", "F:\\UNIVERSITY\\CS-491_492\\Code\\chromedriver.exe");
		//				WebDriver driver = new ChromeDriver();
		HtmlUnitDriver driver = new HtmlUnitDriver();
		//				driver.setJavascriptEnabled(true);	
		
		int count = 0;
		String sut_urunu = "s�tyo�urtyogurtsutpeynirayrankefir";
		String et_kuru = "etyumurtabal�kbal���nohutfasulyebarbunyabaklab�r�lcef�nd�kcevizbadem�ekirde�if�st���kaju";
		String ekmek_tah�l = "ekmeklava�simitbulgurpirin�gevre�igevrekezmeyufkapatlam�� m�s�r�orba"
				+ "tah�l�ekerpekmeztahinhelva�ikolatacezeryedondurmabisk�vikekkrakergofretbarbal";
		String yaglar = "s�v�ya�tereya�kaymakkrema";
		String meyve_sebze = "erik�z�mbamyadutkirazvi�nekarpuzkay�s�sar�msakm�s�rkavunenginarpatatesmarulfasulyebiberrezene�ilekpatl�candomatesso�anerikbarbunyahavu�bezelye"
				+ "�a�laku�konmazturppancarlimonp�rasamandalinamuzkerevizhindiba�lahana"
				+ "greyfurtkestanebalkaba��kivihurmaportakalayvaarmutkarnabaharnarelmamantar�spanakbrokoli";
		String meals[] = new String[10];
		String splitted[] = null;
		String holder;
		String img;
		String name;
		String nextPageURL = null;
		List<String> ingredients;
		List<String> recipe;
		List<Meals> mealList = new ArrayList<Meals>();
		boolean first = false;
		boolean second = false;	
		boolean pageAvailable = true;
		String URL ="http://www.nefisyemektarifleri.com/kategori/tarifler/diyet/gluten-diyeti-tarifleri/";
		driver.get(URL);	
		System.out.println(driver.getTitle());
		while(pageAvailable) {
		WebElement element = driver.findElement(By.xpath("//div[@id='blog-archives']//div[@id='item-body']"));
		List<WebElement> nextPage = driver.findElements(By.xpath("//a[@class='nextpostslink']"));
		//System.out.println(nextPage.get(0).getAttribute("href"));
		if(nextPage.size()!=0) {
			nextPageURL = nextPage.get(0).getAttribute("href");
			//System.out.println(nextPageURL);
		}
		else pageAvailable = false;
		List<WebElement> posts = element.findElements(By.xpath("div[@class='post']"));
		//System.out.println(posts.size());
		WebElement temp;
		for(int i=0;i<posts.size();i++) {
			temp = posts.get(i).findElement(By.xpath("*/a[1]"));
			//System.out.println(temp.getAttribute("href"));
			meals[i] = temp.getAttribute("href");
		}
		for(int i=0;i<posts.size();i++) {
			ingredients = new ArrayList<String>();
			recipe = new ArrayList<String>();
			driver.get(meals[i]);
			nextPage = driver.findElements(By.xpath("//article"));
			if(nextPage.size()!=0)
				element = nextPage.get(0);
			else {
				System.out.println(driver.getCurrentUrl()+"\nMy Link: "+meals[i]);
				continue;
			}
			img = element.findElement(By.xpath("//img[contains(@class,'thumbnail')]")).getAttribute("src");
			name = element.findElement(By.xpath("//h1[contains(@class,'posttitle')]")).getText();
			//System.out.println("Text:\n"+element.findElement(By.xpath("//div[@class='entry']//div[contains(@class,'content')]")).getText()+"\n");
			holder = element.findElement(By.xpath("//div[@class='entry']//div[contains(@class,'content')]")).getText();
			splitted = holder.split("[\\r\\n]+");
			for(int j=0;j<splitted.length;j++) {
				if(splitted[j].length()>2) {
				if(second) {
					recipe.add(splitted[j]);
				}
				else if(first)
					ingredients.add(splitted[j]);
				}
				if(splitted[j].contains("Malzemeler")) {
					first = true;
				}
				else if(splitted[j].contains(" Yap�l���") || splitted[j].contains("Haz�rlan��")) {
					ingredients.remove(splitted[j]);
					second = true;
					first = false;
				}
			}
			first = false;
			second = false;
			//System.out.println(recipe.toString());
			mealList.add(new Meals(img, name, ingredients, recipe));
			count++;
		}
		if(!pageAvailable) break;
		driver.get(nextPageURL);
		}
		try (Writer writer = new FileWriter("nefisyemekler.json")) {
		    Gson gson2 = new GsonBuilder().create();
		    gson2.toJson(mealList, writer);
		}
		System.out.println("Done: "+count);
	}
}
