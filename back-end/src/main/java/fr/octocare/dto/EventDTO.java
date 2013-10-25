package fr.octocare.dto;

import java.util.Date;

public class EventDTO {

    private String id;

    private HexaDTO author;

    private Date date;

    private String text;

    public EventDTO() {
    }

    public EventDTO(HexaDTO author, String id, Date date, String text) {
        this.author = author;
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HexaDTO getAuthor() {
        return author;
    }

    public void setAuthor(HexaDTO author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
