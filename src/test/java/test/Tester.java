package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BrowserDriver;

public class Tester {

	public static void main(String[] args) {

		WebDriver driver = new BrowserDriver("CHROME");
		driver.findElement(By.className("random"));
	}

}
