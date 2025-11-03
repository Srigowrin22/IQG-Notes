// 5. Write a Node Snippet to
// a. Get the hostname of Given URL
// b. Get the pathname given in the URL
// c. Get the PortNo and protocol of the URL
// d. Convert the Given List of URL’s into JSON

const { URL } = require('url');

// Example URL
const exampleUrl = "https://example.com:8080/path/name?query=123";

// a. Get the hostname of given URL
const urlObj = new URL(exampleUrl);
console.log("Hostname:", urlObj.hostname); // example.com

// b. Get the pathname given in the URL
console.log("Pathname:", urlObj.pathname); // /path/name

// c. Get the PortNo and protocol of the URL
console.log("Port:", urlObj.port); // 8080
console.log("Protocol:", urlObj.protocol); // https:

// d. Convert the given list of URLs into JSON
const urlList = [
  "https://example.com:8080/path/name?query=123",
  "http://anotherdomain.org:3000/another/path",
  "https://yetanother.net"
];

const urlJson = urlList.map(u => {
  const uObj = new URL(u);
  return {
    hostname: uObj.hostname,
    pathname: uObj.pathname,
    port: uObj.port,
    protocol: uObj.protocol
  };
});

console.log("URLs as JSON:", JSON.stringify(urlJson, null, 2));
