var dangnhap = sessionStorage.getItem("tendn");
var activeLogin = $('#activeLogin').text();

function getIdSanPham (int,name,sale,price,anh,sale_active) {

    //hiệu ứng add item
    const product = event.target.previousElementSibling;
    const productClone = product.cloneNode(true);
    const cart = document.getElementById('cart');
    const productRect = product.getBoundingClientRect();
    const cartRect = cart.getBoundingClientRect();


    productClone.classList.add('fly-to-cart');
    productClone.style.left = `${productRect.left}px`;
    productClone.style.top = `${productRect.top}px`;
    document.body.appendChild(productClone);

    requestAnimationFrame(() => {
        productClone.style.transform = `translate(${(cartRect.left - productRect.left) -170}px, ${(cartRect.top - productRect.top)-100}px) scale(0.1)`;
        productClone.style.opacity = '0';

    });

    let flag = false;
    productClone.addEventListener('transitionend', () => {
        if (!flag) { //dùng để check và cho hàm này được hoạt động 1 lần duy nhất(vì hàm hoạt động 2 lần)
            addItem();
            flag = true;
        }
    });

    function addItem(){
        if(dangnhap == "" || activeLogin == ""){
            alert("Có vẻ như bạn chưa đăng nhập, vui lòng đăng nhập!");
            window.location.href = "/p/login";
        }

        if (sale_active == "1"){
            var sanPhamMoi = {
                gia: parseFloat(sale),
                tensp: name,
                linkanh: anh,
                idproductt : int,
                soLuong: 1
            };
        }else {
            var sanPhamMoi = {
                gia: parseFloat(price),
                tensp: name,
                linkanh: anh,
                idproductt : int,
                soLuong: 1
            };
        }

        var arrayObj = []

        if(localStorage.getItem(sessionStorage.getItem("tendn")) == null){
            arrayObj.push(sanPhamMoi);
            localStorage.setItem(activeLogin, JSON.stringify(arrayObj));
            window.location.href = "/a/cart";
        }else {
            var objArrya = JSON.parse(localStorage.getItem(sessionStorage.getItem("tendn")));
            var found = false;
            objArrya.forEach(function(obj, index) {
                if(sanPhamMoi.tensp === obj.tensp){
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
            }
            layTongSoLuong();
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
    }

}