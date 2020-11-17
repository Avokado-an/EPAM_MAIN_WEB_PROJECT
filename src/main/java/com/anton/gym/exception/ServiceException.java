package com.anton.gym.exception;

/**
 * The {@code ServiceException} class represents ServiceException.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class ServiceException extends Exception {
    /**
     * create object of class
     */
    public ServiceException() {
    }

    /**
     * create object of class
     *
     * @param message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * create object of class
     *
     * @param message
     * @param cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * create object of class
     *
     * @param cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }
}
