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

public class NutritionScrapeGroups {

	public static void main(String[] args) throws InterruptedException, ParseException, IOException {
//				System.setProperty("webdriver.chrome.driver", "F:\\UNIVERSITY\\CS-491_492\\Code\\chromedriver.exe");
//				WebDriver driver = new ChromeDriver();
		HtmlUnitDriver driver = new HtmlUnitDriver();
//				driver.setJavascriptEnabled(true);	
		Actions action = new Actions(driver);
		int count = 3;
		int groupCheck = 0;
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
			List<WebElement> elements2;
			//
			List<Nutrition> nutritions;
			List<NutritionList> eturunu = new ArrayList<NutritionList>();
			List<NutritionList> geleneksel = new ArrayList<NutritionList>();
			List<NutritionList> meyve = new ArrayList<NutritionList>();
			List<NutritionList> sebze = new ArrayList<NutritionList>();
			List<NutritionList> balik = new ArrayList<NutritionList>();
			List<NutritionList> icecek = new ArrayList<NutritionList>();
			List<NutritionList> tahil = new ArrayList<NutritionList>();
			List<NutritionList> yaglitohum = new ArrayList<NutritionList>();
			List<NutritionList> yaglar = new ArrayList<NutritionList>();
			List<NutritionList> muhtelif = new ArrayList<NutritionList>();
			List<NutritionList> sekerli = new ArrayList<NutritionList>();
			List<NutritionList> ozel = new ArrayList<NutritionList>();
			List<NutritionList> suturunleri = new ArrayList<NutritionList>();
			List<NutritionList> yumurta = new ArrayList<NutritionList>();
			boolean[] exist = new boolean[14];
			String[] groups = new String[] {"Sebze ve sebze ürünleri","Et ve et ürünleri","Geleneksel gýdalar","Meyve ve meyve ürünleri","Balýk ve su ürünleri",
					"Ýçecekler","Tahýl ve tahýl ürünleri"," Yaðlý tohumlar ve kuru baklagiller","Sývý ve katý yaðlar","Muhtelif gýda","Þeker ve þekerli ürünler",
					"Özel beslenme amaçlý gýdalar","Süt ve süt ürünleri","Yumurta ve yumurta ürünleri"};
			
			String name;
			String prev = "abc";
			WebElement temp;
			double value;
			NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
			Number number;
			for(int i =1;i<elements.size();i++) {
				for(int j = 0;j<exist.length;j++) exist[j] = false;
				nutritions = new ArrayList<Nutrition>();
				temp = elements.get(i);
				
				elements2 = temp.findElements(By.xpath("td[3]/a"));
				for(int j=0;j<elements2.size();j++) {
					for(int k=0;k<groups.length;k++) {
						if(elements2.get(j).getText().contains(groups[k])) exist[k] = true;
					}
				}
				name = temp.findElement(By.xpath("td[1]")).getAttribute("innerText");
				name= name.replaceAll("\\s+","");
				//System.out.println(name);
				temp = temp.findElement(By.xpath("td[1]//a"));
//				links.set(i, temp.getAttribute("href"));
				driver.get(temp.getAttribute("href"));				
				temp = driver.findElement(By.xpath("*//table[@id='foodResultlist']/tbody"));
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
				if(exist[groupCheck++]) sebze.add(new NutritionList(name, nutritions));
				if(exist[groupCheck++]) eturunu.add(new NutritionList(name, nutritions));
				if(exist[groupCheck++]) geleneksel.add(new NutritionList(name, nutritions));
				if(exist[groupCheck++]) meyve.add(new NutritionList(name, nutritions));
				if(exist[groupCheck++]) balik.add(new NutritionList(name, nutritions));
				if(exist[groupCheck++]) icecek.add(new NutritionList(name, nutritions));
				if(exist[groupCheck++]) tahil.add(new NutritionList(name, nutritions));
				if(exist[groupCheck++]) yaglitohum.add(new NutritionList(name, nutritions));
				if(exist[groupCheck++]) yaglar.add(new NutritionList(name, nutritions));
				if(exist[groupCheck++]) muhtelif.add(new NutritionList(name, nutritions));
				if(exist[groupCheck++]) sekerli.add(new NutritionList(name, nutritions));
				if(exist[groupCheck++]) ozel.add(new NutritionList(name, nutritions));
				if(exist[groupCheck++]) suturunleri.add(new NutritionList(name, nutritions));
				if(exist[groupCheck++]) yumurta.add(new NutritionList(name, nutritions));
				driver.navigate().back();
				groupCheck=0;
			}


		    Gson gson2 = new GsonBuilder().create();
			try (Writer writer = new FileWriter("sebze.json")) {
			    gson2.toJson(sebze, writer);
			}
			try (Writer writer = new FileWriter("eturunu.json")) {
			    gson2.toJson(eturunu, writer);
			}
			try (Writer writer = new FileWriter("geleneksel.json")) {
			    gson2.toJson(geleneksel, writer);
			}
			try (Writer writer = new FileWriter("meyve.json")) {
			    gson2.toJson(meyve, writer);
			}
			try (Writer writer = new FileWriter("balik.json")) {
			    gson2.toJson(balik, writer);
			}
			try (Writer writer = new FileWriter("icecek.json")) {
			    gson2.toJson(icecek, writer);
			}
			try (Writer writer = new FileWriter("tahil.json")) {
			    gson2.toJson(tahil, writer);
			}
			try (Writer writer = new FileWriter("yaglitohum.json")) {
			    gson2.toJson(yaglitohum, writer);
			}
			try (Writer writer = new FileWriter("yaglar.json")) {
			    gson2.toJson(yaglar, writer);
			}
			try (Writer writer = new FileWriter("muhtelif.json")) {
			    gson2.toJson(muhtelif, writer);
			}
			try (Writer writer = new FileWriter("sekerli.json")) {
			    gson2.toJson(sekerli, writer);
			}
			try (Writer writer = new FileWriter("ozel.json")) {
			    gson2.toJson(ozel, writer);
			}
			try (Writer writer = new FileWriter("suturunleri.json")) {
			    gson2.toJson(suturunleri, writer);
			}
			try (Writer writer = new FileWriter("yumurta.json")) {
			    gson2.toJson(yumurta, writer);
			}
		System.out.println("Done: "+elements.size());
		driver.close();
	}
}
