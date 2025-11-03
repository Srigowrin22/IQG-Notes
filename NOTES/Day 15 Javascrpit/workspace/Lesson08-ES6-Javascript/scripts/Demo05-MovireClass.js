class Movie {

    constructor(givenId, givenTitle, givenRating) {
        this.movId = givenId;
        this.movTitle = givenTitle;
        this.movRating = givenRating;
    }

    displayDetails() {
        console.log("\nMovie Details: ");
        console.log("Movie ID: " + this.movId);
        console.log("Movie name: " + this.movTitle);
        console.log("Movie Rating: " + this.movRating);
    }
}

var movRef1 = new Movie(1001, "Vaaranam Ayiram", "Good");
movRef1.displayDetails();

var movRef2 = new Movie(1002, "Ayirathil Oruvan", "Super");
movRef2.displayDetails();