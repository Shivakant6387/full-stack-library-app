package com.shivakant.springbootlibrary.controller;

import com.shivakant.springbootlibrary.model.Book;
import com.shivakant.springbootlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/secure/currentloads/count")
    public int currentLoansCount() {
        String userEmail = "testuser@gmail.com";
        return bookService.currentLoansCount(userEmail);
    }

    @GetMapping("/secure/issecure/byuser")
    public Boolean checkoutBookByUser(@RequestParam Long bookId) throws Exception {
        String userEmail = "testuser@gmail.com";
        return bookService.checkoutBookByUser(userEmail, bookId);
    }

    @PutMapping("/secure/checkout")
    public Book checkoutBook(@RequestParam Long bookId) throws Exception {
        String userEmail = "testuser@gmail.com";
        return bookService.checkoutBook(userEmail, bookId);
    }
}
