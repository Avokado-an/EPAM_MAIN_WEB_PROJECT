package com.anton.gym.exception;

/**
 * The {@code ControllerException} class represents ControllerException.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class ControllerException extends Exception {
    /**
     * create object of class
     */
    public ControllerException() {
    }

    /**
     * create object of class
     *
     * @param message
     */
    public ControllerException(String message) {
        super(message);
    }

    /**
     * create object of class
     *
     * @param message
     * @param cause
     */
    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * create object of class
     *
     * @param cause
     */
    public ControllerException(Throwable cause) {
        super(cause);
    }
}
