$(document).ready(function () {
    function loadAllSale(){
        $.ajax({
            url: '/admin/api/sales/getAllSale',
            type: 'GET',
            success: function (data) {
                $('#SaleProductTableBody').empty();
                $.each(data,function (index,sale){
                    var formattedStartDate = moment(sale.slateStart).format('DD-MM-YYYY');
                    var formattedEndDate = moment(sale.saleEnd).format('DD-MM-YYYY');
                    var newRow = `
                            <tr>
                                        <td class="align-middle text-center">
                                            <h6 class="text-sm mb-0">${sale.saleId}</h6>
                                        </td>
                                        <td class="align-middle text-center text-sm">
                                            <div class="d-flex w-100 flex-column justify-content-center">
                                                <div class="mb-0 text-sm">${sale.saleName}</div>
                                            </div>
                                        </td>
                                        <td class="align-middle text-center">
                                            <span class="text-secondary text-xs font-weight-bold">${sale.salePercent}</span>
                                        </td>
                                        <td class="align-middle text-center">
                                            <span class="text-secondary text-xs font-weight-bold">${formattedStartDate}</span>
                                        </td>
                                        <td class="align-middle text-center">
                                            <span class="text-secondary text-xs font-weight-bold">${formattedEndDate}</span>
                                        </td>
                                        <td class="align-middle text-center d-flexa">
                                            <button class="btn border-0 rounded btn-outline-info"
                                                    data-bs-toggle="modal" data-bs-target="#exampleModalUpSaleProduct" onclick="getSaleFromID(${sale.saleId})">
                                                <i class="fa-solid fa-pen-to-square"></i></button>
                                            <button class="border border-0 rounded btn-outline-danger"
                                                    data-bs-toggle="modal" data-bs-target="#exampleModalDeleteSaleProduct">
                                                <i class="fa-solid fa-trash" onclick="deleteSale(${sale.saleId})"></i></button>
                                        </td>
                                    </tr>
                            `;
                    $('#SaleProductTableBody').append(newRow);
                });

            },
            error: function (xhr, status, error) {
                console.error("Đã xảy ra lỗi khi gọi API: " + error);
            }
        });
    }
    $('#v-pills-home-status-productSale').click(function () {
        loadAllSale();
    });
})

function loadAllProduct(){
    $.ajax({
        url: '/allproductapi',
        type: 'GET',
        success: function (data) {
            $('#ProductTableBody').empty();
            $.each(data,function (index,procduct){
                var newRow = `
                            <tr>
                                        <td class="align-middle text-center">
                                            <h6 class="text-sm mb-0">${procduct.productId}</h6>
                                        </td>
                                        <td class="align-middle text-center text-sm">
                                            <div class="d-flex w-100 flex-column justify-content-center">
                                                <div class="mb-0 text-sm">${procduct.name}</div>
                                            </div>
                                        </td>
                                        <td class="align-middle text-center">
                                            <span class="text-secondary text-xs font-weight-bold">${procduct.price}</span>
                                        </td>
                                        <td class="align-middle text-center">
                                            <span class="text-secondary text-xs font-weight-bold">${procduct.category.category_name}</span>
                                        </td>
                                        <td class="align-middle text-center">
                                            <span class="text-secondary text-xs font-weight-bold">${procduct.sale != null ? procduct.sale.saleName : 'chưa có'}</span>
                                        </td>
                                        <td class="align-middle text-center ">
                                            <button class="btn border-0 rounded btn-outline-info"
                                                    data-bs-toggle="modal" data-bs-target="#exampleModalUpSaleProductFormSale" onclick="getSaleProduct(${procduct.productId})">
                                                <i class="fa-solid fa-pen-to-square"></i></button>
                                        </td>
                                    </tr>
                            `;
                $('#ProductTableBody').append(newRow);
            });
            // Áp dụng CSS mới cho phần tử tbody
            tableBody.css({
                'display': 'block',
                'max-height': '300px',
                'overflow-y': 'auto'
            });
            tableBody.find('tr').css({
                'width': '100%',
                'display': 'table',
                'table-layout': 'fixed'
            });

        },
        error: function (xhr, status, error) {
            console.error("Đã xảy ra lỗi khi gọi API: " + error);
        }
    });
}

$('#product-tab').click(function () {
    loadAllProduct();
});


function getSaleFromID(id){
    $.ajax({
        url: '/admin/api/sales/'+id,
        type: 'GET',
        success: function (data) {
            $("#upid").val(data.saleId);
            $("#upSalename").val(data.saleName);
            $("#upSalePercent").val(data.salePercent);
            $("#upStartDate").val(data.slateStart);
            $("#upEndDate").val(data.saleEnd);
        },
        error: function (xhr, status, error) {
            console.error("Đã xảy ra lỗi khi gọi API: " + error);
        }
    });
}


