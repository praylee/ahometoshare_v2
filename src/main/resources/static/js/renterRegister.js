function checkPassword(str)
        {
          var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
          return re.test(str);
        }

        function checkForm(form)
        { 
          if(form.pwd1.value !== "" && form.pwd1.value === form.pwd2.value) {
            if(!checkPassword(form.pwd1.value)) {
              alert("The password you have entered is not valid!\n \n\
Pleae enter password at least one number, one lowercase and one uppercase letter\n\n\
The password at least six characters that are letters, numbers or the underscore");
              form.pwd1.focus();
              return false;
            }
          } else {
            alert("Error: Please make sure you enter matched password!");
            form.pwd2.focus();
            return false;
          }
          return true;
        }