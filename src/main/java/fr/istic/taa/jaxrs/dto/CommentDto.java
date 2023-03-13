package fr.istic.taa.jaxrs.dto;

public class CommentDto {

    private String text;
    private long userId;
    private long ticketId;

    public CommentDto(String text, long userId, long ticketId) {
        this.text = text;
        this.userId = userId;
        this.ticketId = ticketId;
    }

    public CommentDto() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }
}
