function marks(marks, exam){
    if(marks>=89 && marks <= 100){
        if(exam === "Final-exam"){
            return true;
        }else if(marks >= 90){
            return true;
        }
    }
    return false;
}

result = marks(90,"Quaterly-exam");
console.log(result);

result = marks(95,"Final-exam");
console.log(result);

result = marks(80,"Final-exam");
console.log(result);