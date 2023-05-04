package uz.market.uzum.exceptions;

public class DuplicateUserRoleCodeException extends RuntimeException {
    public DuplicateUserRoleCodeException(String message) {
        super(message);
    }
}
