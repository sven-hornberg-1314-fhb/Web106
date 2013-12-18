/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/configuration.html
*/

import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.chrome.ChromeDriver

System.setProperty("webdriver.chrome.driver", 
                                "/usr/local/bin/chromedriver");

driver = { new ChromeDriver() }
