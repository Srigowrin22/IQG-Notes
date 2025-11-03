function sortThreeNum(a, b, c) {
    let nums = [a, b, c];
    nums.sort(function (x, y) { return x - y; });
    alert('Sorted numbers: ' + nums.join(', '));
}

document.getElementById('sortForm').onsubmit = function (event) {
    event.preventDefault(); 
    const a = parseFloat(document.getElementById('num1').value);
    const b = parseFloat(document.getElementById('num2').value);
    const c = parseFloat(document.getElementById('num3').value);

    if (isNaN(a) || isNaN(b) || isNaN(c)) {
        alert('Please enter valid numbers in all fields.');
        return;
    }

    sortThreeNum(a, b, c);
};