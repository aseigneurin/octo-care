package fr.octocare.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class HexaDTO {

    private String id;

    private String email;

    private String name;

    public HexaDTO() {
    }

    public HexaDTO(String id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public void setId(String id) {
        this.id = id;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
