package org.example;

import org.example.util.Book;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainTest {

    public static void main(String[] args) {


        List<Book> books = Arrays.asList(new Book("basta","accion") , new Book("Hola ya!","drama"),new Book("Por ahi","aventura"));

        Comparator<Book> comparatorBook = (b1,b2) -> b1.getGenre().compareTo(b1.getGenre());

        System.out.println(books.stream().sorted(comparatorBook.thenComparing(Book::getGenre)).collect(Collectors.toList()));

        System.out.println(books);
        {
            System.out.println("aqui");
        }
    }
    static {
        System.out.println("aqui2");
    }
}
