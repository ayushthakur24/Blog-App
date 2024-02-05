package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(
        description = "PostDto model information"
)
public class PostDto {
    private long id;

    @Schema(
            description = "Blog Post title"
    )
    @NotEmpty
    @Size(min = 2,message = "Title should have atleast 2 characters")
    private String title;

    @Schema(
            description = "Blog Post title"
    )
    @NotEmpty
    @Size(min = 10,message = "Post Description should have 10 or more characters")
    private String description;

    @NotEmpty
    private String content;

    private Set<CommentDto> comments;

    private Long categoryId;
}
