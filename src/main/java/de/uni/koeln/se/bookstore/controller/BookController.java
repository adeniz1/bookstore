package de.uni.koeln.se.bookstore.controller;

import de.uni.koeln.se.bookstore.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import de.uni.koeln.se.bookstore.datamodel.Book ;

import java.util.Collections;
import java.util.List ;
import java.util.Optional ;


@RestController
public class BookController {

    @Autowired
    BookRepo bookrepo;

    //Add a new  Book
    @PostMapping("/books")
    public ResponseEntity<Book> save(@RequestBody Book book) {
        try {
            return new ResponseEntity<>(bookrepo.save(book), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get all books
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> list = bookrepo.findAll();
            if (list.isEmpty() || list.size() == 0) {
                return new ResponseEntity<List<Book>>(list, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get a specific book
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getSingleBook(@PathVariable Integer id) {
        Optional<Book> book = bookrepo.findById(id);

        if (book.isPresent()) {
            return new ResponseEntity<Book>(book.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }

    //delete a specific book
    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Integer id) {
        try {
            Optional<Book> book = bookrepo.findById(id);
            if (book.isPresent()) {
                bookrepo.delete(book.get());
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/books/oldest")
    public ResponseEntity<Book> getOldestBook() {
        List<Book> list = bookrepo.findAll();
        Book oldest = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDateYear() < oldest.getDateYear()) {
                oldest = list.get(i);
            }

        }
        return new ResponseEntity<Book>(oldest,HttpStatus.OK) ;
    }

    @GetMapping("/books/recent")
    public ResponseEntity<Book> getRecentBook() {
        List<Book> list = bookrepo.findAll() ;
        Book recent = list.get(0);
        for(int i = 0 ; i< list.size();i++) {
            if(list.get(i).getDateYear() > recent.getDateYear()) {
                recent=list.get(i);
            }
        }
        return new ResponseEntity<Book>(recent,HttpStatus.OK) ;
    }






}
