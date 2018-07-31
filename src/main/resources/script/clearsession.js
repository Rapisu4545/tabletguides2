document.getElementById('submit-save').onclick = function() {
    console.log(sessionStorage.getItem('main-text'));
    sessionStorage.clear();
    console.log(sessionStorage.getItem('main-text'));
}

