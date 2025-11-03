class Movie {
    constructor(givenId, givenTitle, givenRating) {
        this.movId = givenId;
        this.moveTile = givenTitle;
        this.movrating = givenRating;
    }

    displayDetails() {
        return `Movie Details [Id: ${this.movId}, Title: ${this.moveTile}, Rating: ${this.movrating}]`
    }
}

export { Movie };