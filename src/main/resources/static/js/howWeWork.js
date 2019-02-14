// Get the popup
                    var loginPopup = document.getElementById("loginPopup");
                    // Get the button that opens the popup
                    var loginBtn = document.getElementById("loginBtn");
                    // Get the <span2> element that closes the popup
                    var span2 = document.getElementsByClassName("close2")[0];
                    // When the user clicks the button, open the popup 
                    loginBtn.onclick = function() {
                        loginPopup.style.display = "block";
                    };
                    // When the user clicks on <span2> (x), close the popup
                    span2.onclick = function() {
                        loginPopup.style.display = "none";
                    };
                    // When the user clicks anywhere outside of the popup, close it
                    window.onclick = function(event) {
                        if (event.target === loginPopup) {
                            loginPopup.style.display = "none";
                            
                        }
                    };
                    
                    if(false) {
                        loginPopup.style.display = "block";
                    }
                    
                    