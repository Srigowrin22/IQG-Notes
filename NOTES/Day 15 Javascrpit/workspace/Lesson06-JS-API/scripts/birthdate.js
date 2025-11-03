window.addEventListener("load", function () {
    var button = document.getElementById("birthdateButton");
    button.addEventListener("click", function () {
        // Got Hold of the UI Elements
        var day = document.getElementById("dayElement");
        var month = document.getElementById("monthElement");
        var result = document.getElementById("result");

        // Conversion
        var dayVal = parseInt(day.value);
        var monthVal = parseInt(month.value);

        // Get current date
        var currentDate = new Date();

        var currmonth = currentDate.getMonth() + 1;
        var currdate = currentDate.getDate();
        var curryear = currdate.getFullYear();

        if (monthVal < currmonth) {
            if (dayVal < currdate) {
                console.log("Your birday is yet to come!");
            }
        } else if (monthVal == currdate) {
            if (dayVal < currdate) {
                console.log("Your birday is yet to come!");
            } else if (dayVal == currdate) {
                console.log("Happy birday!!!");
            }
        } else {
            console.log("Net birday is in: " + curryear + 1);
        }
    });
});