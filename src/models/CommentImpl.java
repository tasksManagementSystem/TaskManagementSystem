package models;

import models.contracts.Comment;

public class CommentImpl implements Comment {

    private String comment;
    private String author;

    public CommentImpl(String comment, String author) {
        this.comment = comment;
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public String getAuthor() {
        return author;
    }
}
