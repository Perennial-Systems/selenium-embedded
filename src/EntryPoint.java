public class EntryPoint {
    public static void main(String[] args) {
        for(String arg: args) {
            SampleClass sc = new SampleClass();

            try {
                switch (arg) {
                    case "chrome":
                        sc.chrome();
                        sc.run();
                        break;
                    case "firefox":
                        sc.firefox();
                        sc.run();
                        break;
                    default:
                        System.out.println("invalid driver name. choose one between chrome or firefox");
                        System.exit(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
