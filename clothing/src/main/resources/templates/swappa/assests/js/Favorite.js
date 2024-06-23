$(document).ready(function () {
    var url = new URL(window.location.href);
    var slug = url.searchParams.get("slug_url");

    const checkFavorite = async () => {
        let yeuThich = $('#yeuThich');
        axios.get('/api/favorite/checkFavorite', {
            params: {
                slugUrl: slug
            }
        })
            .then(response => {
                yeuThich.html('');
                let responseData = response.data;
                if (responseData.message === 'Favorite existed') {
                    yeuThich.append('<i class="fa-solid fa-heart"></i>');
                } else {
                    yeuThich.append('<i class="fa-regular fa-heart"></i>');
                }
            })
            .catch(error => {
                console.error("Error checking favorite:", error);
            });
    }
    checkFavorite();

    const addFavorite = async () => {
        console.log(slug)
        axios.post('/api/favorite/saveFavorite',null, {
            params: {
                slugUrl: slug
            }
        })
            .then(response => {
                console.log(response.data);
                let responseData = response.data;
                if (responseData.message === 'User not found') {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Please log in!!!",
                        timer: 2000,
                    }).then((result) => {
                        if (result.isConfirmed || result.dismiss === Swal.DismissReason.timer) {
                            window.location.href = '/loginacount';
                        }
                    });
                } else if (responseData.message === 'Favorite deleted successfully') {
                    Swal.fire({
                        icon: "success",
                        title: "Success",
                        text: "Removed from favorite",
                        timer: 2000,
                    }).then((result) => {
                        if (result.isConfirmed || result.dismiss === Swal.DismissReason.timer) {
                            checkFavorite();
                        }
                    });
                } else if (responseData.message === 'Favorite saved successfully') {
                    Swal.fire({
                        icon: "success",
                        title: "Success",
                        text: "Added to favorite",
                        timer: 2000,
                    }).then((result) => {
                        if (result.isConfirmed || result.dismiss === Swal.DismissReason.timer) {
                            checkFavorite();
                        }
                    });
                }
            })
            .catch(error => {
                console.error("Error saving favorite:", error);
            });
    };

    $('#yeuThich').click(function () {
        addFavorite();
    });
});
