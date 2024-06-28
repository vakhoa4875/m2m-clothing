


//giỏ hàng
function compareData() {
    sessionStorage.setItem("tienvoucher", 0);
    sessionStorage.removeItem("voucherdetill");
    var productList = document.getElementById("productList");
    var div = document.getElementById("thanhtoan");
    productList.innerHTML = '';
    div.innerHTML ='';
    // Kiểm tra xem local storage có dữ liệu không
    if (localStorage.getItem(sessionStorage.getItem("tendn"))) {
        var objarray = JSON.parse(localStorage.getItem(sessionStorage.getItem("tendn")));

        if (objarray.length == 0){
            var tr = document.createElement("tr");
            var productList = document.getElementById("productList");
            productList.innerHTML = '';
            var div = document.getElementById("thanhtoan");
            div.innerHTML ='';
            var tr = document.createElement("tr");
            tr.innerHTML = `
                    <td colspan="6" class="text-center">
                        <div class="alert alert-info" role="alert">
                            There's nothing in your cart!
                        </div>
                    </td>
                `;
            document.getElementById("productList").appendChild(tr);

            $('#tiengiamgia').text("$0");
            $('.tienCanTra').text("$0");
            $('.tienThanhToan').text("$0");

            var thanhtoan = $('#thanhtoan');
            var div = `<a href="/thanhtoan" class="btn btn-outline-danger disabled"  id="creategiohang" >Checkout cart</a>`;
            thanhtoan.append(div);
            if (thanhtoan.length == 1){
                thanhtoan.removeChild(1);
            }


        }

        // Duyệt qua tất cả các phần tử trong local storage
        for (var int = 0; int < objarray.length; int++) {
            var object = objarray[int];
            // Tạo các thẻ HTML để hiển thị thông tin của đối tượng
            var tr = document.createElement("tr");
            tr.innerHTML = `
                                        <th scope="row">
                                            <li style="overflow: hidden" height="auto" width="136px" class="d-flex align-items-center ms-4">
<!--                                                <input class="form-check-input me-2" type="checkbox" id="checkboxNoLabel" value="" aria-label="..." >-->
                                                <img src="${object.linkanh}" alt="" style="display: block; width: 150px; height: 150px; object-fit: contain;" >
                                                <span class="ms-3">${object.tensp}</span>
                                            </li>
                                        </th>
                                        <td class="align-middle">$${object.gia.toFixed(2)}</td>
                                        <td class="align-middle">
                                            <div>
                                                <nav aria-label="Page navigation example">
                                                    <ul class="pagination">
                                                        <li class="page-item">
                                                            <a class="page-link" href="#" onclick="truSoLuong('${int}')">
                                                                <i class="fa-solid fa-minus"></i>
                                                            </a>
                                                        </li>
                                                        <li class="page-item disabled">
                                                            <a class="page-link text-danger" href="#">${object.soLuong}</a>
                                                        </li>
                                                        <li class="page-item">
                                                            <a class="page-link text-dark" href="#" onclick="themSoLuong('${int}')">
                                                                <i class="fa-solid fa-plus"></i>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                  </nav>
                                            </div>
                                        </td>
                                        <td class="align-middle fw-bold" >$${(object.soLuong * object.gia).toFixed(2)}</td>
                                        <td class="align-middle">
                                            <button class="btn btn-close" onclick="xoamSoLuong('${int}')"></button>
                                        </td>
                    `;
            document.getElementById("productList").appendChild(tr);
            this.capNhatTienTong();
            layTongSoLuong();
        }
        var div = document.createElement("a");
            div.innerHTML = `
             <a href="/a/payment" class="btn btn-outline-danger"  id="creategiohang" >Checkout cart</a>
            `;
        document.getElementById("thanhtoan").appendChild(div);
    }else{
        Swal.fire({
            title: 'Notifications from the system',
            text: 'There nothing in your cart',
            icon: 'info', // Có thể thay đổi icon thành 'error', 'warning', 'info', hoặc 'question'
            confirmButtonText: 'Confirm',
            allowOutsideClick: false
        })
        var tr = document.createElement("tr");
        tr.innerHTML = `
                    <td colspan="6" class="text-center">
                        <div class="alert alert-info" role="alert">
                            The shopping cart is empty!
                        </div>
                    </td>
                `;
        document.getElementById("productList").appendChild(tr);

        var div = document.createElement("a");
        div.innerHTML = `
             <a href="/a/payment" class="btn btn-outline-danger"  id="creategiohang" >Checkout cart</a>
            `;
        document.getElementById("thanhtoan").appendChild(div);

        capNhatTienTong();
        layTongSoLuong();
    }
}


