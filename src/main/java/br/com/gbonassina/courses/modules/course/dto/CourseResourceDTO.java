package br.com.gbonassina.courses.modules.course.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.gbonassina.courses.modules.course.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResourceDTO {
    
    private UUID id;
    private String name;
    private String category;
    private CourseEntity.State state;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
