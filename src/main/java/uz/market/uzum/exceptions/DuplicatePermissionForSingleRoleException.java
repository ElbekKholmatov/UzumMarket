package uz.market.uzum.exceptions;

public class DuplicatePermissionForSingleRoleException extends RuntimeException {
    public DuplicatePermissionForSingleRoleException(String message) {
        super(message);
    }
}
