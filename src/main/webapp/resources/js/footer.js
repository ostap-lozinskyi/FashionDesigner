window.onload = function () {
    var windowSize = window.innerHeight;
    var bodySize = document.body.clientHeight;
    var element = document.getElementsByClassName('footer');
    
    if (windowSize > bodySize) {
        element[0].style.position = 'absolute';        
        element[0].style.bottom = '0';
    }
};