function layTongSoLuong() {
    var tongsp = document.getElementById("tongSoLuongSP");
    var tongSoLuong = 0;
    if (localStorage.getItem(sessionStorage.getItem("tendn"))) {
        var objarray = JSON.parse(localStorage.getItem(sessionStorage.getItem("tendn")));
        for (var int = 0; int < objarray.length; int++) {
            tongSoLuong = objarray.length;
        }
    }else {
        tongsp.textContent = tongSoLuong;
    }
    tongsp.textContent = tongSoLuong;
}

function truSoLuong(int) {
    var objArrya = JSON.parse(localStorage.getItem(sessionStorage.getItem("tendn")));
    objArrya.forEach(function(obj, index) {
        if(index == int){
            if(obj.soLuong <= 1){
                objArrya.splice(int,1);
                localStorage.setItem(sessionStorage.getItem("tendn"), JSON.stringify(objArrya));
                return;
            }
            obj.soLuong--;
            localStorage.setItem(sessionStorage.getItem("tendn"), JSON.stringify(objArrya));
            return;
        }
    });
    compareData();
}

// var appliedVoucher = null; // Biến để lưu trữ thông tin voucher được áp dụng

function capNhatTienTong() {
    if (localStorage.getItem(sessionStorage.getItem("tendn"))) {
        let tienCanTra = 0;
        var tongTien = 0;
        var objarray = JSON.parse(localStorage.getItem(sessionStorage.getItem("tendn")));
        // Duyệt qua tất cả các phần tử trong local storage
        for (var int = 0; int < objarray.length; int++) {
            var object = objarray[int];
            tongTien += (object.gia * object.soLuong);
            tienCanTra += (object.gia * object.soLuong);

        }
        // Áp dụng giảm giá từ voucher nếu có
        var voucher = sessionStorage.getItem("voucherdetill");
        if (voucher != null) {
            const myObjectString = sessionStorage.getItem("voucherdetill");
            const voucherobj = JSON.parse(myObjectString);

            var reduceAmount = tongTien * (voucherobj.voucherReducePercent / 100);

            tongTien -= reduceAmount;
            // Cập nhật giá trị của thẻ HTML mới
            var cacPhanTuTienGiam = document.querySelectorAll(".tiengiam");
            // phanTuTienGiam.textContent = "$" + reduceAmount.toFixed(2);
            cacPhanTuTienGiam.forEach(function (element) {
                element.textContent = "$" + reduceAmount.toFixed(2); // Cập nhật giá trị mới, ở đây là tổng tiền
                sessionStorage.setItem("tienvoucher",reduceAmount.toFixed(3));
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


function themSoLuong(int) {
    var objArrya = JSON.parse(localStorage.getItem(sessionStorage.getItem("tendn")));
    objArrya.forEach(function(obj, index) {
        if(index == int){
            obj.soLuong++;
            localStorage.setItem(sessionStorage.getItem("tendn"), JSON.stringify(objArrya));
            return;
        }
    });
    compareData();
    capNhatTienTong();
}

function xoamSoLuong(int) {
    var objArrya = JSON.parse(localStorage.getItem(sessionStorage.getItem("tendn")));
    if(objArrya.length === 1){
        Swal.fire({
            title: 'Notifications from the system',
            text: 'There nothing in your cart',
            icon: 'info', // Có thể thay đổi icon thành 'error', 'warning', 'info', hoặc 'question'
            confirmButtonText: 'Confirm',
            allowOutsideClick: false
        }).then((result) => {
            var tr = document.createElement("tr");
            var productList = document.getElementById("productList");
            productList.innerHTML = '';
            var div = document.getElementById("thanhtoan");
            div.innerHTML ='';
            var tr = document.createElement("tr");
            tr.innerHTML = `
                    <td colspan="6" class="text-center">
                        <div class="alert alert-info" role="alert">
                            There's nothing in your cart!
                        </div>
                    </td>
                `;
            document.getElementById("productList").appendChild(tr);

            var div = document.createElement("a");
            div.innerHTML = `
                 <a href="/a/payment" class="btn btn-outline-danger disabled"  id="creategiohang" >Checkout cart</a>
            `;
            document.getElementById("thanhtoan").appendChild(div);
            layTongSoLuong();
            capNhatTienTong();
        })
    }


    objArrya.forEach(function(obj, index) {
        if(index == int){
            objArrya.splice(int,1);
            localStorage.setItem(sessionStorage.getItem("tendn"), JSON.stringify(objArrya));
            return;
        }


    });

    compareData();
    capNhatTienTong();
}

function xoaHetGioHang() {
    localStorage.removeItem(sessionStorage.getItem("tendn"));
    if(localStorage.getItem(sessionStorage.getItem("tendn")) === null){
        Swal.fire({
            title: 'Notifications from the system',
            text: 'There nothing in your cart!',
            icon: 'info', // Có thể thay đổi icon thành 'error', 'warning', 'info', hoặc 'question'
            confirmButtonText: 'Confirm',
            allowOutsideClick: false
        }).then((result) => {
            var productList = document.getElementById("productList");
            productList.innerHTML = '';
            var div = document.getElementById("thanhtoan");
            div.innerHTML ='';
            var tr = document.createElement("tr");
            tr.innerHTML = `
                    <td colspan="6" class="text-center">
                        <div class="alert alert-info" role="alert">
                            There's nothing in your cart!
                        </div>
                    </td>
                `;
            document.getElementById("productList").appendChild(tr);

            var div = document.createElement("a");
            div.innerHTML = `
                 <a href="/a/payment" class="btn btn-outline-danger disabled"  id="creategiohang" >Checkout cart</a>
            `;
            document.getElementById("thanhtoan").appendChild(div);
            layTongSoLuong();
            capNhatTienTong();
        })
    }
}

window.onload = compareData;




$(document).ready(function () {
    let selectedCategoryId = null;

    //Trang chủ
    $.ajax({
        type: "get",
        url: "/allcategoryapi",
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
            console.error(error);
        }
    });

    $.ajax({
        type: "get",
        url: "/findTop6ByOrderByGiaBanDesc",
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
            console.error(error);
        }
    });


    function appendCategoriesToList(categories) {
        console.log(categories)
        $.each(categories, function (index, category) {
            // Tạo một thẻ li mới cho mỗi đối tượng user
            let link = $("<a>")
                .attr("id", "category_" + index)
                .addClass("rounded-1 btn text-white hoverbutton nghe")
                .css("width", "120px")
                .text(category)
                .click(function() {
                    console.log(index);
                    window.location.href = "/p/AllcategoryType/"+(category);
                });
            ;
            let div = $("<div>").append(link);
            if (category == "Jewels & Accessories") {
                div.addClass("special-category");
                link.css("width", "200px");
            }
            // Lưu trữ ID của danh mục vào thuộc tính "data-category-id"
            link.attr("data-category-id", index + 1);

            // Thêm thẻ li vào danh sách
            $("#userListCategory").append(div);
        });
    }

    function loadSanPhamNoiBat(sanpham, anh) {

        $.each(sanpham, function (index, item) {
            var html = `
                            <div class="col-lg-2 col-md-3 col-sm-4">
                                <div class="card rounded-3 shadow border-0 text-center d-product justify-content-center mb-3" style="overflow: hidden;">
                                    <a href="/p/product?slug_url=${item.slugUrl}" class="stretched-link">
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
                                                    <div class="text-white fw-bolder ">Buy now</div>
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
                sessionStorage.setItem("tienvoucher",0);
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


                        var appliedVoucher = {
                            voucherID: newVoucherID,
                            voucherName: voucherName,
                            voucherReducePercent: voucherReducePercent
                        };
                        const objvoucher = JSON.stringify(appliedVoucher);

                        sessionStorage.setItem("voucherdetill",objvoucher);

                        $('#addVoucherText').text('Bạn đã thêm ' + voucherName + ' vào giỏ hàng.');
                        $('#addVoucherText').removeClass('d-none').addClass('d-block');
                        capNhatTienTong();
                        let tiengiam = $('#tiengiamgia').text();
                        sessionStorage.setItem("tienvoucher",tiengiam.slice(1));
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
var dangnhap = document.getElementById("dn");
var giaString = gia.textContent;
// Thay thế dấu phẩy bằng dấu chấm
var giaSo = parseFloat(giaString.replace("$", "").replace(",", "."));
var idproduct = document.getElementById("idproduct");
var idproductstring = parseFloat(idproduct.textContent);
var tensp = document.getElementById("productName");
var anh = document.getElementById("productAnh");
// Lấy giá trị của thuộc tính src
var srcAnh = anh.getAttribute("src");
let btnCart = document.getElementById("soLuong");
// Khởi tạo biến để đếm số lượng
var soLuong = 0;

btnCart.addEventListener("click", function() {
    const productImage = document.querySelector(`.product img[data-id="${1}"]`);
    const cart = document.getElementById('cart');
    const productRect = productImage.getBoundingClientRect();
    const cartRect = cart.getBoundingClientRect();

    const productClone = productImage.cloneNode(true);
    productClone.classList.add('fly-to-cart');
    productClone.style.left = `${productRect.left-400}px`;
    productClone.style.top = `${productRect.top}px`;
    productClone.style.position = 'fixed';
    productClone.style.width = `${productRect.width}px`;
    productClone.style.height = `${productRect.height}px`;
    document.body.appendChild(productClone);

    requestAnimationFrame(() => {
        productClone.style.transform = `translate(${(cartRect.left - productRect.left)-200}px, ${(cartRect.top - productRect.top)-300}px) scale(0.1)`;
        productClone.style.opacity = '0';
    });
    let flag = false;
    productClone.addEventListener('transitionend', () => {
        if (!flag) { //dùng để check và cho hàm này được hoạt động 1 lần duy nhất(vì hàm hoạt động 2 lần)
            if(dangnhap.innerHTML === ""){
                alert("You are not logged in, please log in.!");
                const currentUrl = window.location.href;
                const url = new URL(currentUrl);
                const pathAndQuery = url.pathname + url.search;
                sessionStorage.setItem("duongdan", pathAndQuery);
                window.location.href = "/p/login";
                return;
            }else {
                sessionStorage.removeItem("duongdan");
                sessionStorage.setItem("tendn",dangnhap.innerText);
            }

            var sanPhamMoi = {
                gia: giaSo,
                tensp: tensp.textContent,
                linkanh: srcAnh,
                idproductt : idproductstring,
                soLuong: 1
            };

            var arrayObj = []

            if(localStorage.getItem(sessionStorage.getItem("tendn")) == null){
                arrayObj.push(sanPhamMoi);
                localStorage.setItem(dangnhap.innerText, JSON.stringify(arrayObj));
            }else {
                var objArrya = JSON.parse(localStorage.getItem(sessionStorage.getItem("tendn")));
                var found = false;
                objArrya.forEach(function(obj, index) {
                    if(sanPhamMoi.tensp === obj.tensp){
                        obj.soLuong++;
                        localStorage.setItem(sessionStorage.getItem("tendn"), JSON.stringify(objArrya));
                        found = true;
                        return;
                    }
                });
                if (!found){
                    objArrya.push(sanPhamMoi);
                    localStorage.setItem(sessionStorage.getItem("tendn"), JSON.stringify(objArrya));
                }
            }
            layTongSoLuong();
            flag = true;
        }
    });
});


let btnBuyNow = document.getElementById("mualien");
btnBuyNow.addEventListener("click",function (){

    const productImage = document.querySelector(`.product img[data-id="${1}"]`);
    const cart = document.getElementById('cart');
    const productRect = productImage.getBoundingClientRect();
    const cartRect = cart.getBoundingClientRect();

    const productClone = productImage.cloneNode(true);
    productClone.classList.add('fly-to-cart');
    productClone.style.left = `${productRect.left-400}px`;
    productClone.style.top = `${productRect.top}px`;
    productClone.style.position = 'fixed';
    productClone.style.width = `${productRect.width}px`;
    productClone.style.height = `${productRect.height}px`;
    document.body.appendChild(productClone);

    requestAnimationFrame(() => {
        productClone.style.transform = `translate(${(cartRect.left - productRect.left)-200}px, ${(cartRect.top - productRect.top)-300}px) scale(0.1)`;
        productClone.style.opacity = '0';
    });

    let flag = false;
    productClone.addEventListener('transitionend', () => {
        if (!flag) { //dùng để check và cho hàm này được hoạt động 1 lần duy nhất(vì hàm hoạt động 2 lần)
            if(dangnhap.innerHTML === ""){
                alert("You are not logged in, please log in.!");
                const currentUrl = window.location.href;
                const url = new URL(currentUrl);
                const pathAndQuery = url.pathname + url.search;
                sessionStorage.setItem("duongdan", pathAndQuery);
                window.location.href = "/p/login";
                return;
            }else {
                sessionStorage.removeItem("duongdan")
                sessionStorage.setItem("tendn",dangnhap.innerText);
            }

            var sanPhamMoi = {
                gia: giaSo,
                tensp: tensp.textContent,
                linkanh: srcAnh,
                idproductt : idproductstring,
                soLuong: 0
            };

            var arrayObj = []

            if(localStorage.getItem(sessionStorage.getItem("tendn")) == null){
                arrayObj.push(sanPhamMoi);
                localStorage.setItem(dangnhap.innerText, JSON.stringify(arrayObj));
                window.location.href = "/a/cart";
                return;
            }else {
                var objArrya = JSON.parse(localStorage.getItem(sessionStorage.getItem("tendn")));
                var found = false;
                objArrya.forEach(function(obj, index) {
                    if(sanPhamMoi.tensp == obj.tensp){
                        obj.soLuong++;
                        localStorage.setItem(sessionStorage.getItem("tendn"), JSON.stringify(objArrya));
                        found = true;
                        window.location.href = "/a/cart";
                        return;
                    }
                });
                if (!found){
                    objArrya.push(sanPhamMoi);
                    localStorage.setItem(sessionStorage.getItem("tendn"), JSON.stringify(objArrya));
                    window.location.href = "/a/cart";
                    return;
                }
            }
            layTongSoLuong();
            flag = true;
        }
    })
})
