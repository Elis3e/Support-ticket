package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.domain.User;

import java.util.List;

public class TicketDto {

    private String title;
    private String body;
    private long creatorId;
    private List<Tag> tags;


    public TicketDto(String title, String body, long creatorId, List<Tag> tags) {
        this.title = title;
        this.body = body;
        this.creatorId = creatorId;
        this.tags = tags;
    }

    public TicketDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
