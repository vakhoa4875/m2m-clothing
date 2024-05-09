


//giỏ hàng
function compareData() {
    var productList = document.getElementById("productList");
    var div = document.getElementById("thanhtoan");
    productList.innerHTML = '';
    div.innerHTML ='';
    // Kiểm tra xem local storage có dữ liệu không
    if (localStorage.length > 0) {
        // Duyệt qua tất cả các phần tử trong local storage
        for (var i = 0; i < localStorage.length; i++) {
            var key = localStorage.key(i);
            var value = localStorage.getItem(key);

            var object = JSON.parse(value);
            // Tạo các thẻ HTML để hiển thị thông tin của đối tượng
            var tr = document.createElement("tr");

            tr.innerHTML = `
                                        <th scope="row">
                                            <li style="overflow: hidden" height="auto" width="136px" class="d-flex align-items-center ms-4">
                                                <input class="form-check-input me-2" type="checkbox" id="checkboxNoLabel" value="" aria-label="..." >
                                                <img src="${object.linkanh}" alt="" style="display: block; width: 150px; height: 150px; object-fit: contain;">
                                                <span class="ms-3">${object.tensp}</span>
                                            </li>
                                        </th>
                                        <td class="align-middle">$${object.gia.toFixed(2)}</td>
                                        <td class="align-middle">
                                            <div>
                                                <nav aria-label="Page navigation example">
                                                    <ul class="pagination">
                                                        <li class="page-item">
                                                            <a class="page-link" href="#" onclick="truSoLuong('${key}')">
                                                                <i class="fa-solid fa-minus"></i>
                                                            </a>
                                                        </li>
                                                        <li class="page-item disabled">
                                                            <a class="page-link text-danger" href="#">${object.soLuong}</a>
                                                        </li>
                                                        <li class="page-item">
                                                            <a class="page-link text-dark" href="#" onclick="themSoLuong('${key}')">
                                                                <i class="fa-solid fa-plus"></i>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                  </nav>
                                            </div>
                                        </td>
                                        <td class="align-middle fw-bold" >$${(object.soLuong * object.gia).toFixed(2)}</td>
                                        <td class="align-middle">
                                            <button class="btn btn-close" onclick="xoamSoLuong('${key}')"></button>
                                        </td>
                    `;
            document.getElementById("productList").appendChild(tr);
            this.capNhatTienTong();
            layTongSoLuong();
        }
        var div = document.createElement("a");
            div.innerHTML = `
             <a href="/thanhtoan" class="btn btn-outline-danger"  id="creategiohang" >Thành toán hóa đơn</a>
            `;
        document.getElementById("thanhtoan").appendChild(div);
    } else {
        Swal.fire({
            title: 'Thông Báo từ hệ thống',
            text: 'Không còn gì trong giỏ hàng của bạn.',
            icon: 'info', // Có thể thay đổi icon thành 'error', 'warning', 'info', hoặc 'question'
            confirmButtonText: 'Xác nhận',
            allowOutsideClick: false
        })
            var tr = document.createElement("tr");
            tr.innerHTML = `
                    <td colspan="6" class="text-center">
                        <div class="alert alert-info" role="alert">
                            Giỏ hàng không có gì!
                        </div>
                    </td>
                `;
            document.getElementById("productList").appendChild(tr);

            var div = document.createElement("a");
            div.innerHTML = `
                 <a href="/thanhtoan" class="btn btn-outline-danger disabled"  id="creategiohang" >Thành toán hóa đơn</a>
            `;
            document.getElementById("thanhtoan").appendChild(div);

        capNhatTienTong();
        layTongSoLuong();
    }
}


function layTongSoLuong() {
    var tongsp = document.getElementById("tongSoLuongSP");
    var tongSoLuong = 0;
    if (localStorage.length > 0) {
        for (var i = 0; i < localStorage.length; i++) {
            var key = localStorage.key(i);
            var value = localStorage.getItem(key);
            var object = JSON.parse(value);
            tongSoLuong++;
        }
    }else {
        tongsp.textContent = tongSoLuong;
    }
    tongsp.textContent = tongSoLuong;
}

