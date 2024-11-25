/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.music.model;
import java.io.Serializable;
/**
 *
 * @author Harraz
 */
public class MusicItem {
    private String title;
    private String year;
    private String genre;
    private String artist;
    
    public MusicItem(){
        title = "";
        year = "";
        genre = "";
        artist = "";
    }
    
    public MusicItem(String title, String year, String genre, String artist){
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.artist = artist;
    }
    
    public String getTitle(){
        return title;
    }
    public String getYear(){
        return year;
    }
    public String getGenre(){
        return genre;
    }
    public String getArtist(){
        return artist;
    }
}
