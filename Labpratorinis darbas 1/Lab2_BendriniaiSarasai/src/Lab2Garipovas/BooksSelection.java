package Lab2Garipovas;

import studijosKTU.Ks;
import studijosKTU.ListKTU;
import studijosKTU.ListKTUx;

public class BooksSelection {

    private ListKTUx<Book> bookListKTU = new ListKTUx<Book>(new Book());

    public void getAllBooks()
    {
        for (Book bo:bookListKTU) {
            Ks.oun(bo);
        }
    }

    public void removeMostExpensiveBook()
    {
        double max = 0;
        Book book = new Book();

        for (Book bo:bookListKTU) {
            if(bo.getPrice() > max)
            {
                max = bo.getPrice();
                book = bo;
            }
        }

        removeBook(bookListKTU.indexOf(book));
    }



    public void getNewBooks(int ageBelow)
    {
        for (Book bo:bookListKTU) {
            if (bo.getAge() < ageBelow) {
                Ks.oun(bo);
            }
        }
    }

    public void getBooksByAuthor(String authorName)
    {
        for (Book bo:bookListKTU) {
            if (bo.getAuthor().equals(authorName)) {
                Ks.oun(bo);
            }
        }
    }

    public void getCheapBooks(double priceBelow)
    {
        for (Book bo:bookListKTU) {
            if (bo.getPrice() < priceBelow) {
                Ks.oun(bo);
            }
        }
    }

    public void addToList(Book book)
    {
        bookListKTU.add(book);
    }

    public void addToListWithIndex(int index,Book book)
    {
        bookListKTU.add(index,book);
    }


    public void removeBook(int bookIndex)
    {
        bookListKTU.remove(bookIndex);
    }

    public void editBook(int bookIndex, Book newBook)
    {
        bookListKTU.set(bookIndex,newBook);
    }


}