function truSoLuong(key) {
    // Lấy đối tượng từ localStorage
    var item = JSON.parse(localStorage.getItem(key));
    if (item) {
        // Giảm số lượng của đối tượng
        item.soLuong--;
        if (item.soLuong <= 0) {
            // Xóa đối tượng từ localStorage
            localStorage.removeItem(key);
            compareData();
            capNhatTienTong()
        } else {
            // Nếu số lượng lớn hơn 0, cập nhật lại đối tượng trong localStorage
            localStorage.setItem(key, JSON.stringify(item));
            compareData();
            capNhatTienTong()
        }
    }
}

function capNhatTienTong() {
    var tongTien = 0;
    var tienCanTra = 0;
    if (localStorage.length > 0) {
        // Duyệt qua tất cả các phần tử trong local storage
        for (var i = 0; i < localStorage.length; i++) {
            var key = localStorage.key(i);
            var value = localStorage.getItem(key);

            var object = JSON.parse(value);
            // tienGiamDiscount = object.discount; // lấy discount của ma giam gia
            tongTien += object.soLuong * object.gia;
            tienCanTra += object.soLuong * object.gia;

        }
        // Áp dụng giảm giá từ voucher nếu có
        if (appliedVoucher) {
            var reduceAmount = tongTien * (appliedVoucher.voucherReducePercent / 100);

            tongTien -= reduceAmount;
            // Cập nhật giá trị của thẻ HTML mới
            var cacPhanTuTienGiam = document.querySelectorAll(".tiengiam");
            // phanTuTienGiam.textContent = "$" + reduceAmount.toFixed(2);
            cacPhanTuTienGiam.forEach(function (element) {
                element.textContent = "$" + reduceAmount.toFixed(2); // Cập nhật giá trị mới, ở đây là tổng tiền

                //cập nhật lại discount trong localstorage
                var key = localStorage.key(0);
                var value = localStorage.getItem(key);
                var object = JSON.parse(value);
                object.discount = reduceAmount.toFixed(2); // lấy tien giam rồi sửa lại trong obj của js
                localStorage.setItem(key, JSON.stringify(object)); // sau đó
            });
        }else{

            var cacPhanTuTienGiam = document.querySelectorAll(".tiengiam");
            // phanTuTienGiam.textContent = "$" + reduceAmount.toFixed(2);
            cacPhanTuTienGiam.forEach(function (element) {
                element.textContent = "$" + 0;
            });
        }
        var cacPhanTuTienCanTra = document.querySelectorAll(".tienCanTra");
        // Lặp qua từng phần tử và cập nhật giá trị mới
        cacPhanTuTienCanTra.forEach(function (element) {
            element.textContent = "$" + tienCanTra.toFixed(2); // Cập nhật giá trị mới, ở đây là tổng tiền
        });
        var cacPhanTu = document.querySelectorAll(".tienThanhToan");
        // Lặp qua từng phần tử và cập nhật giá trị mới
        cacPhanTu.forEach(function (element) {
            element.textContent = "$" + tongTien.toFixed(2); // Cập nhật giá trị mới, ở đây là tổng tiền
        });

    } else {
        var cacPhanTu = document.querySelectorAll(".tienThanhToan");
        // Lặp qua từng phần tử và cập nhật giá trị mới
        cacPhanTu.forEach(function (element) {
            element.textContent = "$" + 0; // Cập nhật giá trị mới, ở đây là tổng tiền
        });
    }
}


function themSoLuong(key) {
    // Lấy đối tượng từ localStorage
    var item = JSON.parse(localStorage.getItem(key));

    if (item) {
        // Tăng số lượng của đối tượng
        item.soLuong++;

        // Cập nhật lại đối tượng trong localStorage
        localStorage.setItem(key, JSON.stringify(item));
        compareData();
        capNhatTienTong();
    }
}

