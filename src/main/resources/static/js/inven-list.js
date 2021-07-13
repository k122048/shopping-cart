$(document).ready(function () {

    $( "[add-orders-button]" ).on( 'click', function( e ) {
            $('#msg').removeClass("alert-danger");
            $('#msg').removeClass("alert-success");
            let $this = $( this ),
                inventoryId = $this.attr( 'data-inventory-id' );
             let quantity = $this.closest('.card').find("input[data-quantity]");

            let inventoryObj = {};

            inventoryObj.inventoryId = inventoryId;
            inventoryObj.quantity = quantity.val();

            $.ajax({
                url: "/rest/shopping-cart",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify( inventoryObj ),
                success: function (data) {
                    $('#msg').text(data);
                    if ( data.includes("Error") ){

                        $('#msg').addClass("alert-danger").show();
                    }else{
                        $('#msg').addClass("alert-success").show();
                    }
                }

            })



        });


    // your code
});

