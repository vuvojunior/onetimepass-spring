package com.blogkinhnghiem.googleauth.controller;

import com.blogkinhnghiem.googleauth.GoogleAuthenticatorKey;
import com.blogkinhnghiem.googleauth.GoogleAuthenticatorQRGenerator;
import com.blogkinhnghiem.googleauth.IGoogleAuthenticator;
import com.blogkinhnghiem.googleauth.form.OTPForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/otp")
public class OTPController
{
    @Autowired
    private IGoogleAuthenticator googleAuthenticator;

    private String orgName = "blogkinhnghiem.com";

    private String username = "exampleuser";

    @RequestMapping (value = "/setup", method = RequestMethod.GET)
    public String setup(Model model)
    {
        final GoogleAuthenticatorKey key = googleAuthenticator.createCredentials();
        final String qrCodeURL = GoogleAuthenticatorQRGenerator.getOtpAuthURL(orgName, username, key);
        OTPForm otpForm = new OTPForm();
        otpForm.setQrCodeURL(qrCodeURL);
        model.addAttribute("otpForm", otpForm);
        return "setup";
    }

    @RequestMapping (value = "/setup", method = RequestMethod.POST)
    public String doSetup(OTPForm otpForm, Model model)
    {
        boolean authorized = googleAuthenticator.authorizeUser(username, otpForm.getVerificationCode());
        model.addAttribute("optForm", otpForm);
        model.addAttribute("authorized", authorized);
        return "setup";
    }
}
