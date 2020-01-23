import java.io.IOException;

public class EntryPoint extends SampleClass {
    EntryPoint(String browser) throws IOException {
        super(browser);
    }

    public static void main(String[] args) {
        for(String arg: args) {
            try {
                EntryPoint ep = new EntryPoint(arg);
                ep.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
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
}
