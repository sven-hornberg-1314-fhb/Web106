import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.DesiredCapabilities
import web106.test.GebConfigUtils

/* little hackaround for standard grails test-app --function
* to download and then chose chromedriver of detected os system
 */
def osname = System.getProperty("os.name")

if(osname.startsWith('Windows')){

        //TODO

}else if (osname.startsWith('Linux')) {
        def chromeDriver = new File('test/drivers/chrome/chromedriver')
        GebConfigUtils.downloadDriver(chromeDriver, "http://chromedriver.storage.googleapis.com/2.8/chromedriver_linux64.zip")
        System.setProperty('webdriver.chrome.driver', chromeDriver.absolutePath)
        driver = { new ChromeDriver() }
}


/*Setting for environmental starting
* e.g grails -Dgeb.env=chrome test-app --functional
*/
environments {

    // run as “grails -Dgeb.env=chrome test-app --functional”
    // See: http://code.google.com/p/selenium/wiki/ChromeDriver
    chrome {
        def chromeDriver = new File('test/drivers/chrome/chromedriver')
        GebConfigUtils.downloadDriver(chromeDriver, "http://chromedriver.storage.googleapis.com/2.8/chromedriver_linux64.zip")
        System.setProperty('webdriver.chrome.driver', chromeDriver.absolutePath)
        driver = { new ChromeDriver() }
    }

    // run as “grails -Dgeb.env=firefox test-app --functional”
    // See: http://code.google.com/p/selenium/wiki/FirefoxDriver
    firefox {
        driver = { new FirefoxDriver() }
    }

}