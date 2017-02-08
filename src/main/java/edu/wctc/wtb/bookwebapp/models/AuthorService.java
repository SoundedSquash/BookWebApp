/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.wtb.bookwebapp.models;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author wboyer
 */
public class AuthorService {
    public List<Author> getAllAuthors(){
        return Arrays.asList(
                new Author(1,"Chris Palmer",new Date()),
                new Author(2, "Will Boyer", new Date()),
                new Author(3, "Jim Lombardo", new Date())
        );
    }
}
