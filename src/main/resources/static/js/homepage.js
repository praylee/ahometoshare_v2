/**
 * for sign in button
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


// When the user clicks the button, open the popup
    loginBtn.click(
        function(){
            loginPopup.show();
            forgotPasswordBtn.hide();
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

// added by Christopher Labelle
// if(<%=request.getAttribute("isLoginValid")%> === false) {
    if(true){
        loginPopup.show()
    }

// added by Christopher Labelle
// if(<%=request.getAttribute("isForgotPasswordEmailValid")%> === false) {
    if(true){
        forgotPasswordPopup.show();
    }

// added by Christopher Labelle
// if(<%=request.getAttribute("isForgotPasswordEmailValid")%> !== null) {
    if(true){
        // if(<%=request.getAttribute("isForgotPasswordEmailValid")%> === true) {
        if(true){
            alert("Password has been reset and been sent to your accoutn");
        }

    }

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
        if (hostSelected.prop('checked', true)) {
            window.location.href="hostRegister";
        }else {
            window.location.href="renterRegister";
        }
    })

});
