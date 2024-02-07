package br.com.gbonassina.courses.modules.course;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    Optional<CourseEntity> findByNameAndCategory(String name, String category);

    List<CourseEntity> findAllByNameAndCategory(String name, String category);
    List<CourseEntity> findAllByNameIgnoreCaseContaining(String name);
    List<CourseEntity> findAllByCategoryIgnoreCaseContaining(String category);
}
