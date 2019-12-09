package com.example.serverclientassignment;

import com.example.serverclientassignment.IBook;

interface IMyAidlInterface {
    String clickedShow(int whichAttribute, String argument);
    String clickedDelete(int whichAttribute, String argument);
    String insertCommand (in Book filledBook);
    String updateCommand (in Book filledBook);
    Book[] getBookList(String argument);
}