function xoamSoLuong(key) {
    localStorage.removeItem(key);
    compareData();
    var cacPhanTuTienCanTra = document.querySelectorAll(".tienCanTra");
    // Lặp qua từng phần tử và cập nhật giá trị mới
    cacPhanTuTienCanTra.forEach(function (element) {
        element.textContent = "$" + 0; // Cập nhật giá trị mới, ở đây là tổng tiền
    });
    capNhatTienTong();
}

function xoaHetGioHang() {
    localStorage.clear()
    compareData();
    capNhatTienTong();
}

window.onload = compareData;




var appliedVoucher = null; // Biến để lưu trữ thông tin voucher được áp dụng
$(document).ready(function () {
    let selectedCategoryId = null;

    //Trang chủ
    $.ajax({
        type: "get",
        url: "http://localhost:8083/allcategoryapi",
        data: "data",
        dataType: "json",
        success: function (response) {
            var categories = [];


            for (var i = 0; i < response.length; i++) {
                categories.push(response[i].category_name);
            }

            appendCategoriesToList(categories);
            layTongSoLuong();
        },
        error: function (xhr, status, error) {
            alert("Tải dữ liệu thất bại");
            console.log(error);
        }
    });

    $.ajax({
        type: "get",
        url: "http://localhost:8083/findTop6ByOrderByGiaBanDesc",
        data: "data",
        dataType: "json",
        success: function (response) {
            var sanpham = [];
            var anh = [];

            for (var i = 0; i < response.length; i++) {
                sanpham.push(response[i]);
                anh.push(response[i].pictures.split(','));
            }
            loadSanPhamNoiBat(sanpham,anh);
            layTongSoLuong();
        },
        error: function (xhr, status, error) {
            alert("Tải dữ liệu thất bại");
            console.log(error);
        }
    });


    function appendCategoriesToList(categories) {
        $.each(categories, function (index, category) {
            // Tạo một thẻ li mới cho mỗi đối tượng user
            var link = $("<a>").attr("id", "category_" + index).addClass("rounded-1 btn text-white hoverbutton nghe").css("width", "120px").text(category);
            var div = $("<div>").append(link);

            if (category == "Jewels & Accessories") {
                div.addClass("special-category");
                link.css("width", "200px");
            }
            // Lưu trữ ID của danh mục vào thuộc tính "data-category-id"
            link.attr("data-category-id", index + 1);

            // Thêm thẻ li vào danh sách
            $("#userListCategory").append(div);
            link.on("click", function() {
                // Lấy giá trị của thuộc tính "value" của thẻ <a>
                selectedCategoryId = $(this).attr("data-category-id");
                console.log(selectedCategoryId);
                window.location.href = "/categoryType?categoryId="+(index+1);
            });
        });
    }

    function loadSanPhamNoiBat(sanpham, anh) {

        $.each(sanpham, function (index, item) {
            var html = `
                            <div class="col-lg-2 col-md-3 col-sm-4">
                                <div class="card rounded-3 shadow border-0 text-center d-product justify-content-center mb-3" style="overflow: hidden;">
                                    <a href="/product?slug_url=${item.slugUrl}" class="stretched-link">
                                            ${item.sale ? `
                                                <div style="position: absolute">       
                                                    <span class="badge text-bg-danger">${item.sale.salePercent}%</span>
                                                </div>
                                            `:``}
                                            <div class="img-container">
                                                <img src="/assests/media/${anh[index][0]}" class="img-fluid mt-2 mb-3" alt="">
                                            </div>
                                            <div class="w-100">
                                                <span class="d-inline-block text-truncate" style="max-width: 90%;"> ${item.productName} </span>
                                            </div>
                                        <div class="w-100">
                                            <div class="d-flex justify-content-around">
                                                ${item.sale ? `
                                                       <span> <del> ${item.price} </del> </span>
                                                       <span style="color:#c07d4b; font-weight: bolder">${((item.price) - (item.sale.salePercent / 100.00 * item.price)).toFixed(2)}</span>
                                                ` : `
                                                        <span> ${item.price}</span>
                                                `}
                                            </div>
                                            <div class="">
                                                <div class="rounded-bottom-3" style="background-color: rgb(224, 150, 150);">
                                                    <div class="text-white fw-bolder ">Mua ngay</div>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                `
            $("#sanphamtrangchu").append(html);
            layTongSoLuong();
        });
    }
    //hàm phatteacher
    var appliedVoucherID = null; // Biến để lưu trữ ID của voucher được áp dụng gần đây nhất

// Hàm gọi API để lấy danh sách voucher
    function getAllVoucherByUserEmail() {
        $.ajax({
            url: '/api-public/vouchers/getCartVouchersByEmail',
            type: 'GET',
            success: function (data) {
                console.log(data);
                $('#modalBodyVoucherInCart').empty();

                // Lưu trữ ID của voucher đã được chọn trước đó (nếu có)
                var selectedVoucherID = appliedVoucherID;

                // Định dạng ngày bằng Moment.js và tạo các phần tử voucher
                $.each(data, function (index, voucher) {
                    var formattedStartDate = moment(voucher.startDay).format('DD-MM-YYYY');
                    var formattedEndDate = moment(voucher.endDay).format('DD-MM-YYYY');
                    var newRow = `
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card mb-3">
                                <div class="row g-0">
                                    <div class="col-md-4">
                                        <img src="/assests/images/logoVoucher.jpg" class="img-fluid rounded-start" alt="...">
                                    </div>
                                    <div class="col-md-8">
                                        <div class="card-body">
                                            <h5 class="card-title">${voucher.voucherName}</h5>
                                            <p class="card-text">Reduce: ${voucher.reduce}%</p>
                                            <p class="card-text">End day: ${formattedEndDate}</p>
                                            <button class="btn btn-primary" data-voucher-id="${voucher.voucherID}" id="addVoucherBtn_${voucher.voucherID}">Add Voucher</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                `;
                    $('#modalBodyVoucherInCart').append(newRow);
                });

                // Áp dụng trạng thái vô hiệu hóa cho các nút voucher dựa trên ID
                $('[id^="addVoucherBtn_"]').each(function () {
                    var voucherID = $(this).data('voucher-id');
                    if (voucherID === selectedVoucherID) {
                        // Nếu voucher đã được chọn trước đó, vô hiệu hóa nút
                        $(this).prop("disabled", true);
                    }
                });

                // Xử lý sự kiện khi nút "Add Voucher" được nhấn
                $('[id^="addVoucherBtn_"]').click(function () {
                    var newVoucherID = $(this).data('voucher-id');
                    if (appliedVoucherID !== newVoucherID) {
                        // Kiểm tra xem đã chọn voucher mới chưa
                        if (appliedVoucherID !== null) {
                            // Nếu đã chọn voucher mới, kích hoạt lại nút của voucher cũ (nếu có)
                            $('#addVoucherBtn_' + appliedVoucherID).prop("disabled", false);
                        }

                        // Lưu ID của voucher mới và vô hiệu hóa nút
                        appliedVoucherID = newVoucherID;
                        $(this).prop("disabled", true);

                        // Tiến hành các thao tác khác như lưu thông tin voucher và cập nhật tổng tiền
                        var voucherName = $(this).closest('.card-body').find('.card-title').text();
                        var voucherReducePercent = parseFloat($(this).siblings('.card-text').text().split(":")[1]);

                        appliedVoucher = {
                            voucherID: newVoucherID,
                            voucherName: voucherName,
                            voucherReducePercent: voucherReducePercent
                        };

                        $('#addVoucherText').text('Bạn đã thêm ' + voucherName + ' vào giỏ hàng.');
                        $('#addVoucherText').removeClass('d-none').addClass('d-block');
                        capNhatTienTong();
                    }
                });
            },
            error: function (xhr, status, error) {
                console.error("Đã xảy ra lỗi khi gọi API: " + error);
            }
        });
    }

    $('#btnvoucherModal').click(function () {
        getAllVoucherByUserEmail(); // Gọi hàm updateUser() khi nhấn vào nút "Submit"
    });



})










