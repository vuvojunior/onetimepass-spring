package com.blogkinhnghiem.googleauth;

public class GoogleAuthenticatorException extends RuntimeException
{

    /**
     * Builds an exception with the provided error message.
     *
     * @param message the error message.
     */
    public GoogleAuthenticatorException(String message)
    {
        super(message);
    }

    /**
     * Builds an exception with the provided error mesasge and the provided cuase.
     *
     * @param message the error message.
     * @param cause the cause.
     */
    public GoogleAuthenticatorException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
