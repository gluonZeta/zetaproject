package gluon.projects.exceptions;

public class MyFileNotFoundException extends RuntimeException {

    public MyFileNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
