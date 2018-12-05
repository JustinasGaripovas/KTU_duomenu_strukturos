
import java.io.Console;


public class KonsolesDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Console cns = System.console();
        String s = cns.readLine();
        int a = Integer.parseInt(s);
        System.out.println("Kvadr=" + a*a);
    }

}
