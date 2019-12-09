package com.example.serverclientassignment;

import com.example.serverclientassignment.IBook;

interface IMyAidlInterface {
    String clickedShow(int whichAttribute, String argument);
    String clickedDelete(int whichAttribute, String argument);
    Book[] getBookList(String argument);
}

