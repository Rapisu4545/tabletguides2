function filter(element) {
    var items = document.querySelectorAll(".item");
    for (var j=0;j<items.length; j++){
        items[j].classList.remove('filter');
    }
var filtername = element.getAttribute('id');
    if (filtername!='radioall'){
        var filterid = filtername.replace("radio-", "");

        for (var i=0;i<items.length; i++){
            var itemid = items[i].getAttribute('name').replace("item-","");
            if (itemid!=filterid){
                items[i].classList.add('filter');
            }
        }

    }
    else{
        for (var k=0;k<items.length; k++){
            items[k].classList.remove('filter');
        }
    }

}