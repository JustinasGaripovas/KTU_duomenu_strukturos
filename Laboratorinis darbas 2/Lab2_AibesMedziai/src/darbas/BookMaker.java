/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darbas;
import java.util.Random;

/**
 *
 * @author justin
 */
public class BookMaker {
        private static Book[] books;
        private static int index=0;
        
        Random rand = new Random();
        
    public static Book[] generate(int amount) {
        books = new Book[amount];
        for (int i = 0; i < amount; i++) {
            books[i] = createBook();
            index++;
        }
        return shuffle();
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
        Book currentBook = new Book("test_book","Tadas",1992+index,"TR"+ index, index +10);
        index++;
        return currentBook;
    }
    
}
