window.addEventListener("load" , function(){
    var button = document.getElementById("tipButton");
    button.addEventListener("click", function(){
        // Got Hold of the UI Elements
        var tipTotal = document.getElementById("tipTotalBill");
        var number = document.getElementById("tipNumberOfPeople");
        var tipTotalOutput = document.getElementById("tipOutput");
        
        // Conversion
        var tipTotalValue = parseInt(tipTotal.value, 10);
        var numValue = parseInt(number.value, 10);

        var tipBill = (tipTotalValue + (tipTotalValue * 0.1)) / numValue;

        if (isNaN(tipBill) || !isFinite(tipBill) || numValue <= 0) {
            tipTotalOutput.innerHTML = "Please enter valid numbers";
        } else {
            tipTotalOutput.innerHTML = tipBill.toFixed(2);
        }         
    });
});