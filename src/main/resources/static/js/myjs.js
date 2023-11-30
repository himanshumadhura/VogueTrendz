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

    $("#updateProduct").click(function (){
        var pName = $("#productName").val();
        var cat = $("#category").val();
        var price = $("#price").val();
        var img = $("#imageUrl").val();

        if(pName && cat && price && img){
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Product Updated Successfully",
                showConfirmButton: false,
                timer: 1500
            });
        }
    });

    $("#deleteBtn").click(function (){
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: "btn btn-success",
                cancelButton: "btn btn-danger"
            },
            buttonsStyling: false
        });
        swalWithBootstrapButtons.fire({
            title: "Are you sure?",
            text: "You won't be able to revert this!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Yes, delete it!",
            cancelButtonText: "No, cancel!",
        }).then((result) => {
            if (result.isConfirmed) {
                swalWithBootstrapButtons.fire({
                    title: "Deleted!",
                    text: "Your product has been deleted.",
                    icon: "success",
                    showConfirmButton: false,
                    timer: 1500
                });
                window.location.href="/admin/deleteProductProcess/"+$(this).val();
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire({
                    title: "Cancelled",
                    text: "Your product isn't deleted!",
                    icon: "error",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        });
    });

    $("#updateBtn").click(function (){
        window.location.href="/admin/updateProduct/"+$(this).val();
    });

    $("#cancelBtn").click(function(){
        window.location.href="/admin/dashboard";
    });
});