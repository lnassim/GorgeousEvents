$(document).ready(function(){
    $('#btnSignup').click(() => signup());
});

var signup=function(){
    var userN = $('#inputLogin').val();
    var passwrd = $('#inputPassword').val();
    var eMail = $('#inputMail').val();
    var confirmPasswrd = $('#inputConfirmPassword').val();

    $.ajax({
        url:"http://localhost:8080/inscription",
        type:"POST",
        data:{userName:userN, password:passwrd, email:eMail,confirmPassword:confirmPasswrd},
        success:function(resultat){
            location.href="index.html"
        }
    })

}