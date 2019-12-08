package com.example.clientserverassignment;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    int id;
    String bookTitle;
    String bookAuthor;
    String bookPublisher;
    String bookYear;

    public Book(){

    }

    public Book(int id, String bookTitle, String bookAuthor, String bookPublisher, String bookYear){
        this.id = id;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.bookYear = bookYear;
    }

    protected Book(Parcel in) {
        id = in.readInt();
        bookTitle = in.readString();
        bookAuthor = in.readString();
        bookPublisher = in.readString();
        bookYear = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public int getId() { return id; }

    public void setId(int id){this.id = id;}

    public String getbookTitle() {
        return bookTitle;
    }

    public void setbookTitle(String title) { bookTitle = title; }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String publisher) { bookPublisher = publisher; }

    public String getbookAuthor() {
        return bookAuthor;
    }

    public void setbookAuthor(String author) { bookAuthor = author; }

    public String getbookYear() {
        return bookYear;
    }

    public void setbookYear(String year) { bookYear = year; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(bookTitle);
        dest.writeString(bookAuthor);
        dest.writeString(bookPublisher);
        dest.writeString(bookYear);
    }
}
