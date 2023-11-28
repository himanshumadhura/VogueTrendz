$(document).ready(function(){
    $("#registerBtn").click(function (){
        var fName = $("#firstName").val();
        var lName = $("#lastName").val();
        var email = $("#email").val();
        var phNumber = $("#phoneNumber").val();
        var pass = $("#password").val();
        var cPass = $("#confirmPassword").val();

        if(fName && lName && email && phNumber && pass && cPass){
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Register Successfully",
                showConfirmButton: false,
                timer: 1500
            });
        }
    });

    $("#addProduct").click(function (){
        var pName = $("#productName").val();
        var cat = $("#category").val();
        var price = $("#price").val();
        var img = $("#imageUrl").val();

        if(pName && cat && price && img){
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Product Added Successfully",
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
});