$(document).ready(async function(){
    await loadAllProduct()
})
let products =[]
function loadAllProduct () {
    return axios.get("/allproductapi")
        .then(response => {
            products = response.data;
            console.log(products)
            let categoryId = window.location.pathname.split('/')[2];
            fiterByCategory(categoryId)
        })
}
function filterMostPurchasedProducts() {
    let sortedProducts = products.sort((a, b) => b.rateCount - a.rateCount);
    let mostPurchasedProducts = sortedProducts.slice(0, 12);
    displayProducts(mostPurchasedProducts);
}
function filterBestSellingProducts() {
    let sortedProducts = products.sort((a, b) => b.sold - a.sold);
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
function filterProductsByPriceLowToHigh() {
    let sortedProducts = products.sort((a, b) => a.price - b.price);
    displayProducts(sortedProducts);
}
function filterProductsByPriceHighToLow() {
    let sortedProducts = products.sort((a, b) => b.price - a.price);
    displayProducts(sortedProducts);
}
function fiterSanPhamPhoBienNhat(){
    let sortedProducts = products.sort((a, b) => b.productId - a.productId);
    let newestProducts = sortedProducts.slice(0, 12);
    displayProducts(newestProducts);
}
function fiterByCategory(categoryId){
    console.log(categoryId)
    if(!categoryId){
        displayProducts(products);
    }else{
        console.log(products)
        let filterProducts = products.filter(product => product.category.category_id === Number(categoryId));
        displayProducts(filterProducts);
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
