package com.anton.gym.exception;

/**
 * The {@codeConnectionPoolException} class represents ConnectionPoolException.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class ConnectionPoolException extends Exception {
    /**
     * create object of class
     */
    public ConnectionPoolException() {
    }

    /**
     * create object of class
     *
     * @param message
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

    /**
     * create object of class
     *
     * @param message
     * @param cause
     */
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * create object of class
     *
     * @param cause
     */
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
