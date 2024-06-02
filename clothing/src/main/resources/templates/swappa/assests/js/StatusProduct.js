$('#v-pills-home-status-product').click(function () {
    loadStatusProduct()
});

$('#updateStatusProduct').click(function () {
    updateStatus()
});


function loadStatusProduct(){

    $.ajax({
        url: '/api-product/ordersAllProctStatus',
        type: 'GET',
        success: function(data) {
            $('#TableStatusUser').empty(); // xóa các phần tử cũ đi để gọi lại phần tử mới
            // Hiển thị dữ liệu trong bảng
            data.forEach(function(order) {
                var orderDateFormatted = new Date(order.orderDate);
                var formattedDate = `${orderDateFormatted.getDate()}/${orderDateFormatted.getMonth() + 1}/${orderDateFormatted.getFullYear()}`;
                if(order.orderStatus === "Approved"){
                    return;
                }
                $('#TableStatusUser').append(`
                    <tr>
                                        <td class="align-middle text-center text-sm">
                                            <div class="d-flex w-100 flex-column justify-content-center">
                                                <h6 class="mb-0 text-sm" id="${order.orderId}">${order.orderId}</h6>
                                            </div>
                                        </td>
                                        <td class="align-middle text-center text-sm">
                                            <span class="text-secondary text-xs font-weight-bold">${order.username}</span>
                                        </td>
                                        <td class="align-middle text-center text-sm">
                                            <span class="text-secondary text-xs font-weight-bold">${order.phoneNumber}</span>
                                        </td>
                                        <td class="align-middle text-center text-sm">
                                            <span class="text-secondary text-xs font-weight-bold"><span class="badge text-bg-secondary">${order.deliveryAddress}</span></span>
                                        </td>
                                        <td class="align-middle text-center text-sm">
                                            <span class="text-secondary text-xs font-weight-bold">${order.countSp}</span>
                                        </td>
                                        <td class="align-middle text-center">
                                            <span class="text-secondary text-xs font-weight-bold"><span class="badge text-bg-warning">${order.orderStatus}</span></span>
                                        </td>
                                        <td class="align-middle text-center">
                                            <button class="btn border-0 rounded btn-outline-info btn-click-product"
                                                    data-bs-toggle="modal" data-bs-target="#exampleModalAccpectProduct"
                                                    onclick="getidProduct(${order.orderId})">
                                                    <i class="fa-regular fa-circle-check"></i>
                                            </button>
                                        </td>
                                    </tr>
                `);
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error:', errorThrown);
        }
    });


}

var idprocudt = '';

function getidProduct(id){
    $('#idProduct').text(id);
    idprocudt = id;
}

function updateStatus(){
    $.ajax({
        url: "/api-product/updateOderUser?idProduct="+idprocudt+"&OrderStatus="+"Approved",
        type: 'Get',
        contentType: 'application/json',
        success: function(data) {
            Swal.fire({
                title: 'Thông Báo từ hệ thống',
                text: 'Xác nhận đơn hàng thành công',
                icon: 'success', // Có thể thay đổi icon thành 'error', 'warning', 'info', hoặc 'question'
                confirmButtonText: 'Xác nhận',
                allowOutsideClick: false,
            }).then((result) => {
                location.reload();
            })
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error:', errorThrown);
        }
    });
}