package gluon.projects.myexception;

public class ApiBinanceException extends RuntimeException{
    public ApiBinanceException(Throwable throwable) {
        super(throwable);
    }
}
