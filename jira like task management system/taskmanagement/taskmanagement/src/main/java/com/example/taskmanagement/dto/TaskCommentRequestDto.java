package com.example.taskmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskCommentRequestDto {

	@NotNull(message = "User id is required")
	private Long userId;
	
	@NotBlank(message = "Comment is required")
	private String commentText;
	
	public TaskCommentRequestDto() {}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	
	
}
