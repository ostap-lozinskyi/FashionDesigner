$(document).ready(function () {
    $('.carousel-control-prev').click(function () {
        var element = document.getElementsByClassName('photoIDmini');
        var i;

        for (i = 0; i < element.length; i++) {
            if (element[i].classList.contains("active")) {
                element[i].classList.remove("active");
                if (element[i - 1]) {
                    element[i - 1].classList.add("active");
                } else {
                    element[element.length - 1].classList.add("active");
                }
                break;
            }
        }
    });
    $('.carousel-control-next').click(function () {
        var element = document.getElementsByClassName('photoIDmini');
        var i;

        for (i = 0; i < element.length; i++) {
            if (element[i].classList.contains("active")) {
                element[i].classList.remove("active");
                if (element[i + 1]) {
                    element[i + 1].classList.add("active");
                } else {
                    element[0].classList.add("active");
                }
                break;
            }
        }
    });
    $('.photoIDmini').click(function () {
        var element = document.getElementsByClassName('active');
        element[1].classList.remove("active");

        $(this).addClass('active');
    });
    $('.photoID').click(function () {
        var elementActive = document.getElementsByClassName('active');
        var adress = elementActive[1].getAttribute('src');

        var elementModal = document.getElementsByClassName('photoIDmodal');
        elementModal[0].src = adress;

        var modalDialog = document.getElementsByClassName('modal-dialog');

        var screenHeight = screen.height;
        var windowWidth = window.innerWidth;
        if (window.innerHeight > window.innerWidth) {
            if (elementModal[0].width < elementModal[0].height) {
                modalDialog[0].style.width = windowWidth * 0.95 + "px";
            } else {
                modalDialog[0].style.width = screenHeight / 1.8 + "px";
            }
        } else {
            if (elementModal[0].width < elementModal[0].height) {
                modalDialog[0].style.width = screenHeight / 1.75 + "px";
            } else {
                modalDialog[0].style.width = windowWidth * 0.7 + "px";
            }
        }

    });
    $('.carousel').carousel({
        interval: false
    })
});