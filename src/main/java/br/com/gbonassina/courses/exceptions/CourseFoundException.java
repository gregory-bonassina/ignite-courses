package br.com.gbonassina.courses.exceptions;

public class CourseFoundException extends RuntimeException {
    public CourseFoundException() {
        super( "Course already exists in this category" );
    }
}
