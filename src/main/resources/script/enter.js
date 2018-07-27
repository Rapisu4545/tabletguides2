var textarea = document.querySelector("textarea");
textarea.addEventListener("keydown", function(event) {
    // The key code for F2 happens to be 113
    if (event.keyCode == 13) {
        replaceSelection(textarea, "<br>"+"\r\n");
        event.preventDefault();
    }
});

function replaceSelection(field, word) {
    var from = field.selectionStart, to = field.selectionEnd;
    field.value = field.value.slice(0, from) + word +
        field.value.slice(to);
    // Put the cursor after the word
    field.selectionStart = field.selectionEnd =
        from + word.length;
};