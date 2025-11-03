var sum = function (a, b) {
    return a + b;
}

var sub = function (a, b) {
    return a - b;
}

function invoke(fn) {
    return fn(23, 26);
}

var result = invoke(sum);
console.log(result);

var result1 = invoke(sub);
console.log(result1);