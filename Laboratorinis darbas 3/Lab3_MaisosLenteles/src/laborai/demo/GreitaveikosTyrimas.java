package laborai.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import laborai.studijosktu.HashType;
import laborai.studijosktu.MapKTUx;
import laborai.gui.MyException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import laborai.studijosktu.MapKTUOA;

/**
 * @author eimutis
 */
public class GreitaveikosTyrimas {

    public static final String FINISH_COMMAND = "finishCommand";
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("laborai.gui.messages");

    
    private final BlockingQueue resultsLogger = new SynchronousQueue();
    private final Semaphore semaphore = new Semaphore(-1);
    private final Timekeeper tk;

    private final String[] TYRIMU_VARDAI = {"add0.75", "add0.25", "rem0.75", "rem0.25", "get0.75", "get0.25"};
    private final int[] TIRIAMI_KIEKIAI = {10000, 20000, 40000, 80000};

    private final MapKTUOA<String, Book> bookAtvaizdis
            = new MapKTUOA(10,HashType.DIVISION);
    private final MapKTUx<String, Book> bookAtvaizdis2
            = new MapKTUx(new String(), new Book(), 10, 0.25f, HashType.DIVISION);
    
    private final HashSet<String> bookZodynas
            = new HashSet();
    
    private final Queue<String> chainsSizes = new LinkedList<>();

    public GreitaveikosTyrimas() {
        semaphore.release();
        tk = new Timekeeper(TIRIAMI_KIEKIAI, resultsLogger, semaphore);
    }

    public void pradetiTyrima() {
        try {
            SisteminisTyrimas();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public void readData() throws IOException
    {
       
        String fileName = "/home/justin/Documents/Duomenu strukturos/Laboratorinis darbas 3/Lab3_MaisosLenteles/src/laborai/demo/zodynas.txt";
        File file = new File(fileName);
        FileReader fr;
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            while((line = br.readLine()) != null){
                
                bookZodynas.add(line);
                
                System.out.println(line);
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GreitaveikosTyrimas.class.getName()).log(Level.SEVERE, null, ex);
        }
    

    }

    public void SisteminisTyrimas() throws InterruptedException, IOException {
    
        
        try {
            chainsSizes.add(MESSAGES.getString("msg4"));
            chainsSizes.add("   kiekis      " + TYRIMU_VARDAI[0] + "   " + TYRIMU_VARDAI[1]);
            
            
            
            for (int k : TIRIAMI_KIEKIAI) {
                Book[] bookArray = BookMaker.generate(k);
                String[] bookIdArray = BookMaker.gamintibookIds(k);
                bookAtvaizdis.clear();
                bookAtvaizdis2.clear();
                
                readData();

                tk.startAfterPause();
                tk.start();

                for (int i = 0; i < k; i++) {
                    bookAtvaizdis.put(bookIdArray[i], bookArray[i]);
                }
                tk.finish(TYRIMU_VARDAI[0]);

                String str = "   " + k + "          " + bookAtvaizdis.getMaxChainSize();
                for (int i = 0; i < k; i++) {
                    bookAtvaizdis2.put(bookIdArray[i], bookArray[i]);
                }
                tk.finish(TYRIMU_VARDAI[1]);

                str += "         " + bookAtvaizdis2.getMaxChainSize();   
                chainsSizes.add(str);

                for (String s : bookIdArray) {
                    bookAtvaizdis.remove(s);
                }
                tk.finish(TYRIMU_VARDAI[2]);

                for (String s : bookIdArray) {
                    bookAtvaizdis2.remove(s);
                }
                tk.finish(TYRIMU_VARDAI[3]);

                bookZodynas.forEach((s) -> {
                    bookAtvaizdis2.contains(s);
                });
                tk.finish(TYRIMU_VARDAI[4]);

                bookZodynas.forEach((s) -> {
                    bookAtvaizdis2.contains(s);
                });
                tk.finish(TYRIMU_VARDAI[5]);
                tk.seriesFinish();
            }

            StringBuilder sb = new StringBuilder();
            chainsSizes.stream().forEach(p -> sb.append(p).append(System.lineSeparator()));
            tk.logResult(sb.toString());
            tk.logResult(FINISH_COMMAND);
        } catch (MyException e) {
            tk.logResult(e.getMessage());
        }
    }

    public BlockingQueue<String> getResultsLogger() {
        return resultsLogger;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
