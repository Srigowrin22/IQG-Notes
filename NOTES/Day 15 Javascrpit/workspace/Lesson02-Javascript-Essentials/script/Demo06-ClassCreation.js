// Creating class using Constructor Look and Feel

function displayDetails(){
    console.log("ID: " + Movie.id);
    console.log("Title: " + this.title);
    console.log("Rating: " + this.rating);
}
function Movie(givenId, givenTitle, givenRating){
    this.id = givenId;
    this.title = givenTitle;
    this.rating = givenRating;
    this.display = displayDetails; // Function within an handle
}

// Movie(10, "Ghajini", "9.2"); // Treated as function without link and variables are treated as instance variables
// displayDetails();

// var mov1 = new Movie(20, "Ghilli", "9.3"); // Treated as Objects and so variables are not linked and treated as class variables
// displayDetails(); 

var mov2 = new Movie(30, "24", "9.3");
mov2.display();