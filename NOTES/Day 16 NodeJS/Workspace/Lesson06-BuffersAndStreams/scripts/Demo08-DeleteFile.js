var fs = require('fs');

fs.unlink('Data.txt', function(){
    console.log("Delete operation performed");
})