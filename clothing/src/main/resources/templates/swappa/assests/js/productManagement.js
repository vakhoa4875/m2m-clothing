class productManagement{

    constructor() {
        this.self = this;
    }

    loadInit = async () => {
        await this.callAllUsers()
        // await this.getProductById();
    }
    callAllUsers = async () =>{
        await $.ajax({
            url: 'http://localhost:8083/allproductapi',
            get: 'GET',
            success: function (data) {
                const list = $('#listproduct');
                let htmlString = '';
                $.each(data, function (index, item) {
                    htmlString += `
                        <tr>
                            <td class="max-width-column">
                                <div class="d-flex py-1 justify-content-center">
                                    <div>
                                        <!--Hình của sản phầm... 1 hình avatar product-->
                                        <img src="" class="avatar avatar-sm border-radius-lg" alt="">
                                    </div>
                                </div>
                            </td>
                            <td class="align-middle text-center text-sm">
                                <div class="d-flex w-100 flex-column justify-content-center">
                                    <h6 class="mb-0 text-sm">${item.productName}</h6>
                                </div>
                            </td>
                            <td class="align-middle text-center text-sm">
                                <span class="text-secondary text-xs font-weight-bold">${item.price}</span>
                            </td>
                            <td class="align-middle text-center">
                                <span class="text-secondary text-xs font-weight-bold">${item.quantity}</span>
                            </td>
        
                            <td class="align-middle text-center">
                                <button class="btn border-0 rounded btn-outline-info"
                                        data-bs-toggle="modal" data-bs-target="#exampleModalUpdateProduct" onclick="productservices.getProductById('${item.slugUrl}')">
                                    <i class="fa-solid fa-pen-to-square"></i></button>
                                <button class="border border-0 rounded btn-outline-danger"
                                        data-bs-toggle="modal" data-bs-target="#exampleModalDeleteProduct">
                                    <i class="fa-solid fa-trash"></i></button>
                            </td>
                        </tr>
                    `;
                });
                list.append(htmlString);
            },
            error: function (xhr, status, error) {
                console.log(error);
            }
        });
    }

    getProductById = (slug_url_product) => {
        $.ajax({
            url: 'http://localhost:8083/findbyproductidapi',
            method: 'GET',
            data: {
                "slug_url": slug_url_product
            },
            success: function (data) {
                let imagesString = data.pictures;
                let imagesArray = imagesString.split(",");
                let modelProduct = $('#updateProductModalBody');
                modelProduct.empty();
                let html = '';
            // language=HTML format=false
                html += `
                    <div class="row">
                        <div class="col-12">
                            <div class="mb-2 ">
                                <label for="updID" class="form-label">ID</label>
                                <input type="text" class="form-control ps-3" id="updID"
                                aria-describedby="basic-addon3 basic-addon4" style="border: 1px solid #d2d6da;" value="${data.productId}">
                            </div>
                            <div class="mb-2 ">
                                <label for="updNameProduct" class="form-label">Name</label>
                                <input type="text" class="form-control ps-3" id="updNameProduct"
                                aria-describedby="basic-addon3 basic-addon4" style="border: 1px solid #d2d6da;" value="${data.productName}">
                            </div>
                            <div class="mb-2 ">
                                <label for="updPriceProduct" class="form-label">Price</label>
                                <input type="text" class="form-control ps-3" id="updPriceProduct"
                                aria-describedby="basic-addon3 basic-addon4" style="border: 1px solid #d2d6da;" value="${data.price}" >
                            </div>
                            <div class="mb-2 ">
                                <label for="updQuantityProduct" class="form-label">Quantity</label>
                                <input type="text" class="form-control ps-3" id="updQuantityProduct"
                                aria-describedby="basic-addon3 basic-addon4" style="border: 1px solid #d2d6da;" value="${data.quantity}">
                            </div>
                            <div class="mb-2">
                                <label for="updDescriptionProduct" class="form-label">Description</label>
                                <textarea class="form-control" id="updDescriptionProduct" rows="3" >${data.description}</textarea>
                            </div>
                            <div class="mb-2">
                                <div class="container " style="">
                                    <div id="loadImages" class="row">
                                    </div>
                            <!--==============-->
                                </div>
                            </div>
                            <div class="mb-2 ">
                                <div class=" d-flex justify-content-center embed-responsive embed-responsive-16by9">
                                    <video  id="previewVideo" class="embed-responsive-item">  <source src="../media/${data.videos}"> </video>
                                </div>
                                <div class="col-4">
                                    <div class="text-center my-3" style="margin-right: 10px;">
                                        <input type="file" class="form-control" id="previewVideo1" accept="video/*" onchange="productservices.previewVideo(event)">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                `
                modelProduct.append(html);
                productservices.forImages(imagesString);
            },
            error: (error) => {
                console.log("error" + error)
            }
        })
    }

    forImages = (imagesString) =>{
        let imagesArray = imagesString.split(',');
        let modelProduct = $('#loadImages');
        modelProduct.empty();

        if (imagesArray.length > 0) {
            for (let i = 0; i < imagesArray.length; i++) {
                let imageUrl = imagesArray[i].trim();
                let imgHtml = `
                    <div class="col-4">
                        <!--  ảnh  -->
                        <div class="  d-flex justify-content-center">
                            <img src="../media/${imagesArray[i]}" class="avatar me-3 border-radius-lg img-fluid"
                             id="updfileInput${i + 1}" alt="Ảnh đã chọn">
                        </div>
                        <!--  file input ảnh -->
                        <div class="text-center my-3" style="margin-right: 10px;">
                            <input type="file" class="form-control fileInputClass" id="fileInput${i + 1}" accept="image/*" onchange="productservices.previewImage(event, 'updfileInput${i + 1}')">
                        </div>
                    </div>
                `
                modelProduct.append(imgHtml);
            }
        }
    }

    previewImage = (event, imageId) => {
        var reader = new FileReader();
        reader.onload = function() {
        var output = document.getElementById(imageId);
        output.src = reader.result;
    }
        reader.readAsDataURL(event.target.files[0]);
    }

    previewVideo = (event) => {
        var reader = new FileReader();
        reader.onload = function() {
        var video = document.getElementById('previewVideo');
        video.src = reader.result;
    }
        reader.readAsDataURL(event.target.files[0]);
    }
    inSertProduct = async () => {
        let product = this.createProductObject();
        await $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: 'http://localhost:8083/insertProduct',
            data: JSON.stringify(product),
            dataType: 'text',
            processData: false,
            success: function (data) {
                console.log(data)
                alert("Insert product success");
            },
            error: function (xhr, status, error) {
                console.log(xhr.responseText);
                console.log(status);
                console.log(error);
                alert("Đã xảy ra lỗi khi thêm sản phẩm.");
            }
        })
        location.reload();
    }
    getFileNameFromPath = (path) => {
        let parts = path.split("\\");
        return parts[parts.length - 1].trim();
    }
    createProductObject = () => {
        let name = $('#insNameProduct').val();
        let price = $('#insPriceProduct').val();
        let quantity = $('#insQuantityProduct').val();
        let description = $('#inDescriptionProduct').val();
        let img1 = $('#fileInput1').val()
        let img2 = $('#fileInput2').val();
        let img3 = $('#fileInput3').val();
        let videos = $('#fileInput4').val();

        let fileName1 = this.getFileNameFromPath(img1);
        let fileName2 = this.getFileNameFromPath(img2);
        let fileName3 = this.getFileNameFromPath(img3);
        let fileNameVideo = this.getFileNameFromPath(videos)

        // Gộp các tên tệp lại thành một chuỗi
        let imagesString = [fileName1,fileName2,fileName3].join(",");
        let slug = this.convertToSlug(name);

        let product = {
            "name": name,
            "price": price,
            "quantity": quantity,
            "description": description,
            "pictures": imagesString,
            "videos": fileNameVideo,
            "slug": slug
        }
        return product;
    }
    convertToSlug = (str) => {
        str = str.toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "");
        return str.replace(/\s+/g, '-');
    }

    updateProduct = async  () => {
        let productUpdate = this.createProductObjectUpdate();
        console.log(productUpdate)
        // await $.ajax({
        //     type: 'POST',
        //     contentType: 'application/json',
        //     url: 'http://localhost:8083/updateProduct',
        //     data: JSON.stringify(product),
        //     dataType: 'text',
        //     processData: false,
        //     success: function (data) {
        //         console.log(data)
        //         alert("update product success");
        //     },
        //     error: function (xhr, status, error) {
        //         console.log(xhr.responseText);
        //         console.log(status);
        //         console.log(error);
        //         alert("Đã xảy ra lỗi khi sua sản phẩm.");
        //     }
        // })
        // location.reload();
    }
    createProductObjectUpdate = async () => {
        let name = $('#updNameProduct').val();
        let price = $('#updPriceProduct').val();
        let quantity = $('#updQuantityProduct').val();
        let description = $('#updDescriptionProduct').val();
        let img1 = $('#fileInput1').val()
        let img2 = $('#fileInput2').val();
        let img3 = $('#fileInput3').val();
        let videos = $('#previewVideo1').val();
        let id = $('#updID').val();

        console.log(img1);
        console.log(img2);
        console.log(img3);

        let fileName1 = this.getFileNameFromPath(img1);
        let fileName2 = this.getFileNameFromPath(img2);
        let fileName3 = this.getFileNameFromPath(img3);
        let fileNameVideo = this.getFileNameFromPath(videos)

        // Gộp các tên tệp lại thành một chuỗi
        let imagesString = [fileName1,fileName2,fileName3].join(",");
        let slug = this.convertToSlug(name);

        let imagesString1 = "";
        $(document).on('change', '.fileInputClass', function () { // Thêm class cho input
            let img1 = $(this).val();
            let fileName1 = this.getFileNameFromPath(img1);
            imagesString += fileName1 + ",";
        });

        let product = {
            "name": name,
            "price": price,
            "quantity": quantity,
            "description": description,
            "pictures": imagesString,
            "videos": fileNameVideo,
            "id": id
        }
        return product;
    }
}