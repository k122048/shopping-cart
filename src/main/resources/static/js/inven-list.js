$(document).ready(function () {

    $( "[add-orders-button]" ).on( 'click', function( e ) {
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
                    console.log("order saved");
                }

            })



        });


    // your code
});

