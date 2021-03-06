/**
 *
 * File: header.js
 * Create: Oct. 12, 2018
 * Author: Zhan Shen
 * Modified By: Milos Boskovic
 * Clients: Michelle Bilek, Farheen Khan
 * Course: CST8334 Software Development Project - 2018F
 * Professor: Dr. Anu Thomas
 * Project: A Home to Share
 * Copyright @ 2018
 *
 * for popup logic control
 * @type {HTMLElement}
 */

$(function(){


    /*
    for sign in and forget password button
     */
    var loginPopup = $("#loginPopup");
    var forgotPasswordPopup = $("#forgotPasswordPopup");
    var loginBtn = $("#loginBtn");
    var forgotPasswordBtn = $("#forgotPasswordBtn");
    var closeLoginButton = $("#closeLoginButton");
    var closeForgetPasswordButton = $("#closeForgetPasswordButton");
    var resetPwdForm = $("#resetPwdForm");
    var loginEmailForm = $("#loginEmailForm");


    resetPwdForm.submit(function(event){
        event.preventDefault();
        let email = $("#forgotPasswordEmail").val();
        let firstName = $("#forgotPasswordFirstName").val();
        let lastName = $("#forgotPasswordLastName").val();
        let errorMsg = $("#resetPwdError");
        let successMsg = $("#resetPwdSuccess");
        let json = {
            "email" : email,
            "firstName" : firstName,
            "lastName" : lastName
        };
        let resetUrl = "/forgotPassword";
        $.ajax({
            url : resetUrl,
            type : "POST",
            async : true,
            data : JSON.stringify(json),
            dataType : 'json',
            contentType: "application/json",
            success : function(data) {
                if (data.status === 1 ) {
                    successMsg.text(data.desc);
                    successMsg.show();
                    errorMsg.hide();
                } else {
                    errorMsg.text(data.desc);
                    errorMsg.show();
                    successMsg.hide();
                }
            }
        });
    });

    loginEmailForm.submit(function (event) {
        event.preventDefault();
        let email = $('#loginEmail').val();
        let password = $('#loginPassword').val();
        let rememberMe = $('#rememberMe').prop("checked");
        let errorMsg = $("#errorMsg");
        let json = {
            "username" : email,
            "password" : password,
            "rememberMe" : rememberMe
        };
        let loginUrl = "/login";
        $.ajax({
            url : loginUrl,
            type : "POST",
            async : true,
            data : JSON.stringify(json),
            dataType : 'json',
            contentType: "application/json",
            success : function(data) {
                if (data.status === 1 ) {
                    errorMsg.hide();
                    if(data.data.userType == 1){
                        window.location.href="/host/hostProfile";
                    }
                    if(data.data.userType == 2){
                        window.location.href="/renter/renterProfile";
                    }
                } else {
                    errorMsg.text(data.desc);
                    errorMsg.show();
                }
            }
        });
    })


// When the user clicks the button, open the popup
    loginBtn.click(
        function(){
            loginPopup.show();
        }
    );

// WHen the user clicks the 'forgot password' button
    forgotPasswordBtn.click(
        function () {
            loginPopup.hide();
            forgotPasswordPopup.show();
        }
    );

// When the user clicks on <span2> (x), close the popup
    closeLoginButton.click(function () {
        loginPopup.hide();
    });


// When the user clicks on <span2> (x), close the popup
    closeForgetPasswordButton.click(function () {
        forgotPasswordPopup.hide();
    })

// When the user clicks anywhere outside of the popup, close it
    $("body").click(function(event) {
        if (event.target === loginPopup.get(0)) {
            loginPopup.hide();
        }else if (event.target == forgotPasswordPopup.get(0)){
            forgotPasswordPopup.hide();
        }
    });



    /*
    For sign up popup windows
     */
    var signupPopup = $("#signupPopup");
// Get the button that opens the popup
    var signupBtn = $("#signupBtn");
// Get the <span1> element that closes the popup
    var closeSignUpButton = $("#closeSignUpButton");
// When the user clicks the button, open the popup
    signupBtn.click(function () {
        signupPopup.show();
    })

// When the user clicks on <span1> (x), close the popup
    closeSignUpButton.click(function () {
        signupPopup.hide();
    })
// When the user clicks anywhere outside of the popup, close it
    $("body").click(function(event) {
        if (event.target === signupPopup.get(0)) {
            signupPopup.hide();
        }
    });


    let signupEmailBtn = $("#emailBtn");
    signupEmailBtn.click(function () {
        let hostSelected = $("#userHost");
        if (hostSelected.prop("checked") == true) {
            window.location.href="/hostRegister";
        }else {
            window.location.href="/renterRegister";
        }
    });

});