//chi tiết sản phẩm
var gia = document.getElementById("productPrice");
var giaString = gia.textContent;
// Thay thế dấu phẩy bằng dấu chấm
var giaSo = parseFloat(giaString.replace("$", "").replace(",", "."));

var tensp = document.getElementById("productName");
var anh = document.getElementById("productAnh");
// Lấy giá trị của thuộc tính src
var srcAnh = anh.getAttribute("src");
let btnCart = document.getElementById("soLuong");
// Khởi tạo biến để đếm số lượng
var soLuong = 0;
var discount = 0;
// Thêm sự kiện click cho nút button
btnCart.addEventListener("click", function() {
    // Tăng số lượng sản phẩm lên 1
    soLuong++;

    // Kiểm tra xem dữ liệu "sanPham" đã tồn tại trong local storage chưa
    var sanPhamJSON = localStorage.getItem(tensp.textContent);

    // Nếu dữ liệu đã tồn tại
    if (sanPhamJSON) {
        // Chuyển chuỗi JSON thành đối tượng JavaScript
        var sanPham = JSON.parse(sanPhamJSON);

        // Tăng số lượng sản phẩm trong đối tượng
        sanPham.soLuong++;

        // Chuyển đối tượng thành chuỗi JSON và cập nhật lại trong local storage
        localStorage.setItem(tensp.textContent, JSON.stringify(sanPham));
        layTongSoLuong();
    } else {
        console.log(giaSo)
        // Nếu dữ liệu chưa tồn tại, tạo mới đối tượng sản phẩm và lưu vào local storage
        var sanPhamMoi = {
            gia: giaSo,
            tensp: tensp.textContent,
            linkanh: srcAnh,
            soLuong: 1,
            discount : 0
        };

        // Chuyển đối tượng thành chuỗi JSON và lưu vào local storage
        localStorage.setItem(tensp.textContent, JSON.stringify(sanPhamMoi));
        layTongSoLuong();
    }
});

