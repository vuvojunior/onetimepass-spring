package com.blogkinhnghiem.googleauth;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicInteger;

class ReseedingSecureRandom
{
    private static final int MAX_OPERATIONS = 1000000;
    private final String provider;
    private final String algorithm;
    private final AtomicInteger count = new AtomicInteger(0);
    private SecureRandom secureRandom;

    @SuppressWarnings ("UnusedDeclaration")
    ReseedingSecureRandom()
    {
        this.algorithm = null;
        this.provider = null;

        buildSecureRandom();
    }

    @SuppressWarnings ("UnusedDeclaration")
    ReseedingSecureRandom(String algorithm)
    {
        if (algorithm == null)
        {
            throw new IllegalArgumentException("Algorithm cannot be null.");
        }

        this.algorithm = algorithm;
        this.provider = null;

        buildSecureRandom();
    }

    ReseedingSecureRandom(String algorithm, String provider)
    {
        if (algorithm == null)
        {
            throw new IllegalArgumentException("Algorithm cannot be null.");
        }

        if (provider == null)
        {
            throw new IllegalArgumentException("Provider cannot be null.");
        }

        this.algorithm = algorithm;
        this.provider = provider;

        buildSecureRandom();
    }

    private void buildSecureRandom()
    {
        try
        {
            if (this.algorithm == null && this.provider == null)
            {
                this.secureRandom = new SecureRandom();
            }
            else if (this.provider == null)
            {
                this.secureRandom = SecureRandom.getInstance(this.algorithm);
            }
            else
            {
                this.secureRandom = SecureRandom.getInstance(this.algorithm, this.provider);
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new GoogleAuthenticatorException(
                    String.format(
                            "Could not initialise SecureRandom " +
                                    "with the specified algorithm: %s",
                            this.algorithm
                    ), e
            );
        }
        catch (NoSuchProviderException e)
        {
            throw new GoogleAuthenticatorException(
                    String.format(
                            "Could not initialise SecureRandom " +
                                    "with the specified provider: %s",
                            this.provider
                    ), e
            );
        }
    }

    void nextBytes(byte[] bytes)
    {
        if (count.incrementAndGet() > MAX_OPERATIONS)
        {
            synchronized (this)
            {
                if (count.get() > MAX_OPERATIONS)
                {
                    buildSecureRandom();
                    count.set(0);
                }
            }
        }

        this.secureRandom.nextBytes(bytes);
    }
}
