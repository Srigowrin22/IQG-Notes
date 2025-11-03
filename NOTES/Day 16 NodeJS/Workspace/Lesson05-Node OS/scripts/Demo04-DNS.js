// const dns = require('dns');
// dns.lookup('www.google.com', (err, addresses, family) => {
//     console.log('addresses : ' , addresses);
//     console.log('family : ', family);
// });

const dns = require('dns');
dns.lookup('www.iqgateway.com', (err, addresses, family) => {
    console.log('addresses : ' , addresses);
    console.log('family : ', family);
});