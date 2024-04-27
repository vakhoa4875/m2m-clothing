class productManagement{

    constructor() {
        this.self = this;

    }
    loadInit = async () => {
        await this.callAllUsers()
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
                                    <h6 class="mb-0 text-sm">${item.name}</h6>
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
                                        data-bs-toggle="modal" data-bs-target="#exampleModalUpdateProduct" onclick="productservices.getProductById('${item.slug}')">
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
                let modelProduct = $('#updateProductModalBody');
                console.log(data)
                modelProduct.empty();
                let html = '';
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
                                aria-describedby="basic-addon3 basic-addon4" style="border: 1px solid #d2d6da;" value="${data.name}">
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
                                    <video  id="previewVideoUpd" class="embed-responsive-item">  <source src="../media/${data.videos}"> </video>
                                </div>
                                <div class="col-4">
                                    <div class="text-center my-3" style="margin-right: 10px;">
                                        <input type="file" class="form-control" id="previewVideo1" accept="video/*" onchange="productservices.previewVideo(event,'previewVideoUpd')">
                                        <label id="nameVideo" style="display: none ">${data.videos}</label>
                                    </div>
                                </div>
                                <select class="form-select w-auto" aria-label="Default select example" id="formSelect">
                                    <option class="form-select" value="1">Outerwear</option>
                                    <option class="form-select" value="2">Tops</option>
                                    <option class="form-select" value="3">Bottoms</option>
                                    <option class="form-select" value="4">Jewels & Accessories</option>
                                    <option class="form-select" value="5">Headwear</option>
                                    <option class="form-select" value="6">Footwear</option>
                                </select>
                            </div>
                        </div>
                    </div>
                `
                let categoryid = data.category;
                modelProduct.append(html);
                document.querySelectorAll('.form-select').forEach(option =>{
                    if(option.value === categoryid.toString()){
                        option.selected = true;
                    }else{
                        option.selected = false;
                    }
                })
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
                            <input type="file" class="form-control " id="fileInput${i + 1}" accept="image/*" onchange="productservices.previewImage(event, 'updfileInput${i + 1}')">
                            <label id="nameImage${i + 1}" style="display: none">${imagesArray[i]}</label>
                        </div>
                    </div>
                `
                modelProduct.append(imgHtml);
            }
        }
    }

    fileImg3;
    fileImg2;
    fileImg1;
    previewImage = async (event, imageId) => {
        var reader = new FileReader();
        console.log(imageId);
        var self = this;
        var inputId = imageId.replace('previewImage', 'fileInput'); // Tạo id của input tương ứng với hình ảnh

        // Lấy file từ input tương ứng
        var file = document.getElementById(inputId).files[0];

        // Kiểm tra xem có file được chọn không
        if (file) {
            reader.onload = function() {
                var output = document.getElementById(imageId);
                output.src = reader.result;

                // var labelId = imageId.replace('previewImage', 'nameImage');
                // var label = document.getElementById(labelId);
                // label.textContent = file.name; // Sử dụng file.name thay vì event.target.files[0].name

                if (imageId === 'previewImage1') {
                    self.fileImg1 = reader.result;
                } else if (imageId === 'previewImage2') {
                    self.fileImg2 = reader.result;
                } else if (imageId === 'previewImage3') {
                    self.fileImg3 = reader.result;
                }
                console.log(self.fileImg1);
                console.log(self.fileImg2);
                console.log(self.fileImg3);
            };

            reader.readAsDataURL(file);
        }
    };

    previewVideo = (event, videoId) => {
        var reader = new FileReader();
        reader.onload = function() {
            var video = document.getElementById(videoId);
            video.src = reader.result;

            var labelId = 'nameVideo';
            var label = document.getElementById(labelId);
            label.textContent = event.target.files[0].name;

            this.fileVideo = reader.result
            console.log(this.fileVideo)
        }
        reader.readAsDataURL(event.target.files[0]);
    }
    inSertProduct = async () => {
        let product = this.createProductObject();
        console.log(product)
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
        // location.reload();
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
        let img1 = $('#fileInput11').val()
        let img2 = $('#fileInput22').val();
        let img3 = $('#fileInput33').val();
        let videos = $('#fileInput4').val();


        let fileName1 = this.getFileNameFromPath(img1);
        let fileName2 = this.getFileNameFromPath(img2);
        let fileName3 = this.getFileNameFromPath(img3);
        let fileNameVideo = this.getFileNameFromPath(videos)

        // Gộp các tên tệp lại thành một chuỗi
        const fileNames = [fileName1, fileName2, fileName3];
        const validFileNames = fileNames.filter(fileName => fileName);
        const imagesString = validFileNames.join(",");
        let slug = this.convertToSlug(name);
        var selectedValue = $('#formSelectIns option:selected').val();

        console.log(selectedValue);

        console.log(fileImg1);
        console.log(fileImg2);
        console.log(fileImg3);


        let product = {
            "name": name,
            "price": price,
            "quantity": quantity,
            "description": description,
            "pictures": imagesString,
            "videos": fileNameVideo,
            "slug": slug,
            "category":selectedValue,
            "fileimg1": productManagementInstance.fileImg1,
            "fileimg2": productManagementInstance.fileImg2,
            "fileimg3": productManagementInstance.fileImg3,
            "filevideo": productManagementInstance.fileVideo
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
        await $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: 'http://localhost:8083/updateProduct',
            data: JSON.stringify(productUpdate),
            success: function (data) {
                console.log(data)
                alert("update product success");
            },
            error: function (xhr, status, error) {
                console.log(xhr.responseText);
                console.log(status);
                console.log(error);
                alert("Đã xảy ra lỗi khi sua sản phẩm.");
            }
        })
    }
    createProductObjectUpdate =  () => {
        let name = $('#updNameProduct').val();
        let price = $('#updPriceProduct').val();
        let quantity = $('#updQuantityProduct').val();
        let description = $('#updDescriptionProduct').val();
        let img1 = $('#fileInput11').val()
        let img2 = $('#fileInput22').val();
        let img3 = $('#fileInput33').val();
        let videos = $('#previewVideo1').val();
        let id = $('#updID').val();

        let nameimge = $('#nameImage1').text();
        let nameimge1 = $('#nameImage2').text();
        let nameimge2 = $('#nameImage3').text();
        let nameVideo = $('#nameVideo').text();

        let slug = this.convertToSlug(name);
        const fileNames = [nameimge, nameimge1, nameimge2];
        const validFileNames = fileNames.filter(fileName => fileName);
        const imagesString = validFileNames.join(",");
        var selectedValue = $('#formSelect option:selected').val();

        console.log(selectedValue);

        let productupdate = {
            "name": name,
            "price": price,
            "quantity": quantity,
            "description": description,
            "pictures": imagesString,
            "videos": nameVideo,
            "category":selectedValue,
            "productId": id
        }
        return productupdate;
    }
}