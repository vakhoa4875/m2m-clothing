$(document).ready(async function(){
    await loadAllProduct()
})
let productsByCategory = [];
let products = [];
let currentPage = 1; // page hiện tại
let perPage = 12; // item number in page
let totalPage = 0;
let perProducts = [];
let pageTam=[];
var dangnhap = sessionStorage.getItem("tendn");
var activeLogin = $('#activeLogin').text();
let url = new URL(window.location.href);
let keyword = url.searchParams.get("key_search");
let type = url.searchParams.get("type_search");
let pathComponent = window.location.pathname.split('/')[2];
let categoryType = pathComponent === undefined ? '' : decodeURIComponent(pathComponent);
function loadAllProduct () {
    return axios.get("/allproductapi")
        .then(response => {
            products = response.data;
            let categoryId = window.location.pathname.split('/')[2];
            fiterByCategory(categoryId)
            if(keyword && type){
                searchByKeywordAndCategory()
            }
        })
}
//handle search funtion
function searchByKeywordAndCategory(){
    let filteredByCategory = type === 'All Product' ? products : products.filter(product => product.category.category_name === type);
    let filteredByKeyword = filteredByCategory.filter(product => product.name.toLowerCase().includes(keyword.toLowerCase()));
    pageTam = filteredByKeyword;
    productsByCategory = filteredByKeyword;
    totalPage = Math.ceil(filteredByKeyword.length / perPage);
    updatePage()
}
function updatePage(){
    let start = (currentPage - 1) * perPage;
    let end = start + perPage;
    perProducts = pageTam.slice(start, end);
    $("#page-info").text(currentPage + "/" + totalPage);
    displayProducts(perProducts);
}

