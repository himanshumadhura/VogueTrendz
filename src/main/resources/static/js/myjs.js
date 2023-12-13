$(document).ready(function(){

    $("#registerBtn").click(function (){
        let fName = $("#firstName").val();
        let lName = $("#lastName").val();
        let email = $("#email").val();
        let phNumber = $("#phoneNumber").val();
        let pass = $("#password").val();
        let cPass = $("#confirmPassword").val();
        let agree = $("#agreement").val();


        if(fName && lName && email && phNumber && pass && cPass && agree){
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
        let pName = $("#productName").val();
        let cat = $("#category").val();
        let price = $("#price").val();
        let img = $("#imageUrl").val();

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
        let pName = $("#productName").val();
        let cat = $("#category").val();
        let price = $("#price").val();
        let img = $("#imageUrl").val();

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
                window.location.href="/admin/deleteProductProcess/"+$("#productId").val();
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

    $("#cancelBtn").click(function(){
        window.location.href="/admin/dashboard";
    });

    if(parseInt($("#quantity").val()) <= 1){
        $("#decrease-quantity-btn").attr('disabled', true);
    }

    $("#totalPrice").text(parseInt($("#finalPrice").text()) + parseInt($("#shipping").text()));

    $("#increase-quantity-btn").click(function(){
        let price = parseInt($("#summaryPrice").text());
        let quantity = parseInt($("#quantity").val())+1;
        if(quantity > 9){
            $(this).attr('disabled', true);
        }
        $("#quantity").val(quantity);
        $("#decrease-quantity-btn").attr('disabled', false);
        $("#summaryPrice").text(price*quantity);
        $("#finalPrice").text(price*quantity);
        $("#totalPrice").text(parseInt($("#finalPrice").text()) + parseInt($("#shipping").text()));
    });

    $("#decrease-quantity-btn").click(function(){
        let price = parseInt($("#summaryPrice").text());
        let quantity = parseInt($("#quantity").val())-1;
        if(quantity < 2 ){
            $(this).attr('disabled', true);
        }
        $("#quantity").val(quantity);
        $("#increase-quantity-btn").attr('disabled', false);
        $("#summaryPrice").text(price/(quantity+1));
        $("#finalPrice").text(price/(quantity+1));
        $("#totalPrice").text(parseInt($("#finalPrice").text()) + parseInt($("#shipping").text()));
    });


    $("#addToCartBtn").click(function (){
        Swal.fire({
            position: "center",
            icon: "success",
            title: "Item added to cart successfully!!!",
            showConfirmButton: false,
            timer: 1500
        });
        window.location.href="/user/addToCart/"+$("#productId").val();
    });

    $("#addToWishlistBtn").click(function (){
        Swal.fire({
            position: "center",
            icon: "success",
            title: "Item added to wishlist successfully!!!",
            showConfirmButton: false,
            timer: 1500
        });
    });

    $("#addAddress").click(function (){
        Swal.fire({
            position: "center",
            icon: "success",
            title: "Address added successfully!!!",
            showConfirmButton: false,
            timer: 1500
        });
    });

});