function Movie(givenId, givenTitle, givenRating) {
    this.id = givenId;
    this.title = givenTitle;
    this.rating = givenRating;
}
 
Movie.prototype.toString = function() {
    return this.id + "\t" + this.title + "\t" + this.rating;
}
 
var mov1Ref = new Movie(1, "Vikram", "Fantabulous");
console.log(mov1Ref.toString());