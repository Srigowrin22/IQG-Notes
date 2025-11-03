function doSomthing(givenVaue){
    value = givenVaue || 'Default value';
    console.log(value);
}

doSomthing();
doSomthing(null);
doSomthing("hello");
doSomthing(true);