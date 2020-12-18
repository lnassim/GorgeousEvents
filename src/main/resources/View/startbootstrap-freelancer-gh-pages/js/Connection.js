var init = function() {
    $("#btnConnection").click(() => connection());

};
$('#problem').css('display', 'none');
var connection = function() {
    var identifiant = $('#identifiant').val();
    var motDePasse = $('#motDePasse').val();

    if (identifant.length === 0 || motDePasse.length === 0) {
        $('#problem').css('display', 'block');
        $('#problem').html('Vous n avez pas renseigné tout les champs');
    } else {
        $.ajax({
            url: "http://localhost:8080/user/new",
            type: 'post',
            data: {
                username: identifiant,
                password: motDePasse,
            },
            success : function(resultat) {
                location.href = "accueil.html";
            },
            problem : function() {
                $('#problem').css('display', 'block');
                $('#problem').html('L identifiant ou le mot de passe est incorrect');
            }
        });
    }
};

$(document).ready(function(){
    init();
});
