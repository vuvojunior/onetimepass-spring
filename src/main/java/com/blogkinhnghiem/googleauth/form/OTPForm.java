package com.blogkinhnghiem.googleauth.form;

/**
 * Created by vvo on 8/19/15.
 */
public class OTPForm
{
    private String qrCodeURL;

    private int verificationCode = 000000;

    public String getQrCodeURL()
    {
        return qrCodeURL;
    }

    public void setQrCodeURL(String qrCodeURL)
    {
        this.qrCodeURL = qrCodeURL;
    }

    public int getVerificationCode()
    {
        return verificationCode;
    }

    public void setVerificationCode(int verificationCode)
    {
        this.verificationCode = verificationCode;
    }
}
