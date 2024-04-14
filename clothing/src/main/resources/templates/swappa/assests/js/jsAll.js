


//giỏ hàng
function compareData() {

    var productList = document.getElementById("productList");
    productList.innerHTML = '';
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
                                                <img src="${object.linkanh}" alt="" width="auto" style="display: block; max-width: 150px; max-height: 150px; width: auto; height: auto;">
                                                <span class="ms-3">${object.tensp}</span>
                                            </li>
                                        </th>
                                        <td class="align-middle">$${object.gia}</td>
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
                                        <td class="align-middle fw-bold" >$${(object.soLuong * object.gia)}</td>
                                        <td class="align-middle">
                                            <button class="btn btn-close" onclick="xoamSoLuong('${key}')"></button>
                                        </td>
                    `;
            document.getElementById("productList").appendChild(tr);
            this.capNhatTienTong();
            layTongSoLuong();
        }

    } else {
        Swal.fire({
            title: 'Thông Báo từ hệ thống',
            text: 'Không còn gì trong giỏ hàng của bạn.',
            icon: 'info', // Có thể thay đổi icon thành 'error', 'warning', 'info', hoặc 'question'
            confirmButtonText: 'Xác nhận',
            allowOutsideClick: false
        });
        var tr = document.createElement("tr");
        tr.innerHTML = `
                    <td colspan="6" class="text-center">
                        <div class="alert alert-info" role="alert">
                            Giỏ hàng không có gì!
                        </div>
                    </td>
                `;
        document.getElementById("productList").appendChild(tr);
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
    if (localStorage.length > 0) {
        // Duyệt qua tất cả các phần tử trong local storage
        for (var i = 0; i < localStorage.length; i++) {
            var key = localStorage.key(i);
            var value = localStorage.getItem(key);

            var object = JSON.parse(value);

            tongTien += object.soLuong * object.gia;
        }
        var cacPhanTu = document.querySelectorAll(".tienThanhToan");
        // Lặp qua từng phần tử và cập nhật giá trị mới
        cacPhanTu.forEach(function (element) {
            element.textContent = "$" + tongTien; // Cập nhật giá trị mới, ở đây là tổng tiền
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
    capNhatTienTong();
}

function xoaHetGioHang() {
    localStorage.clear()
    compareData();
    capNhatTienTong();
}

window.onload = compareData;





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
                                                       <span style="color:#c07d4b; font-weight: bolder">${((item.price) - (item.sale.salePercent / 100 * item.price)).toFixed(2)}</span>
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






})










//chi tiết sản phẩm
var gia = document.getElementById("productPrice");
var giaString = gia.textContent;
var giaSo = parseInt(giaString.replace("$", ""));

var tensp = document.getElementById("productName");
var anh = document.getElementById("productAnh");
// Lấy giá trị của thuộc tính src
var srcAnh = anh.getAttribute("src");
let btnCart = document.getElementById("soLuong");
// Khởi tạo biến để đếm số lượng
var soLuong = 0;
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
        // Nếu dữ liệu chưa tồn tại, tạo mới đối tượng sản phẩm và lưu vào local storage
        var sanPhamMoi = {
            gia: giaSo,
            tensp: tensp.textContent,
            linkanh: srcAnh,
            soLuong: 1
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
            soLuong: soLuong
        };

        // Lưu đối tượng mới vào local storage
        localStorage.setItem(tensp.textContent, JSON.stringify(sanPhamMoi));
        layTongSoLuong();
        // Chuyển hướng người dùng đến trang giỏ hàng
        window.location.href = "/giohang";
    }
})