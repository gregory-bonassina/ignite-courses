package br.com.gbonassina.courses.modules.course.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gbonassina.courses.exceptions.CourseFoundException;
import br.com.gbonassina.courses.modules.course.CourseEntity;
import br.com.gbonassina.courses.modules.course.CourseRepository;

@Service
public class CreateCourseUseCase {
    
    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(CourseEntity courseEntity) {
        this.courseRepository.findByNameAndCategory(courseEntity.getName(), courseEntity.getCategory())
                             .ifPresent( user -> {
                                 throw new CourseFoundException();
                             });

        return this.courseRepository.save(courseEntity);
    }
}
