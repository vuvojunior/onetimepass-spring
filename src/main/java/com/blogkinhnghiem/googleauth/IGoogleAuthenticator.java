
package com.blogkinhnghiem.googleauth;


/**
 * Google Authenticator library interface.
 */
@SuppressWarnings ("UnusedDeclaration")
public interface IGoogleAuthenticator
{
    /**
     * This method generates a new set of credentials including: <ol> <li>Secret key.</li> <li>Validation code.</li>
     * <li>A list of scratch codes.</li> </ol>
     * <p/>
     * The user must register this secret on their device.
     *
     * @return secret key
     */
    GoogleAuthenticatorKey createCredentials();

    /**
     * This method generates a new set of credentials invoking the <code>#createCredentials</code> method with no
     * arguments. The generated credentials are then saved using the configured <code>#ICredentialRepository</code>
     * service.
     * <p/>
     * The user must register this secret on their device.
     *
     * @param userName the user name.
     * @return secret key
     */
    GoogleAuthenticatorKey createCredentials(String userName);

    /**
     * Checks a verification code against a secret key using the current time. The algorithm also checks in a time
     * window whose size determined by the <code>windowSize</code> property of this class.
     * <p/>
     * The default value of 30 seconds recommended by RFC 6238 is used for the interval size.
     *
     * @param secret the Base32 encoded secret key.
     * @param verificationCode the verification code.
     * @return <code>true</code> if the validation code is valid, <code>false</code> otherwise.
     * @throws GoogleAuthenticatorException if a failure occurs during the calculation of the validation code. The only
     * failures that should occur are related with the cryptographic functions provided by the JCE.
     */
    boolean authorize(String secret, int verificationCode)
            throws GoogleAuthenticatorException;

    /**
     * This method validates a verification code of the specified user whose private key is retrieved from the
     * configured credential repository. This method delegates the validation to the <code>#authorize</code> method.
     *
     * @param userName The user whose verification code is to be validated.
     * @param verificationCode The validation code.
     * @return <code>true</code> if the validation code is valid, <code>false</code> otherwise.
     * @throws GoogleAuthenticatorException if an unexpected error occurs.
     * @see #authorize(String, int)
     */
    boolean authorizeUser(String userName, int verificationCode)
            throws GoogleAuthenticatorException;
}
