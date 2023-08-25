package pl.marspc.recruitmenttask.exception;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException() {
        super("User with the provided login does not exist");
    }
}
