package pl.marspc.recruitmenttask.exception;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException() {
        super("User with the provided login does not exist");
    }
}
