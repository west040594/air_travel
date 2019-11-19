$(document).ready(function() {

    var $addCardItemButtons = $('[role=addCartItem]')
    var $removeCartItemButtons = $('[role=removeCartItem]')
    var $removeAllItemsButtons = $('[role=removeAllCartItems]');
    var $orderCartBody = $('table#bookingCart').find('tbody');

    function refreshCartFields(data) {
        $('.cart-quantity').html(data.itemCount);
        $('.cart-total').html(data.total);

        //Цикл по tr
        $orderCartBody.find('tr').each(function(index) {
            var findFlag = false;
            //Цикл по предметам корзины
            //Если айдишники совпали меняем значения tr, ставим флаг что нашли и выходим из цикла
            for(var i = 0; i < data.itemList.length; i++) {
                console.log($(this).data("seatid") + " " + data.itemList[i].seat.id)
                if($(this).data("seatid") == data.itemList[i].seat.id) {
                   findFlag = true;
                   break;
                }
            }
            //Если совпадения не найдено, то удаляем строку
            if(!findFlag) $(this).remove();
        });
    }

    $addCardItemButtons.each(function() {
        var flightId = $(this).attr('data-flightid')
        var flightSeatId = $(this).attr('data-flightseatid')
        $(this).on('click', function () {
            $.ajax({
                type: "POST",
                url: "/api/v1/booking",
                contentType: "application/json",
                data: JSON.stringify({flightId : flightId, seatId: flightSeatId}),
            }).done(function(data) {
                refreshCartFields.call(this, data);
            })
        });
    })

    $removeCartItemButtons.each(function() {
        var flightSeatId = $(this).attr('data-flightseatid')
        $(this).on('click', function () {
            $.ajax({
                type: "DELETE",
                url: "/api/v1/booking/" + flightSeatId,
                contentType: "application/json",
            }).done(function(data) {
                refreshCartFields.call(this, data);
            })
        });
    })

    $removeAllItemsButtons.each(function() {
        $(this).on('click', function() {
            $.ajax({
                type: "DELETE",
                url: '/api/v1/booking',
                contentType: "application/json; charset=utf-8",
                dataType: "json",

            }).done(function(data) {
                $('.cart-quantity').html(data.itemCount);
                $('.cart-total').html(data.total);
                $orderCartBody.empty();
            })
        })
    })
})

