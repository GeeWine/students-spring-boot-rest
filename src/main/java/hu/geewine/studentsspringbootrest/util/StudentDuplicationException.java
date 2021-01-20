package hu.geewine.studentsspringbootrest.util;

public class StudentDuplicationException extends RuntimeException {

    public StudentDuplicationException() {
        super("There is already a user with the same data!");
    }

}
