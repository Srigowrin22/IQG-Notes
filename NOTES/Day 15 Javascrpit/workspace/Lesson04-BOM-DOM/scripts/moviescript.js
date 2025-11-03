// var target = document.getElementById("movie-details");
// alert(target.nodeName);

// var listItems = document.getElementsByTagName("li");
// for(var i = 0; i< listItems.length; i++){
//     alert(listItems[i].nodeName + "\t" + listItems[i].textContent);
// }

// var vikram = document.getElementById("vikram-2022");
// var articleVikram = vikram.parentNode;
// alert(articleVikram.nodeName);

// var vikram = document.getElementById("artist-list");
// var articleVikram = vikram.childNodes[2];
// alert(articleVikram);

// -----------Reading Attributes----------
// var ele = document.getElementById("vikram-2022");
// alert(ele.getAttribute("href"));

var newElement = document.createElement("h1");
var content = "Heyaa this is my createELement";
var newTxt = document.createTextNode(content);
newElement.appendChild(newTxt);

var parent = document.getElementById("movie-details");
parent.appendChild(newElement);

