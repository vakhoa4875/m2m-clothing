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
                            <span class="text-secondary text-xs font-weight-bold">${order.deliveryAddress}</span>
                        </td>
                        <td class="align-middle text-center text-sm">
                            <span class="text-secondary text-xs font-weight-bold">${order.countSp}</span>
                        </td>
                        <td class="align-middle text-center">
                            <span class="text-secondary text-xs font-weight-bold"><span class="badge text-bg-warning">${order.orderStatus}</span></span>
                        </td>
                        <td class="align-middle text-center">
                            <button class="btn border-0 rounded btn-success btn-click-product"
                                    data-bs-toggle="modal" data-bs-target="#exampleModalAccpectProduct"
                                    onclick="getIdProduct(${order.orderId}, '${order.paymentMethod}')"
                                    ${order.orderStatus === "Need approved" ? '' : 'disabled'}>
                                    <i class="fa-regular fa-circle-check"></i> Approve
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

let updatedOrderPayload = {
    idProduct: undefined,
    OrderStatus: undefined
}
const orderStatus = {
    needPayment: "Need payment",
    delivering: "Delivering",
    approved: "Approved",
    canceled: "Canceled",
    denied: "Denied"
}

function getIdProduct(id, paymentMethod){
    this.updatedOrderPayload = {
        idProduct: id,
        OrderStatus: paymentMethod === 'Cod' ? orderStatus.delivering : orderStatus.needPayment
    }
}

function updateStatus(){
    $.ajax({
        url: "/api-product/updateOrderUser",
        type: 'Get',
        data: this.updatedOrderPayload,
        contentType: 'application/json',
        success: function(data) {
            console.log(updatedOrderPayload);
            Swal.fire({
                title: 'Order approved successfully!',
                icon: 'success', // Có thể thay đổi icon thành 'error', 'warning', 'info', hoặc 'question'
                confirmButtonText: 'OK',
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