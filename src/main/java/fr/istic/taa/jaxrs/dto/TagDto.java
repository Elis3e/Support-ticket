package fr.istic.taa.jaxrs.dto;

public class TagDto {

    private String label;

    public TagDto(String label) {
        this.label = label;
    }

    public TagDto() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
