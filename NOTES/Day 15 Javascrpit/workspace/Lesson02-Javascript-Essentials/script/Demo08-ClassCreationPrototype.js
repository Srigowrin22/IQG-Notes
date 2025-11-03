// Creating class using Constructor Look and Feel with prototype

function Movie(givenId, givenTitle, givenRating) {
    this.id = givenId;
    this.title = givenTitle;
    this.rating = givenRating;
}

Movie.prototype.display = function () {
    console.log("ID: " + Movie.id);
    console.log("Title: " + this.title);
    console.log("Rating: " + this.rating);
}

var mov2 = new Movie(30, "Vaaranam Ayiram", "9.7");
mov2.display();