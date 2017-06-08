package ru.bmstu.rk9.mechanics.exceptions;

public class MaximumCapacityException extends DispatchingException {

    public MaximumCapacityException() {
        message = "Billet capacity exceeded";
    }

    public MaximumCapacityException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return "";
    }
}
