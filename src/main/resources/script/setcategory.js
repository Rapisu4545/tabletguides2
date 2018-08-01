function setcategory() {
    var options = document.querySelectorAll('.option');
    var hiddenname = document.querySelector('.hiddenopt').getAttribute('name');
    for (var i=0; i< options.length; i++){
        if (options[i].getAttribute('name')===hiddenname){
            options[i].selected = 'selected';
        }
    }
}