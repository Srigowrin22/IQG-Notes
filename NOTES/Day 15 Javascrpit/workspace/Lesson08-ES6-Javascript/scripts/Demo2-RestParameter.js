function doAdd(...args) {
    let sum = 0;
    for (let i of args) {
        sum += i;
    }
    console.log("sum is: " + sum);
}

function doAdd(...args) {
    let sum = 0;
    for (let i of args) {
        sum += i;
    }
    console.log("Hello Sumss is: " + sum);
}

doAdd(10, 20, 30, 40, 50, 60, 70);
doAdd("Hello");
doAdd(100);