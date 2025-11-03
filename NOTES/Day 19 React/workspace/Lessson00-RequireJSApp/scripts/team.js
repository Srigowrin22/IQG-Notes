define(['./coach'], function(coachRef){
    return{
        displayDetails: function(){
            document.write("Coach: " + coachRef.coachName + ", coaches for " + coachRef.team);
        }
    }
});