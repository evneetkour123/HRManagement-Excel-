package miniProject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup {
	public static WebDriver getChromeDriver() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		return driver;
	}

	public static WebDriver getEdgeDriver() {
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		return driver;
	}

	public static void main(String[] args) throws Exception {
		OrangeHumanResourceManagement hrm = new OrangeHumanResourceManagement();
		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				hrm.getChromeDriver();
				hrm.verifyURL();
			} else {
				hrm.getEdgeDriver();
				hrm.verifyURL();
			}

			hrm.testScript();
		}

	}
}
