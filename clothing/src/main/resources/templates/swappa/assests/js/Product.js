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
function loadAllProduct () {
    return axios.get("/allproductapi")
        .then(response => {
            products = response.data;
            console.log(products)
            let categoryId = window.location.pathname.split('/')[2];
            fiterByCategory(categoryId)
        })
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
    displayProducts(mostPurchasedProducts);
}
function filterBestSellingProducts() {
    let sortedProducts = productsByCategory.sort((a, b) => b.sold - a.sold);
    let bestSellingProducts = sortedProducts.slice(0, 12);
    displayProducts(bestSellingProducts);
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
    displayProducts(sortedProductss);
}
function filterProductsByPriceHighToLow() {
    let sortedProducts = productsByCategory.sort((a, b) => culculateFinalPrice(b) - culculateFinalPrice(a));
    let sortedProductss = sortedProducts.slice(0, 12);
    displayProducts(sortedProductss);
}
function fiterSanPhamPhoBienNhat(){
    let sortedProducts = productsByCategory.sort((a, b) => b.productId - a.productId);
    let newestProducts = sortedProducts.slice(0, 12);
    displayProducts(newestProducts);
}
function fiterByCategory(categoryId){
    console.log(categoryId)
    if(!categoryId){
        pageTam = products;
        totalPage = Math.ceil(products.length / perPage);
        productsByCategory = products;
        updatePage()
    }else{

        console.log(products)
        let filterProducts = products.filter(product => product.category.category_id === Number(categoryId));
        productsByCategory = filterProducts;
        pageTam = productsByCategory
        totalPage = Math.ceil(productsByCategory.length / perPage);
        console.log(productsByCategory)
        updatePage()
    }
}
function displayProducts(products) {
    let resultsContainer = $('#search-results');
    resultsContainer.empty();
    console.log(123)
    products.forEach(function(product) {
        let productHtml = `
                <div class="col-lg-2 col-md-3 col-sm-4">
                    <div class="card rounded-3 shadow border-0 text-center d-product justify-content-center mb-3" style="overflow: hidden;">
                        <a href="/product?slug_url=${product.slug}" class="stretched-link">
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
                                        <del>${product.price}</del>
                                        <span style="color:#c07d4b; font-weight: bolder">
                                            ${product.price - (product.sale.salePercent / 100 * product.price)}
                                        </span>` : `${product.price}`}
                                </div>
                            </div>
                            <div class="rounded-bottom-3" style="background-color: rgb(224, 150, 150);">
                                <div class="text-white fw-bolder">Mua ngay</div>
                            </div>
                        </a>
                    </div>
                </div>
            `;
        resultsContainer.append(productHtml);
    });
}
