$(document).ready(async function () {
    let listProductForShop =[];
    let currentPage = 1; // page hiện tại
    let perPage = 12; // item number in page
    let totalPage = 0;
    let perProducts = [];
    const getShopByUserId = async () => {
        let shopInfoContainer = $('#shopInfoContainer');
        let shopId = localStorage.getItem('shopId');
        if (!shopId) {
            alert('Shop ID not found in local storage');
            return;
        }

        await axios.get(`/get-shop-by-user-id?shopId=${shopId}`)
            .then(response => {
                shopInfoContainer.html('');
                let responseData = response.data.data;
                let formattedDate = moment(responseData.dateEstablished).format('DD-MM-YYYY');
                let html = `
           <div class="col-3">
                <div class="row rounded-right p-2" style="background-color: rgba(164,162,162,0.42)">
                    <div class="col-4 pr-0">
                        <img class="rounded-circle img-fluid" src="${'/assests/shopImg/' + responseData.logo}" alt="" style="height: 106px; width: 106px; object-fit: contain">
                    </div>
                    <div class="col-8 d-flex align-items-center">
                        <div class="d-inline-block text-truncate" style="max-width: 200px; font-weight: 500">${responseData.nameShop}</div>
                    </div>
                </div>
            </div>
           <div class="col-3 d-flex" style="font-size: 13px">
            <div class="d-flex text-secondary flex-column my-2 w-50 my-4">
                    <div class="flex-grow-1">Shop Owner:</div>
                    <div class="flex-grow-1">Date setting:</div>
                </div>
                <div class="d-flex text-danger flex-column ms-2 w-50 my-4">
                    <div class="flex-grow-1">${responseData.userE.fullname}</div>
                    <div class="flex-grow-1">${formattedDate}</div>
                </div>
            </div>
            <div class="col-2 d-flex" style="font-size: 13px">
                <div class="d-flex text-secondary flex-column my-2 w-50 my-4">
                    <div class="flex-grow-1">Total Product</div>
                    <div class="flex-grow-1">Orders sold within 1 month</div>
                </div>
                <div class="d-flex text-danger flex-column ms-2 w-50 my-4">
                    <div class="flex-grow-1" id="total-product">0 Product</div>
                    <div class="flex-grow-1" id="orders-sold">0 Order</div>
                </div>
            </div>
            <div class="col-2 d-flex" style="font-size: 13px">
                <div class="d-flex text-secondary flex-column my-2 w-50 my-4">
                    <div class="flex-grow-1">Joined</div>
                    <div class="flex-grow-1">Total comments</div>
                </div>
                <div class="d-flex text-danger flex-column ms-2 w-50 my-4">
                    <div class="flex-grow-1" id="joined">0 Date</div>
                    <div class="flex-grow-1" id="total-comments">0 Comment</div>
                </div>
            </div>
            <div class="col-2 d-flex" style="font-size: 13px">
                <div class="d-flex text-secondary flex-column my-2 w-50 my-4">
                    <div class="flex-grow-1">Total product category</div>
                    <div class="flex-grow-1">Total likes of all products</div>
                </div>
                <div class="d-flex text-danger flex-column ms-2 w-50 my-4">
                    <div class="flex-grow-1" id="total-categories">0 Category</div>
                    <div class="flex-grow-1" id="total-likes">0 <i class="fa-solid fa-heart" style="color: #ff0000;"></i></div>
                </div>
            </div>
                    `;
                shopInfoContainer.append(html);
            })
            .catch(error => {
                alert(error);
            });
    };

    getShopByUserId();


    const getCategoryByShopUserShopId = async () => {
        let categoryShopContainer = $('#categoryShopContainer');
        let shopId = localStorage.getItem('shopId');
        if (!shopId) {
            alert('Shop ID not found in local storage');
            return;
        }

        await axios.get(`/get-category-by-shop-user-shopId?shopId=${shopId}`)
            .then(response => {
                categoryShopContainer.html('');
                let responseData = response.data.data;
                responseData.forEach((e, index) => {
                    let html = `
                <div style="margin-left: 10px">
                    <div class="active-category-hover" style="margin-bottom: 10px">
                        <a href="#" class="d-inline-block text-truncate text-decoration-none text-dark button-category" id="${e.category_id} " data-id="${e.category_id}">
                            ${e.category_name}
                        </a>
                    </div>
                </div>
                `;
                    categoryShopContainer.append(html);
                });
                // Gắn sự kiện click cho tất cả các thẻ <a> sau khi chúng được thêm vào DOM
                $('#categoryShopContainer').on('click', 'a', function(event) {
                    event.preventDefault();
                    let categoryId = $(this).data('id');
                    getProductByCategoryAndShopId(categoryId);

                    $('.button-category').removeClass('text-danger').addClass('text-dark');
                    $(this).removeClass('text-dark').addClass('text-danger');
                });
                // Tự động click vào thẻ <a> đầu tiên sau khi các thẻ được thêm vào DOM
                const firstCategory = $('#categoryShopContainer a').first();
                if (firstCategory.length) {
                    firstCategory.click();
                }
            })
            .catch(error => {
                alert(error);
            });
    };

    getCategoryByShopUserShopId();




    const getProductByCategoryAndShopId = async (categoryId) => {
        console.log(categoryId);
        let getProductByCategoryAndShopIdContainer = $('#getProductByCategoryAndShopIdContainer');
        let shopId = localStorage.getItem('shopId');
        if (!shopId) {
            alert('Shop ID not found in local storage');
            return;
        }

        await axios
            .get('/api-public-getListProductByCategoryAndShopId', {
                params: {
                    categoryId: categoryId,
                    shopId: shopId
                }
            })
            .then(response => {
                getProductByCategoryAndShopIdContainer.html('');
                // let responseData = response.data.data;
                listProductForShop = response.data.data;
                totalPage = Math.ceil(listProductForShop.length / perPage);
                updatePage();
            })
            .catch(error => {
                alert(error);
            });
    };
    function updatePage(){
        let start = (currentPage - 1) * perPage;
        let end = start + perPage;
        perProducts = listProductForShop.slice(start, end);
        $("#page-info-shop").text(currentPage + "/" + totalPage);
        displayProducts(perProducts);
    }

    //handler event button
    $('.button').click(function(){
        $('.button').removeClass("btn-danger");
        $(this).addClass("btn-danger");
    });
    $('#handlerPageNumberPrev').click(function() {
        handlerPageNumberPrev();
    });
    $('#handlerPageNumberNext').click(function() {
        handlerPageNumberNext();
    });
    $('#moinhat').click(function() {
        fiterSanPhamPhoBienNhat();
    });
    $('#phobien').click(function() {
        filterMostPurchasedProducts();
    });
    $('#banchay').click(function() {
        filterBestSellingProducts();
    });
    $('#selectSort').change(function (){
        handleSelectChange(this);
    })

    //handler function pagination
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

    //handler function Sort_products
    function culculateFinalPrice(product){
        if(product.sale != null){
            return product.price - (product.sale.salePercent / 100 * product.price)
        }
        return product.price
    }
    function filterMostPurchasedProducts() {
        let sortedProducts = listProductForShop.sort((a, b) => b.rateCount - a.rateCount);
        let mostPurchasedProducts = sortedProducts.slice(0, 12);
        displayProducts(mostPurchasedProducts);
    }
    function filterBestSellingProducts() {
        let sortedBestSellingProducts = listProductForShop.sort((a, b) => b.sold - a.sold);
        let bestSellingProducts = sortedBestSellingProducts.slice(0, 12);
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
        let sortedProducts = listProductForShop.sort((a, b) => culculateFinalPrice(a) - culculateFinalPrice(b));
        let sortedProductss = sortedProducts.slice(0, 12);
        displayProducts(sortedProductss);
    }
    function filterProductsByPriceHighToLow() {
        let sortedProducts = listProductForShop.sort((a, b) => culculateFinalPrice(b) - culculateFinalPrice(a));
        let sortedProductss = sortedProducts.slice(0, 12);
        displayProducts(sortedProductss);
    }
    function fiterSanPhamPhoBienNhat(){
        let sortedProducts = listProductForShop.sort((a, b) => b.productId - a.productId);
        let newestProducts = sortedProducts.slice(0, 12);
        console.log(sortedProducts)
        displayProducts(newestProducts);
    }

    // function render products
    function displayProducts(products) {
        let resultsContainer = $('#getProductByCategoryAndShopIdContainer');
        resultsContainer.empty();
        products.forEach(function(product,index) {
            let badgeHtml = '';
            if (index < 6) {
                badgeHtml = `<span class="badge text-bg-warning text-end">Top ${index + 1}</span>`;
            }
            let productHtml = `
                <div class="col-lg-2 col-md-3 col-sm-4">
                    <div class="card rounded-3 shadow border-0 text-center d-product justify-content-center mb-3" style="overflow: hidden;">
                        <a href="/p/product?slug_url=${product.slugUrl}" class="stretched-link">
                        
                            <div style="position: relative">
                                <div style="position: absolute; ">
                                    ${product.sale ? `<span class="badge text-bg-danger">${product.sale.salePercent}%</span>` : ''}
                                    
                                </div>
                                <div style="position: absolute; top: 0px; right: 0px;">
                                    ${badgeHtml}
                                </div>
                                <div class="img-container">
                                    <img src="../media/${product.pictures.split(',')[0]}" class="img-fluid mt-2 mb-3" alt="">
                                </div>
                                <div class="w-100">
                                    <span class="d-inline-block text-truncate" style="max-width: 90%;">${product.productName}</span>
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
                                    <div class="text-white fw-bolder">Buy now</div>
                                </div>
                            </div>
                            
                        </a>
                    </div>
                </div>
            `;
            resultsContainer.append(productHtml);
        });
    }

});


