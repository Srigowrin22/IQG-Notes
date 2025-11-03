// Variable declarations
var breakfast: string = "Dosa";
var calories: number = 150;
var tasty: boolean = true;
 
// Function to display food details
function details(food: string, energy: number): void {
    console.log("Our " + food + " has " + energy + " calories ");
}
 
// Call the function with variables
details(breakfast, calories);
 