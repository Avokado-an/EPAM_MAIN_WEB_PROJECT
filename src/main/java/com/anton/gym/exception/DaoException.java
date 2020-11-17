package com.anton.gym.exception;

/**
 * The {@code DaoException} class represents DaoException.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class DaoException extends Exception {
    /**
     * create object of class
     */
    public DaoException() {
    }

    /**
     * create object of class
     *
     * @param message
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * create object of class
     *
     * @param message
     * @param cause
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * create object of class
     *
     * @param cause
     */
    public DaoException(Throwable cause) {
        super(cause);
    }
}
