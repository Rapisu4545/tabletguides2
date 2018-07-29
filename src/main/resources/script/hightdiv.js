function init() {
    var elements = document.querySelectorAll('.item');
    for (i=0; i<elements.length; i++) {
        var head = elements[i].children[0].clientHeight;
        if (head>50){
            elements[i].style.height = '100px';
        }

    }
}