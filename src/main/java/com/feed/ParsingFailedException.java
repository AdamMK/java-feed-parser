package com.feed;

public class ParsingFailedException extends Exception{

    public ParsingFailedException(String errorMessage) {
        super(errorMessage);
    }
}
