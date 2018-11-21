package darbas;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import laborai.studijosktu.Ks;
import java.util.Comparator;

public class Book implements laborai.studijosktu.KTUable<Book> {

    final static private int maxAge = 20;

    private String name;
    private String author;
    private int age;
    private String code;
    private double price;

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getAge() {
        return age;
    }

    public static int getMaxAge() {
        return maxAge;
    }

    public String getAuthor() {
        return author;
    }

    public Book create(String dataString) {
        Book a = new Book();
        a.parse(dataString);
        return a;
    }

    public Book(){

    }
    
     public Book(String dataString) {
        this.parse(dataString);
    }

    public Book(String name, String author, int age, String code, double price){
        this.name = name;
        this.author = author;
        this.age = age;
        this.code = code;
        this.price = price;
        validate();
    }

    @Override
    public String validate() {
        String errorType = "";

        if (age > maxAge)
            errorType = "Persena [" + maxAge + ":" + age + "]";

        return errorType;
    }

    @Override
    public void parse(String dataString) {
        try {   // ed - tai elementarūs duomenys, atskirti tarpais
            Scanner ed = new Scanner(dataString);
            name   = ed.next();
            author = ed.next();
            age = ed.nextInt();
            code    = ed.next();
            setPrice(ed.nextDouble());

        } catch (InputMismatchException e) {
            Ks.ern("Blogas duomenų formatas -> " + dataString);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie auto -> " + dataString);
        }
    }

    @Override
    public String toString(){  // surenkama visa reikalinga informacija
        return String.format("%-8s %-8s %4d %-8s %8.1f %s",
                getName(), getAuthor(), getAge(), getCode(), getPrice(), validate());
    };

    @Override
    public int compareTo(Book e) {
        // lyginame pagal svarbiausią požymį - kainą
        double priceOther = e.getPrice();
        if (getPrice() < priceOther) return -1;
        if (getPrice() > priceOther) return +1;
        return 0;
    }

     public static Comparator<Book> byPrice = (Book a1, Book a2) -> {
        // didėjanti tvarka, pradedant nuo mažiausios
        if (a1.price < a2.price) {
            return -1;
        }
        if (a1.price > a2.price) {
            return +1;
        }
        return 0;
    };
}
