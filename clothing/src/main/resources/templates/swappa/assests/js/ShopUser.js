$(document).ready(function () {

    const getShopByUserEmail = async () => {
        let shopInfoContainer = $('#shopInfoContainer');
        await axios.get('/get-shop-by-user-email')
            .then(response => {
                shopInfoContainer.html('');
                let responseData = response.data.data;
                let formattedDate = moment(responseData.dateEstablished).format('DD-MM-YYYY');
                let html = `
           <div class="col-3">
                <div class="row rounded-right p-2" style="background-color: rgba(164,162,162,0.42)">
                    <div class="col-4 pr-0" style="">
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
                    `
                shopInfoContainer.append(html);
            })
            .catch(error => {
                alert(error);
            })
    }
    getShopByUserEmail();

    const getCategoryByShopEmail = async () => {
        let categoryShopContainer = $('#categoryShopContainer');
        await axios.get('/get-category-by-shop-user-email')
            .then(response => {
                categoryShopContainer.html('');
                let responseData = response.data.data;
                responseData.forEach((e, index) => {
                    let html = `
                    <div style="margin-left: 10px">
                        <div class="active-category-hover" style="margin-bottom: 10px">
                            <a href="#" class="d-inline-block text-truncate text-decoration-none" id="${e.category_id}" data-id="${e.category_id}">
                                ${e.category_name}
                            </a>
                        </div>
                    </div>
                    `
                    categoryShopContainer.append(html);
                })
                // Gắn sự kiện click cho tất cả các thẻ <a> sau khi chúng được thêm vào DOM
                $('#categoryShopContainer').on('click', 'a', function(event) {
                    event.preventDefault();
                    let categoryId = $(this).data('id');
                    getProductByCategoryAndShopEmail(categoryId);
                });
                // Tự động click vào thẻ <a> đầu tiên sau khi các thẻ được thêm vào DOM
                const firstCategory = $('#categoryShopContainer a').first();
                if (firstCategory.length) {
                    firstCategory.click();
                }
            })
            .catch(error => {
                alert(error);
            })
    }
    getCategoryByShopEmail();


    const getProductByCategoryAndShopEmail = async (categoryId) => {
        let getProductByCategoryAndShopEmailContainer = $('#getProductByCategoryAndShopEmailContainer');
        await axios
            .get('/api-public-getListProductByCategoryAndShopEmail',{
                params: {
                    categoryId: categoryId
                }
            })
            .then(response => {
                getProductByCategoryAndShopEmailContainer.html('');
                let responseData = response.data.data;


                responseData.forEach((e, index) => {
                    let picture = e.pictures.split(",")[0];
                    let originalPrice = e.price;
                    let salePercent = e.sale.salePercent;
                    let priceAfterDiscount = originalPrice * (1 - salePercent / 100);
                    let html = `
                    <div class="col-2 d-flex flex-column justify-content-around align-items-center mb-3"
                         style="overflow: hidden; position: relative; padding-top: 30px;">
                        <div style="position: absolute; top: 0px; left: 15px">
                            <span class="badge rounded-pill text-bg-warning "
                                  style="background-color: #fc1e1e; color: white; padding: 6px">New</span>
                        </div>
                        <div style="position: absolute; top: 0px; right: 15px">
                            <span class="badge rounded-pill text-bg-warning "
                                  style="background-color: #ababab; color: black; padding: 6px">${e.sale.salePercent}%</span>
                        </div>
                        <div style="width: 100%; height: 150px; display: flex; justify-content: center; align-items: center;">
                            <a href="/product?slug_url=${e.slugUrl}" style="height: 200px">
                                <img src="../media/${picture}" alt="" class="img-fluid"
                                     style="object-fit: revert; width: 100%; height: 100%;">
                            </a>
                        </div>

                        <div style="margin-top: 20px">
                            <div class="d-inline-block text-truncate p-1" style="font-weight: 500; max-width: 150px;">
                                ${e.productName}
                            </div>
                            <div class="d-flex">
                                <div style="color: red">
                                    $${priceAfterDiscount.toFixed(2)}
                                </div>
                                <div style="color: gray; margin-left: 5px;">
                                    <del style="font-size: 13px;">$${e.price}</del>
                                </div>
                            </div>
                        </div>
                    </div>
                    `
                    getProductByCategoryAndShopEmailContainer.append(html);
                })
            })
            .catch(error => {
                alert(error);
            })
    }




});