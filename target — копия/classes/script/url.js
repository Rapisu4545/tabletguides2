function replaceSelectedText(elem, str){
    elem.focus();

    if (document.selection){
        var s = document.selection.createRange();
        if (s.text){
            eval("s.text="+str+"(s.text);");
            s.select();
            return true;
        }
    }
    else if (typeof(elem.selectionStart) == "number"){
        if (elem.selectionStart!=elem.selectionEnd){
            var start = elem.selectionStart;
            var end = elem.selectionEnd;

            eval("var rs = "+str+"(elem.value.substr(start,end-start));");
            elem.value = elem.value.substr(0,start)+rs+elem.value.substr(end);
            elem.setSelectionRange(end,end);
        }
        return true;
    }
    return false;
}

//оборачиваем текст в нужные теги и т.д.
function change_str(s){return "<a href=\""+s+"\">"+s+"</a>"}

// по клику на кнопку, выделенный текст будет заменен на текст с тегами
document.getElementById('url').onclick = function() {
    replaceSelectedText(document.getElementById('main-text'), 'change_str');
    var line = document.getElementById('main-text');
    line.focus();
    line.setSelectionRange(line.value.length,line.value.length);
}