$(document).ready(function () {

    const saveShop = async () => {
        await axios
            .post('/api/public/shopSignUp')
            .then(response => {
            })
            .catch(error => {
                alert(error);
            })
    }
    const getShopByUserEmail = async () => {
        await axios.get('/get-shop-by-user-email')
            .then(response => {
                if(response.data.data != null){
                    $('#v-pills-home-tab').click();
                    window.open('/admin/shop', '_blank');
                }
            })
            .catch(error => {
                alert(error);
            })
    }

    $('#submitShop').click(function () {
        Swal.fire({
            title: "Are you sure?",
            text: "You want to become a seller!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes"
        }).then((result) => {
            if (result.isConfirmed) {
                saveShop();
                Swal.fire({
                    title: "Success!",
                    text: "You have become a seller",
                    icon: "success"
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.open('/admin/shop', '_blank');
                    }
                })

            }
        });
    })

    $('#v-pills-home-shop').click(function () {
        getShopByUserEmail();
    })
})