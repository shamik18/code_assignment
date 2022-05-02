package com.mycomp.reader;
import  com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;

public class CustomCSVReader extends CSVReader{
    public CustomCSVReader(Reader reader) {
        super(reader);
    }

    @Override
    public String[] readNext() throws IOException {
        return super.readNext();
    }
}
