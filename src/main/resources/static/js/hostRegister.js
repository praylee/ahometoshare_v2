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
 */

function checkPassword(str) {
    var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
    return re.test(str);
}

function checkForm(form) {
    if (form.pwd1.value !== "" && form.pwd1.value === form.password.value) {
        if (!checkPassword(form.pwd1.value)) {
            alert("The password you have entered is not valid!\n \n\
Pleae enter password at least one number, one lowercase and one uppercase letter\n\n\
The password at least six characters that are letters, numbers or the underscore");
            form.pwd1.focus();
            return false;
        }
    } else {
        alert("Error: Please make sure you enter matched password!");
        form.password.focus();
        return false;
    }
    return true;
}