function handlerPageNumberNext (){
    if(totalPage !== currentPage){
        currentPage += 1;
    }
    updatePage();
}
function handlerPageNumberPrev (){
    if(currentPage !== 1){
        currentPage -= 1;
    }
    updatePage();
}
function filterMostPurchasedProducts() {
    let sortedProducts = productsByCategory.sort((a, b) => b.rateCount - a.rateCount);
    let mostPurchasedProducts = sortedProducts.slice(0, 12);
    pageTam = sortedProducts;
    totalPage = Math.ceil(sortedProducts.length / perPage);
    productsByCategory = sortedProducts;
    updatePage()
}
function filterBestSellingProducts() {
    let sortedProducts = productsByCategory.sort((a, b) => b.sold - a.sold);
    let bestSellingProducts = sortedProducts.slice(0, 12);
    pageTam = sortedProducts;
    totalPage = Math.ceil(sortedProducts.length / perPage);
    productsByCategory = sortedProducts;
    updatePage()
}
function handleSelectChange(selectElement) {
    var selectedOption = selectElement.value;
    if (selectedOption === 'lowToHigh') {
        filterProductsByPriceLowToHigh();
    } else if (selectedOption === 'highToLow') {
        filterProductsByPriceHighToLow();
    }
}
function culculateFinalPrice(product){
    if(product.sale != null){
        return product.price - (product.sale.salePercent / 100 * product.price)
    }
    return product.price
}
function filterProductsByPriceLowToHigh() {
    let sortedProducts = productsByCategory.sort((a, b) => culculateFinalPrice(a) - culculateFinalPrice(b));
    let sortedProductss = sortedProducts.slice(0, 12);
    pageTam = sortedProducts;
    totalPage = Math.ceil(sortedProducts.length / perPage);
    productsByCategory = sortedProducts;
    updatePage()
}
function filterProductsByPriceHighToLow() {
    let sortedProducts = productsByCategory.sort((a, b) => culculateFinalPrice(b) - culculateFinalPrice(a));
    let sortedProductss = sortedProducts.slice(0, 12);
    pageTam = sortedProducts;
    totalPage = Math.ceil(sortedProducts.length / perPage);
    productsByCategory = sortedProducts;
    updatePage()
}
function fiterSanPhamPhoBienNhat(){
    let sortedProducts = productsByCategory.sort((a, b) => b.productId - a.productId);
    let newestProducts = sortedProducts.slice(0, 12);
    pageTam = sortedProducts;
    totalPage = Math.ceil(sortedProducts.length / perPage);
    productsByCategory = sortedProducts;
    updatePage()
}
function fiterByCategory(categoryId){
    if(!categoryId){
        pageTam = products;
        totalPage = Math.ceil(products.length / perPage);
        productsByCategory = products;
        updatePage()
    }else{
        let categoryName = decodeURIComponent(categoryId);
        let filterProducts = products.filter(product => product.category.category_name === categoryName);
        productsByCategory = filterProducts;
        pageTam = productsByCategory
        totalPage = Math.ceil(productsByCategory.length / perPage);
        updatePage()
    }
}
function displayProducts(products) {
    let resultsContainer = $('#search-results');
    resultsContainer.empty();
    products.forEach(function(product) {
        let productHtml = `
                <div class="col-lg-2 col-md-3 col-sm-4">
                    <div class="card rounded-3 shadow border-0 text-center d-product justify-content-center mb-3" style="overflow: hidden;">
                        <a href="/product?slug_url=${product.slug}" class="product">
                            <div style="position: absolute">
                                ${product.sale ? `<span class="badge text-bg-danger">${product.sale.salePercent}%</span>` : ''}
                            </div>
                            <div class="img-container">
                                <img src="../media/${product.pictures.split(',')[0]}" class="img-fluid mt-2 mb-3" alt="">
                            </div>
                            <div class="w-100">
                                <span class="d-inline-block text-truncate" style="max-width: 90%;">${product.name}</span>
                            </div>
                            <div class="w-100">
                                <div class="d-flex justify-content-around">
                                    ${product.sale ? `
                                        <del>$${product.price}</del>
                                        <span style="color:#c07d4b; font-weight: bolder">
                                           $${(product.price - (product.sale.salePercent / 100 * product.price)).toFixed(2)}
                                        </span>` : `$${product.price}`}
                                </div>
                            </div>
                            <div class="rounded-bottom-3" style="background-color: rgb(224, 150, 150);">
                                <div class="text-white fw-bolder">Buy now</div>
                            </div>
                        </a>
                <button class="rounded-bottom-3 w-100 fw-bolder text-white" onclick="getIdSanPham(${product.productId}, '${product.name}', '${product.sale ? (product.price - (product.sale.salePercent / 100 * product.price)).toFixed(2) : (product.price)} ', '${product.price}','../media/${product.pictures.split(',')[0]}', '${product.sale ? "1" : "0"}')" style="background-color: rgb(224, 150, 150); border-color: rgba(0,0,0,0)" >Buy Now</button>
                    </div>
                </div>
            `;
        resultsContainer.append(productHtml);
    });
}

function getIdSanPham (int,name,sale,price,anh,sale_active) {
    console.log(sale_active)
    //hiệu ứng add item
    const product = event.target.previousElementSibling;
    const productClone = product.cloneNode(true);
    const cart = document.getElementById('cart');
    console.log(cart)
    const productRect = product.getBoundingClientRect();
    console.log(productRect)
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
        if(dangnhap == ""){
            alert("Có vẻ như bạn chưa đăng nhập, vui lòng đăng nhập!");
            window.location.href = "/loginacount";
            return;
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
            localStorage.setItem(sessionStorage.getItem("tendn"), JSON.stringify(arrayObj));
            window.location.href = "/giohang";
        }else {
            var objArrya = JSON.parse(localStorage.getItem(sessionStorage.getItem("tendn")));
            var found = false;
            objArrya.forEach(function(obj, index) {
                if(sanPhamMoi.tensp === obj.tensp){
                    obj.soLuong++;
                    localStorage.setItem(sessionStorage.getItem("tendn"), JSON.stringify(objArrya));
                    found = true;
                    window.location.href = "/giohang";
                    return;
                }
            });
            if (!found){
                objArrya.push(sanPhamMoi);
                localStorage.setItem(sessionStorage.getItem("tendn"), JSON.stringify(objArrya));
                window.location.href = "/giohang";
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
