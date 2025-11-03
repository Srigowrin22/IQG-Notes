// function showSpeakersBanner() {
//     alert("You have Clicked on section - 1")
// }

// function showShopBanner() {
//     alert("You have Clicked on section - 2")
// }

// document.getElementById("section-2").onclick = showShopBanner;

window.addEventListener("load", function(){
    var section2Elem = this.document.getElementById("section-2");
    section2Elem.addEventListener("click", function(){
        alert("You have clicked on section-2");
    }, false);
});