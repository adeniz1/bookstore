package de.uni.koeln.se.bookstore.datamodel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name="tbl_book")  //name der Tabelle
@Entity
@Getter
@Setter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String name  ;
    private String author ;
    private Integer dateYear ;

    private Double price ;

    public Book() {

    }

    public Book( String name, String author, Integer dateYear,Double  price) {
        this.name = name;
        this.author = author;
        this.dateYear = dateYear;
        this.price = price ;
    }
}
