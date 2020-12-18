$(document).ready(function(){
    $('#btnInscription').click(() => inscription());
});

var inscription=function(){
    var userN = $('#insererUser').val();
    var passwrd = $('#insererMotDePasse').val();
    var eMail = $('#insererMail').val();
    var confirmPasswrd = $('#ConfirmerMotDePasse').val();

    $('#problem').css('display', 'none');
    if (userN.length === 0 || sMail.length === 0 || sPassword.length === 0 || sConfirmPassword.length === 0) {
        $('#problem').css('display', 'block');
        $('#problem').html('Veuillez renseigner tous les champs');
    } else if (passwrd !== confirmPasswrd){
        $('#problem').css('display', 'block');
        $('#problem').html('Votre confirmation de mot de passe n est pas identique a la premiere saisie');
    } else if (!eMail.includes('@')){
        $('#problem').css('display', 'block');
        $('#problem').html('Votre email n est pas correct');
    }
    else {
     $.ajax({
        url:"http://localhost:8080/inscription",
        type:"POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data:{userName:userN, password:passwrd, email:eMail,confirmPassword:confirmPasswrd},
        success:function(resultat){
            location.href="Connection.html"
        },
        proble : function() {
            $('#problem').css('display', 'block');
            $('#problem').html('L utilisateur existe deja');
        }
    })

}}
