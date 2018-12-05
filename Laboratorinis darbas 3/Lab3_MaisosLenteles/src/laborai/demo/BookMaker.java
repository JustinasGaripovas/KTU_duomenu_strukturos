package laborai.demo;

import laborai.gui.MyException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.IntStream;

public class BookMaker {

     private static Book[] books;
        private static int index=0;
        
    private static String[] raktai;
    public int kiekis = 0;
    private int idKiekis = 0;
        
    Random rand = new Random();
        
    public static Book[] generate(int amount)
    {
      
        books = new Book[amount];
        for (int i = 0; i < amount; i++) {
            books[i] = createBook();
            index++;
        }
  
        return books;
 
    }
        
    public Book[] generate(int aibesDydis,
            int aibesImtis) throws MyException {
        if (aibesImtis > aibesDydis) {
            aibesImtis = aibesDydis;
        }
        
        books = new Book[aibesDydis];
        for (int i = 0; i < aibesDydis; i++) {
            books[i] = createBook();
            index++;
        }
        
        raktai = gamintibookIds(aibesDydis);
                this.kiekis = aibesImtis;
        
        return Arrays.copyOf(books, aibesImtis);
 
    }
    
    private static final String ID_CODE = "BOOK";
    private static int serNr = 0; 
    
    public static String[] gamintibookIds(int kiekis) {
        String[] raktai = IntStream.range(0, kiekis)
                .mapToObj(i -> ID_CODE + (serNr++))
                .toArray(String[]::new);
        Collections.shuffle(Arrays.asList(raktai));
       
     
        return raktai;
    }
    
    public String gautiIsBazesbookId() {
        if (raktai == null) {
            throw new MyException("carsIdsNotGenerated");
        }
        if (idKiekis >= raktai.length) {
            idKiekis = 0;
        }
        return raktai[idKiekis++];
    }
    
    public Book parduotibook() {
        if (books == null) {
            throw new MyException("carsNotGenerated");
        }
        if (kiekis < books.length) {
            return books[kiekis++];
        } else {
            throw new MyException("allSetStoredToMap");
        }
    }
    
    public static void resetIndex()
    {
        index = 0;
    }
    
    public static Book getBook(int offset)
    {
        return books[offset];
    }
    
    private static Book[] shuffle()
    {
        for (int i = books.length - 1; i > 0; i--) {
            int j = (int)Math.floor(Math.random() * (i + 1));
            Book temp = books[i];
            books[i] = books[j];
            books[j] = temp;
        }
        
        return books;
    }
    
    public static Book createBook()
    {
        Book currentBook = new Book("test_book","Tadas",1992+index,"Book"+ index, index +10);
        index++;
        return currentBook;
    }
}