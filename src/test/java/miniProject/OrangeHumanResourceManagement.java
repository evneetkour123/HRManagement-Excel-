package miniProject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrangeHumanResourceManagement {
	public static WebDriver driver;
	
	
	//Launch the browser Chrome
	public void getChromeDriver() {
		driver=DriverSetup.getChromeDriver();
	}
	//Launch the browser Edge
	public void getEdgeDriver() {
		driver=DriverSetup.getEdgeDriver();
	}
	
	//enter URL and verify the URL
	public void verifyURL() throws InterruptedException
	{
		String URL="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		String app_title=driver.getCurrentUrl();
		if(URL.equals(app_title)) {
			System.out.println("User navigated to HR Mangaement URL and it is verified");
		}
		else {
			System.out.println("User didn't navigated to valid URL");
		}
	}
	public void testScript() throws Exception{
		
		//reading the data from the external excel sheet
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\TestData\\InputData.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheet("Sheet1");
		
		//enter the username
		XSSFRow r1 = sheet.getRow(0);
		XSSFCell c1 = r1.getCell(1);
		
		driver.findElement(By.name("username")).sendKeys(c1.toString());
		
		//enter the password
		XSSFRow r2=sheet.getRow(1);
		XSSFCell c2=r2.getCell(1);
		
		driver.findElement(By.name("password")).sendKeys(c2.toString());
		
		
		//click the "login" button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//verify the title of the dashboard page
		String act_label=driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[1]/span/h6")).getText();
		String exp_label="Dashboard";
			
		if(act_label.equals(exp_label)) {
			System.out.println("Dashboard is visible");
		}else {
			System.out.println("Dashboard is not visible");
		}
			
		//go to admin tab
		WebElement admin=driver.findElement(By.xpath("//span[contains(.,'Admin')]"));
		admin.click();
		
		//Go to the Job tab and check ‘Job Titles’ is there or not.
		WebElement job=driver.findElement(By.xpath("//span[contains(.,'Job')]"));
		job.click();
		
		String act_title=null;
		try {
			act_title=driver.findElement(By.linkText("Job Titles")).getText();
		}
		catch(NoSuchElementException e) {
			act_title="";
		}
		String exp_title="Job Titles";
		if(act_title.equals(exp_title)) {
			System.out.println("Job Titles is visible");
		}
		else {
			System.out.println("Job Titles is not visible");
		}
		
		//Click on “Job Titles”
	
		WebElement jobTitle=driver.findElement(By.linkText("Job Titles"));
		jobTitle.click();
		
		//getting the size of job list
		List<WebElement> option=driver.findElements(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[2]/div/div[1]/div[2]/div[1]"));
		System.out.println("size:" +option.size());
		
		FileOutputStream file1=new FileOutputStream("C:\\Users\\2317554\\OneDrive - Cognizant\\HRManagement\\TestData\\OutputData.xlsx");
		XSSFWorkbook workbook1=new XSSFWorkbook();
		XSSFSheet sheet1=workbook1.createSheet("data");
		
		
		
		//printing the list of job titles
		for(int i=1;i<option.size();i++) {
			WebElement titles=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[2]/div["+i+"]/div[1]/div[2]/div[1]"));
			XSSFRow row1=sheet1.createRow(i);
			XSSFCell cell1=row1.createCell(0);
			cell1.setCellValue(titles.getText());
			
			System.out.println(titles.getText());
			
		}
		workbook1.write(file1);
		workbook1.close();
		file1.close();
		
		//Click on “Add Button” icon, add job as “Automation Tester”.
		WebElement add=driver.findElement(By.xpath("//button[contains(.,' Add')]"));
		add.click();
		
		
		//Fill the appropriate data in the fields “Job Title” and click on “Save”.
		
		XSSFRow r3=sheet.getRow(2);
		XSSFCell c3=r3.getCell(1);
		
		WebElement jobtitle=driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input"));
		jobtitle.click();
		jobtitle.sendKeys(c3.toString());
		
		WebElement save=driver.findElement(By.xpath("//button[@type='submit']"));
		save.click();
		
		workbook.close();
		file.close();
		
		//Logout and close the browser
		WebElement dropDown=driver.findElement(By.xpath("//div[@id='app']/div/div/header/div/div[2]/ul/li/span/i"));
		dropDown.click();
		
		WebElement logOut=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		logOut.click();
		
		driver.quit();
		
		

	}
}