//cái này là của thẻ tab vui lòng không đụng chạm tránh lỗi
$('#myTab a[data-bs-toggle="tab"]').on('show.bs.tab', function(e) {
    let target = $(e.target).data('bs-target')
    $(target)
        .addClass('active show')
        .siblings('.tab-pane.active')
        .removeClass('active show')
})

// lưu sale cho product đó
function saveSaleProduct(id){
    var selectedSaleId = $('#productSale').val();

    $.ajax({
        url: "/admin/api/sales/updateProducctFromSale?sale_ID=" + selectedSaleId + "&product_id=" + id,
        type: 'POST',
        success: function (data) {
            Swal.fire({
                title: 'Thông Báo từ hệ thống',
                text: "Cập nhật thành công sale",
                icon: 'success',
                confirmButtonText: 'Xác nhận',
                allowOutsideClick: false,
            }).then((result) => {
                location.reload();
            })
        },
        error: function (xhr, status, error) {
            console.error("Đã xảy ra lỗi khi gọi API: " + error);
        }
    });

}



//get sale cho bảng product
function getSaleProduct(id){
    $.ajax({
        url: "/admin/api/sales/getAllSale",
        type: 'GET',
        success: function (data) {
            $('#productSale').empty();
            $.each(data,function (index,data){
                var newRow = `
                            <option value="${data.saleId}">${data.saleName} - salePercent( ${data.salePercent}% )</option> 
                `;
                $('#productSale').append(newRow);
            });
        },
        error: function (xhr, status, error) {
            console.error("Đã xảy ra lỗi khi gọi API: " + error);
        }
    });

    $('#saveSaleProduct').click(function () {
        saveSaleProduct(id);
    });
}



//xóa sale
function deleteSale(id){
    Swal.fire({
        title: 'Thông Báo từ hệ thống',
        text: "Bạn có chắc là xóa sale thứ '" + id+"'",
        icon: 'warning',
        confirmButtonText: 'Xác nhận',
        cancelButtonText: 'Hủy',
        showCancelButton: true,
        allowOutsideClick: false,
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: '/admin/api/sales/'+id,
                type: 'DELETE',
                success: function (data) {
                    Swal.fire({
                        title: 'Thông Báo từ hệ thống',
                        text: "Xóa thành công",
                        icon: 'success',
                        confirmButtonText: 'Xác nhận',
                        allowOutsideClick: false,
                    }).then((result) => {
                        location.reload();
                    })
                },
                error: function (xhr, status, error) {
                    console.error("Đã xảy ra lỗi khi gọi API: " + error);
                }
            });
        }
    });

}


//chỉnh sửa sale
function UpdateSale(){
    var id = $("#upid").val();
    var saleName = $("#upSalename").val();
    var salePercent = $("#upSalePercent").val();
    var saleStart = $("#upStartDate").val();
    var saleEnd = $("#upEndDate").val();

    $.ajax({
        url: '/admin/api/sales/'+id,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            saleName: saleName,
            salePersent: salePercent,
            slateStart: saleStart,
            saleEnd: saleEnd
        }),
        success: function (data) {
            Swal.fire({
                title: 'Thông Báo từ hệ thống',
                text: 'Cập nhật thành công',
                icon: 'success', // Có thể thay đổi icon thành 'error', 'warning', 'info', hoặc 'question'
                confirmButtonText: 'Xác nhận',
                allowOutsideClick: false,
            }).then((result) => {
                location.reload();
            })
        },
        error: function (xhr, status, error) {
            console.error("Đã xảy ra lỗi khi gọi API: " + error);
        }
    });
}



//thêm sale
function insertSale(){
    var saleName = $("#inSalename").val();
    var salePercent = $("#inSalePercent").val();
    var saleStart = $("#StartDate").val();
    var saleEnd = $("#EndDate").val();

    $.ajax({
        url: '/admin/api/sales/createSale',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            saleName: saleName,
            salePersent: salePercent,
            slateStart: saleStart,
            saleEnd: saleEnd
        }),
        success: function (data) {
            Swal.fire({
                title: 'Thông Báo từ hệ thống',
                text: 'Thêm 1 Sale thành công',
                icon: 'success', // Có thể thay đổi icon thành 'error', 'warning', 'info', hoặc 'question'
                confirmButtonText: 'Xác nhận',
                allowOutsideClick: false,
            }).then((result) => {
                location.reload();
            })
        },
        error: function (xhr, status, error) {
            console.error("Đã xảy ra lỗi khi gọi API: " + error);
        }
    });
}



