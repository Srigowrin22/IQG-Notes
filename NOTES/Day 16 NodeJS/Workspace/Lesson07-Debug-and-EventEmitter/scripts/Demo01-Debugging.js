var fs = require('fs');
fs.readFile('Data.txt', 'utf8', function(err, data){
    debugger;
    if(err) throw err;
    console.log(data);
})

// node inspect Demo01-Debugging.js 


