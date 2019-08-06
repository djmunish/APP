public class TimeException extends Exception {

    public TimeException(long time) {
        super(String.valueOf(time));
    }
}
