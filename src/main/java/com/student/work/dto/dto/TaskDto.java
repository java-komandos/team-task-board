package com.student.work.dto.dto;


import jakarta.validation.constraints.NotNull;

public class TaskDto {
    private Long id;
    @NotNull(message = "title shouldn't be null")
    private String title;
    private boolean completed;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
