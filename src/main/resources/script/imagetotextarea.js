function addimagetotextarea(object){
    var buttonname = object.getAttribute("name");
    var textarea = document.querySelector("textarea");
    textarea.value = textarea.value+"<img src=\"/img/"+buttonname+"\"><br>"+"\r\n";


}