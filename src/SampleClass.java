import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SampleClass {
    private WebDriver driver;
    private boolean success;

    public void chrome() throws IOException {
        InputStream stream = SampleClass.class.getClassLoader().getResourceAsStream("webdrivers/chromedriver");
        if (stream != null) {
            this.extractDriver(stream, "chromedriver");
        }

        System.setProperty("webdriver.chrome.driver", System.getProperty("java.io.tmpdir") + "chromedriver");
        this.driver = new ChromeDriver();
    }

    public void firefox() throws IOException {
        InputStream stream = SampleClass.class.getClassLoader().getResourceAsStream("webdrivers/geckodriver");
        if (stream != null) {
            this.extractDriver(stream, "geckodriver");
        }

        System.setProperty("webdriver.gecko.driver", System.getProperty("java.io.tmpdir") + "geckodriver");
        this.driver = new FirefoxDriver();
    }

    public void run() {
        this.driver.get("https://www.google.com");

        assertAttributeValueOfElementByClass("gNO89b", "value", "Google Search");
        assertAttributeValueOfElementByClass("RNmpXc", "value", "I'm Feeling Lucky");

        assertAttributeValueOfElementById("hplogo", "alt", "Google");

        assertAttributeValueOfElementByXPath("//*[@id=\"fsl\"]/a[4]", "text", "  How Search works ");

        this.driver.close();

        if (this.success) {
            System.out.println("Test finished successfully");
        } else {
            System.err.println("Test finished with errors");
        }
    }

    public void assertAttributeValueOfElementByClass(String className, String attributeName, String expectedValue) {
        WebElement element = this.driver.findElement(By.className(className));
        assertAttributeValueOfElement(element, attributeName, expectedValue);
    }

    public void assertAttributeValueOfElementById(String id, String attributeName, String expectedValue) {
        WebElement element = this.driver.findElement(By.id(id));
        assertAttributeValueOfElement(element, attributeName, expectedValue);
    }

    public void assertAttributeValueOfElementByXPath(String xPath, String attributeName, String expectedValue) {
        WebElement element = this.driver.findElement(By.xpath(xPath));
        assertAttributeValueOfElement(element, attributeName, expectedValue);
    }

    public void assertAttributeValueOfElement(WebElement element, String attributeName, String expectedValue) {
        String value = element.getAttribute(attributeName);

        if (!value.equals(expectedValue)) {
            this.success = false;
            System.err.printf("FAILED for attribute %s. Expected %s Got %s\n", attributeName, expectedValue, value);
        }
    }

    private void extractDriver(InputStream stream, String filename) throws IOException {
        File f = new File(System.getProperty("java.io.tmpdir") + filename);
        FileOutputStream fos = new FileOutputStream(f);

        byte[] buf = new byte[2048];
        int r;
        while (-1 != (r = stream.read(buf))) {
            fos.write(buf, 0, r);
        }

        f.setExecutable(true, false);
    }
}
