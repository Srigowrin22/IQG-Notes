require(['./team','./coach'], function(teamRef, coachRef){
    alert("Welcome" + coachRef.coachName);
    teamRef.displayDetails();
});