let btnBuyNow = document.getElementById("buynow");
btnBuyNow.addEventListener("click",function (){
    // Đặt số lượng mặc định là 1
    var soLuong = 1;

    // Kiểm tra xem dữ liệu "sanPham" đã tồn tại trong local storage chưa
    var sanPhamJSON = localStorage.getItem(tensp.textContent);

    // Nếu dữ liệu đã tồn tại
    if (sanPhamJSON) {
        // Chuyển chuỗi JSON thành đối tượng JavaScript
        var sanPham = JSON.parse(sanPhamJSON);

        // Tăng số lượng sản phẩm trong đối tượng
        sanPham.soLuong++;

        // Cập nhật lại đối tượng trong local storage
        localStorage.setItem(tensp.textContent, JSON.stringify(sanPham));
        layTongSoLuong();
        // Chuyển hướng người dùng đến trang giỏ hàng
        window.location.href = "/giohang";
    } else {
        // Nếu dữ liệu chưa tồn tại, tạo mới đối tượng sản phẩm và lưu vào local storage
        var sanPhamMoi = {
            gia: giaSo,
            tensp: tensp.textContent,
            linkanh: srcAnh,
            soLuong: soLuong,
            discount : 0
        };

        // Lưu đối tượng mới vào local storage
        localStorage.setItem(tensp.textContent, JSON.stringify(sanPhamMoi));
        layTongSoLuong();
        // Chuyển hướng người dùng đến trang giỏ hàng
        window.location.href = "/giohang";
    }
})
