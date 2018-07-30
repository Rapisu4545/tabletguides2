function addimagetotextarea(object){
    var buttonname = object.getAttribute("name");
    var  pos = buttonname.indexOf(".");
    var sub = buttonname.substr(pos+1, buttonname.length);
    var textarea = document.querySelector("textarea");
    textarea.value = textarea.value+"<div class=\"image-div\"><img class=\"image\" src=\"/files/"+buttonname+"\"><p>"+sub+"</p></div>"+"\r\n";


}

function addnoimagetotextarea(object){
    var buttonname = object.getAttribute("name");
    var textarea = document.querySelector("textarea");
    textarea.value = textarea.value+"<a href=\"/files/"+buttonname+"\">"+buttonname+"</a><br>"+"\r\n";


}