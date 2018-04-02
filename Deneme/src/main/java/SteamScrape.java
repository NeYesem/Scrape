import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SteamScrape {

	public static void main(String[] args) throws InterruptedException, ParseException, IOException {
		DesiredCapabilities dcap = new DesiredCapabilities();
		String[] phantomArgs = new  String[] {
		    "--webdriver-loglevel=NONE"
		};
		dcap.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);
		System.setProperty("phantomjs.binary.path", "F:\\Program Files\\Eclipse\\Workspace\\Deneme\\src\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
		WebDriver driver = new PhantomJSDriver();
		String URL ="http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=7F36A38F44AFD2BA4C77155CB1A5B3C2&steamid=76561197960434622&format=json";
		driver.get("https://www.google.com.tr/");
		System.out.println(driver.getTitle());
		//WebElement element = driver.findElement(By.xpath("//div[1]"));
		//System.out.println(element.getText());
		
	}
}