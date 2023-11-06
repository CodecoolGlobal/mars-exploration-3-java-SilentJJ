package com.codecool.stackoverflowtw.dao;

public class QuestionsDaoJdbc implements QuestionsDAO {
    @Override
    public void sayHi() {
        System.out.println("Hi DAO!");
    }
    // itt lesznek a prepare statementek
}
