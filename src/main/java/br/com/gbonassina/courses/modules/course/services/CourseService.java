package br.com.gbonassina.courses.modules.course.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import br.com.gbonassina.courses.exceptions.CourseFoundException;
import br.com.gbonassina.courses.exceptions.CourseNotFoundException;
import br.com.gbonassina.courses.modules.course.CourseEntity;
import br.com.gbonassina.courses.modules.course.CourseRepository;
import br.com.gbonassina.courses.modules.course.dto.CourseResourceDTO;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    /**
     * add
     * 
     * @param courseEntity CourseEntity
     * @return CourseEntity
     */
    public CourseEntity add(CourseEntity courseEntity) {
        this.courseRepository.findByNameAndCategory(courseEntity.getName(), courseEntity.getCategory())
                             .ifPresent( user -> {
                                 throw new CourseFoundException();
                             });

        return this.courseRepository.save(courseEntity);
    }

    /**
     * update
     * 
     * @param id UUID
     * @param requestParams Map<String, String>
     * @return CourseEntity
     */
    public CourseEntity update(@NonNull UUID id, Map<String, String> requestParams) {
        String name = null;
        String category = null;

        for (var entry : requestParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.equalsIgnoreCase("name")) {
                name = value;
            } else if (key.equalsIgnoreCase("category")) {
                category = value;
            }
        }

        CourseEntity courseEntity = this.courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);

        if (courseEntity != null) {
            if (name != null) {
                courseEntity.setName(name);
            }
            
            if (category != null) {
                courseEntity.setCategory(category);
            }

            return courseRepository.save(courseEntity);
        }

        return courseEntity;
    }

    /**
     * toogleState
     * 
     * @param id UUID
     * @return CourseEntity
     */
    public CourseEntity toogleState(@NonNull UUID id) {
        CourseEntity courseEntity = this.courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);

        switch (courseEntity.getState()) {
            case INACTIVE:
                    courseEntity.setState(CourseEntity.State.ACTIVE);
                break;
            case ACTIVE:
                    courseEntity.setState(CourseEntity.State.INACTIVE);
                break;
        }

        return courseRepository.save(courseEntity);
    }

    /**
     * get
     * 
     * @param requestParams Map<String, String> 
     * @return List<CourseResourceDTO>
     */
    public List<CourseResourceDTO> get(Map<String, String> requestParams) {
        String name = null;
        String category = null;

        for (var entry : requestParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.equalsIgnoreCase("name")) {
                name = value;
            } else if (key.equalsIgnoreCase("category")) {
                category = value;
            }
        }

        List<CourseEntity> courses = new ArrayList<CourseEntity>();

        if (name != null && category != null) {
            courses = courseRepository.findAllByNameAndCategory(name, category);
        } else if (name != null) {
            courses = courseRepository.findAllByNameIgnoreCaseContaining(name);
        } else if (category != null) {
            courses = courseRepository.findAllByCategoryIgnoreCaseContaining(category);
        } else {
            courses = courseRepository.findAll();
        }

        return courses.stream().map(course -> {
            return CourseResourceDTO.builder()
                                        .id(course.getId())
                                        .name(course.getName())
                                        .category(course.getCategory())
                                        .state(course.getState())
                                        .createdAt(course.getCreatedAt())
                                        .updateAt(course.getUpdateAt())
                                        .build();
        }).toList();
    }
}
