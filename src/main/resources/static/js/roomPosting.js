/*
 * Create: Oct. 23,2018
 * Author: Bin Yang, Zhan Shen
 * Modified By: Oroi Wang - 2019W
 * Clients: Michelle Bilek, Farheen Khan 
 * Course: CST8334 - Software Development Project - 2018F
 * Professor: Dr. Anu Thomas
 * Project: A Home to Share
 * Copyright @ 2018
 * 
 * */

var fileIds = ["inputfile","inputfile2" ,"inputfile3","inputfile4","inputfile5","inputfile6"];
var Imgs = ['#img','#img2','#img3','#img4','#img5','#img6'];
var Files = ['#inputfile','#inputfile2','#inputfile3','#inputfile4','#inputfile5','#inputfile6'];

var customBtn = document.getElementById("custom-button");
customBtn.addEventListener("click",function() {
    readURL(fileIds[0],Imgs[0],Files[0]);
});
var customBtn2 = document.getElementById("custom-button2");
customBtn2.addEventListener("click",function(){
    readURL(fileIds[1],Imgs[1],Files[1]);
});

var customBtn3 = document.getElementById("custom-button3");
customBtn3.addEventListener("click",function(){
    readURL(fileIds[2],Imgs[2],Files[2]);
});

var customBtn4 = document.getElementById("custom-button4");
customBtn4.addEventListener("click",function(){
    readURL(fileIds[3],Imgs[3],Files[3]);
});
var customBtn5 = document.getElementById("custom-button5");
customBtn5.addEventListener("click",function(){
    readURL(fileIds[4],Imgs[4],Files[4]);
});

var customBtn6 = document.getElementById("custom-button6");
customBtn6.addEventListener("click",function(){
    readURL(fileIds[5],Imgs[5],Files[5]);
});

function readURL(fileId,img,file) {
    realFileBtn = document.getElementById(fileId);
    realFileBtn.click();
    realFileBtn.addEventListener("change",function(){
        if (this.files && this.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $(img)
                    .attr('src', e.target.result);
            };
            reader.readAsDataURL(this.files[0]);
        }
        var image_name = $(file).val();
        if(image_name != ''){
            var extension = $(file).val().split('.').pop().toLowerCase();
            if($.inArray(extension,['gif','png','jpg','jpeg']) == -1){
                alert('Invalid Image File, only accept gif, png, jpg and jpeg');
                $(file).val('');
                return false;
            }
            if (typeof FileReader !== "undefined") {
                let size = $(file).get(0).files[0].size;
                if (size/1024>1024){
                    alert("File Size cannot exceed 1M");
                    $(file).val('');
                    return false;
                }
            }
        }
    });
}

function checkDate(){
    var sdate = new Date(document.getElementById("startdate").value);
    var edate = new Date(document.getElementById("enddate").value);
    if (edate > sdate){
        return true;
    }else {
        alert("Your ending date for sharing this property should not be equal or before the starting date. Please correct it and input the date again.");
        return false;
    }
}