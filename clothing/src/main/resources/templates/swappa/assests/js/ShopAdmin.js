$(document).ready(function () {
    const getShopByUserEmail = async () => {
        await axios.get('/api/shop/getShopByUser')
            .then(response => {
                let shopInfo = response.data.data;
                let email = response.data.data2;
                $('#nameShop').val(shopInfo.nameShop);
                $('#emailShop').val(email);
                let formattedDate = moment(shopInfo.dateEstablished).format('DD-MM-YYYY');
                $('#dateEst').val(shopInfo.dateEstablished);
                $('#previewImage').attr("src", "/assests/shopImg/" + shopInfo.logo);
                $('#previewImage').attr("data-logo", shopInfo.logo);
            })
            .catch(error => {
                console.error(error);
            })
    }
    getShopByUserEmail();
    $('#v-pills-home-shopinfo').click(function () {
        getShopByUserEmail();
    })

    const updateShopInfo = async () => {
        let nameShop = $('#nameShop').val();
        let email = $('#emailShop').val();
        let logo = $('#fileInput')[0].files[0];
        let logoOldName = $('#previewImage').data('logo');
        let formData = new FormData();
        formData.append('nameShop', nameShop);
        formData.append('email', email);
        if (logo) {
            formData.append('logo', logo);
        }else{
            const logo = new File([""], logoOldName, { type: "image/png" });
            formData.append('logo', logo);
        }


        await axios.post('/api/shop/update', formData)
            .then(response => {
            })
            .catch(error => {
            })
    }
    $('#submitShopBtn').click(function () {
        updateShopInfo();
    })

})
