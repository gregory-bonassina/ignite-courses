package br.com.gbonassina.courses.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException() {
        super( "Course not exists" );
    }
}