function maxi1( elm3 ){
    var elmtemp = elm3.parentElement;
    var elm = elmtemp.parentElement;
    if ( elm.enlarged) {

        elm.style.height='70px';
        init2(elm);
        elm.enlarged = false;
    } else {
        elm.style.height='100%';
        elm.enlarged = true;
    }
}

function init2(elm2) {
    var head = elm2.children[0].clientHeight;
        if (head>50){
            elm2.style.height = '100px';
        }
}