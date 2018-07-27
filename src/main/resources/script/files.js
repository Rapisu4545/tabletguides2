var control = document.getElementById("file");
control.addEventListener("change", function(event) {
    // Когда происходит изменение элементов управления, значит появились новые файлы
    var i = 0,
        files = control.files,
        len = files.length;



    for (; i < len; i++) {

        var filename = files[i].name;
        var reader = new FileReader();

        reader.addEventListener("load", function(event) {
            var list = document.querySelector('.viewfiles');
            var card = document.createElement('div');
            card.classList.add('fileelement');
            list.appendChild(card);
            var imageelement = document.createElement('div');
            imageelement.classList.add('imageelement');
            card.appendChild(imageelement);
            var img = document.createElement('img');
            img.src = event.target.result;
            img.setAttribute('alt', 'image');
            imageelement.appendChild(img);
            var imgbutton = document.createElement('input');
            imgbutton.type = 'button';
            imgbutton.classList.add('button-for-textarea');
            imgbutton.classList.add('imgbutton');
            card.appendChild(imgbutton);
        });


        reader.readAsDataURL(files[i]);
    }

}, false);