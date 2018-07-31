var elements = document.querySelectorAll('.head, textarea, .category-input');

function checkValidity() {};

for (i=0; i<elements.length; i++) {
    (function(element) {
        var id = element.getAttribute('id');
        if (!(sessionStorage.getItem(id)===null)&&!(sessionStorage.getItem(id)==="")){
            element.value = sessionStorage.getItem(id);
        }

            element.oninput = function() {

                    sessionStorage.setItem(id, element.value);


                checkValidity();
            };


    })(elements[i]);
}