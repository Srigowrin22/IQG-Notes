function removecolor() {
    var element = document.getElementById("colorSelect");
    var selectedIndex = element.selectedIndex;

    if (selectedIndex >= 0) {
        element.remove(selectedIndex);
        alert("Removed")
    } else {
        alert("Invalid")
    }
}

