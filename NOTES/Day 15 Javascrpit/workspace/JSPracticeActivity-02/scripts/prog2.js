function getFormValue(){
    var nameList = document.getElementsByTagName("input");
    for(var i=0; i<nameList.length; i++){
        if(nameList[i].type === "text"){
             alert(nameList[i].value);
        }
    }
}