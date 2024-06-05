var listSearchRecommendation = [];
var matchedSearch = [];
const tenSp = $('#tenSp');
const tenShop = $('#tenShop');
const searchInput = $('#navbar_search_input');
const searchRecommendationContainer = $('#input-container');

getListProducts = async () => {
    await axios
        .get("/api-public/getProductsInfoForSearchRecommend")
        .then(response => {
            listSearchRecommendation = response.data.data;
        })
        .catch(error => {
            console.error(error);
        })
}

loadSearchRecommendation = () => {
    tenSp.html('');
    tenShop.html('');
    matchedSearch = [];

    var keyword = searchInput.val().trim();
    if (keyword.length > 0) {
        searchRecommendationContainer.css('display', 'block');
        console.dir(listSearchRecommendation);
        listSearchRecommendation.forEach((value, index) => {
            if (value[0].toLowerCase().includes(keyword)) {
                if (matchedSearch.length < 4) {
                    matchedSearch.push(value);
                }
            }
        });
        tenShop.append(`<a href="/home" class="ms-2 text-white text-start link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover w-100" >
                            ${keyword}
                        </a>`
        );
        matchedSearch.forEach((e, index) => {
            $('#tenSp').append(`<a href="${'/product?slug_url=' + e[1]}" class="ms-2 p-1 pb-2 text-white text-start link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover">
                                    ${e[0]}
                                </a>`
            );
        })
    } else {
        searchRecommendationContainer.css('display', 'none');
    }
}

$(document).ready(async () => {
    await getListProducts();
    console.dir(listSearchRecommendation);
})
searchInput.on('input', () => {
    loadSearchRecommendation();
});
searchInput.blur(() => {
    searchRecommendationContainer.css('display', 'none');
});