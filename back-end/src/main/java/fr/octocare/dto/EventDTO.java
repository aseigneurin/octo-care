package fr.octocare.dto;

import java.util.Date;

import fr.octocare.entity.Octo;

public class EventDTO {

    private String id;

    private Octo octo;

    private Date date;

    private String text;

    public EventDTO() {
    }

    public EventDTO(String id, Date date, String text) {
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

    public Octo getOcto() {
        return octo;
    }

    public void setOcto(Octo octo) {
        this.octo = octo;
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
