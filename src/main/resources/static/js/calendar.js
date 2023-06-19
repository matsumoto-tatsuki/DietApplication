function changeImage() {
    var image = document.getElementById("myImage");
    if (image.src.endsWith("/images/failure.png")) {
      image.src = "/images/success.png";
    } else {
      image.src = "/images/failure.png";
    }
}