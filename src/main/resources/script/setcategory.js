function setcategory() {
    var options = document.querySelectorAll('.option');
    var hiddenname = document.querySelector('.hiddenopt').getAttribute('name');
    console.log(hiddenname);
    for (var i=0; i< options.length; i++){
        console.log(options[i].getAttribute('name'));
        if (options[i].getAttribute('name')===hiddenname){
            options[i].selected = 'selected';
        }
    }
}