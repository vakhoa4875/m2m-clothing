$(document).ready( async function(){
    await loadAllProduct()
})
let products = [];
let voucherCount = 0;

function loadAllProduct(){
     axios.get("/allproductapi")
        .then(response => {
            products = response.data;
            renderHtmlHome(products,'type_product_outerwear')
            handleTypeProduct();
            loadAllVoucher();

        })

}

function loadAllVoucher(){
    return axios.get("/api-public/vouchers/getAllVouchers")
        .then(response =>{
            voucherCount = response.data.length;
            handleQuantityProductAndVouncher()
        })
}
function handleQuantityProductAndVouncher(){
    $('#productCount').text(products.length + " approved listings");
    $('#voucherCount').text(voucherCount + " approved listings")
}

function handleTypeProduct(){
    let productsTypeOuterwear = products.filter(product => product.category.category_name === 'Outerwear');
    renderHtmlHome(productsTypeOuterwear,'type_product_outerwear')
    let productsTypeTops = products.filter(product => product.category.category_name === 'Tops');
    renderHtmlHome(productsTypeTops,'type_product_tops')
    let productsTypeBottoms = products.filter(product => product.category.category_name === 'Bottoms');
    renderHtmlHome(productsTypeBottoms,'type_product_bottoms')
    let productsTypeJewelsAccessories = products.filter(product => product.category.category_name === 'Jewels & Accessories');
    renderHtmlHome(productsTypeJewelsAccessories,'type_product_Jewels_Accessories')
    let productsTypeHeadwear = products.filter(product => product.category.category_name === 'Headwear');
    renderHtmlHome(productsTypeHeadwear,'type_product_Jewels_Headwear')
    let productsTypeFootwear = products.filter(product => product.category.category_name === 'Footwear');
    renderHtmlHome(productsTypeFootwear,'type_product_Jewels_Footwear')
}

function renderHtmlHome(products,idElementRender){
    let resultsContainer = $(`#${idElementRender}`);
    resultsContainer.empty();
    products.forEach(function(product) {
        let productHtml = `
            <div class="col-lg-2 col-md-3 col-sm-4 me-3">
                <div class="card rounded-3 shadow border-0 text-center d-product justify-content-center mb-3" style="overflow: hidden;">
                    <a href="/p/product?slug_url=${product.slug}" class="stretched-link">
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
                                        $${product.price - (product.sale.salePercent / 100 * product.price)}
                                    </span>` : `$${product.price}`}
                            </div>
                        </div>
                        <div class="rounded-bottom-3" style="background-color: rgb(224, 150, 150);">
                            <div class="text-white fw-bolder">Buy now</div>
                        </div>
                    </a>
                </div>
            </div>   
        `;
        resultsContainer.append(productHtml);
    });
}
