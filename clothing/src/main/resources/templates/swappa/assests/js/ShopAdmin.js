$(document).ready(function () {
    const getShopByUserEmail = async () => {
        await axios.get('/get-shop-by-user-email')
            .then(response => {
                let shopInfo = response.data.data;
                let email = response.data.data2;
                $('#nameShop').val(shopInfo.nameShop);
                $('#emailShop').val(email);
                let formattedDate = moment(shopInfo.dateEstablished).format('DD-MM-YYYY');
                console.log(formattedDate)
                $('#dateEst').val(shopInfo.dateEstablished);
                $('#previewImage').attr("src", "/assests/shopImg/" + shopInfo.logo);
                $('#previewImage').attr("data-logo", shopInfo.logo);
            })
            .catch(error => {
                console.log(error);
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


        await axios.post('/api/public/updateShopInfo', formData)
            .then(response => {
                console.log(response.data.data);
                console.log(response.data.message);
            })
            .catch(error => {
                console.log(error);
            })
    }
    $('#submitShopBtn').click(function () {
        updateShopInfo();
    })

})
