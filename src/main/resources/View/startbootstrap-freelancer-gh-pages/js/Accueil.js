StarOutUrl=		'StarOut.png';		//image par défaut
StarOverUrl=	'StarOver.png';		//image d'une étoile sélectionnée
StarBaseId=		'Etoile';				//id de base des étoiles
NbStar=			5;					//nombre d'étoiles

LgtStarBaseId=StarBaseId.lastIndexOf('');

function NotationSystem() {
    for (i=1;i<NbStar+1;i++) {
        var img			=document.getElementById('Etoile'+i);

        img.onclick		=function() {alert('Merci de votre participation, votre note est : '+extractionNote(this.id)+'.');};
        //Réaction lors du clic sur une étoile
        //Evidemment, il faudrait compléter cette fonction pour la rendre vraiment utile.
        //Par exemple, envoyer la note dans une base de donnée via un XMLHttpRequest.

        img.alt			='Donner la note de '+i;
        //Texte au survol

        img.src			=StarOutUrl;
        img.onmouseover	=function() {StarOver(this.id);};
        img.onmouseout	=function() {StarOut(this.id);};
    }
}

function StarOver(Etoile) {
    StarNb=extractionNote(Etoile);
    for (i=1;i<(StarNb*1)+1;i++) {
        document.getElementById('Etoile'+i).src=StarOverUrl;
    }
}

function StarOut(Etoile) {
    StarNb=extractionNote(Etoile);
    for (i=1;i<(StarNb*1)+1;i++) {
        document.getElementById('Etoile'+i).src=StarOutUrl;
    }
}

function extractionNote(Etoile) {
    //LeFonction qui extrait le numéro à partir de l'Id
    StarNb=Etoile.slice(LgtStarBaseId);
    return(StarNb);
}
