function addimagetotextarea(object){
    var buttonname = object.getAttribute("name");
    var textarea = document.querySelector("textarea");
    textarea.value = textarea.value+"<img class=\"image\" src=\"/files/"+buttonname+"\">"+"\r\n";


}

function addnoimagetotextarea(object){
    var buttonname = object.getAttribute("name");
    var textarea = document.querySelector("textarea");
    textarea.value = textarea.value+"<a href=\"/files/"+buttonname+"\">"+buttonname+"</a><br>"+"\r\n";


}