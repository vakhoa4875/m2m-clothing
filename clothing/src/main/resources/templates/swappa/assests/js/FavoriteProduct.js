let currentPage = 1;
const itemsPerPage = 5;
const maxVisiblePages = 3;
let totalPages = 0;
document.addEventListener('DOMContentLoaded', () => {
    fetch('/api/favorite/show-favorite')
        .then(response => response.json())
        .then(data => {
            if (data.status === true) {
                let favorites = data.data;
                const favoriteList = document.getElementById('favoriteList');
                const pagination = document.getElementById('pagination');
                pagination.innerHTML = '';

                const displayCurrentPage = () => {
                    const startIndex = (currentPage - 1) * itemsPerPage;
                    const endIndex = startIndex + itemsPerPage;
                    const currentFavorites = favorites.slice(startIndex, endIndex);
                    favoriteList.innerHTML = '';
                    if (currentFavorites.length === 0) {
                        document.getElementById('noFavoriteMessage').style.display = 'block';
                    } else {
                        document.getElementById('noFavoriteMessage').style.display = 'none';
                        currentFavorites.forEach((favorite, index) => {
                            const { id, productId, productName, pictures, slugUrl } = favorite;
                            const firstPicture = pictures.split(',')[0].trim();
                            const pictureUrl = `../media/${firstPicture}`;
                            const rowHtml = `
                                    <tr class="text-center align-middle">
                                        <th scope="row">${startIndex + index + 1}</th>
                                        <td>${productName}</td>
                                        <td><img src="${pictureUrl}" class="img-fluid " style="max-width: 70px; max-height: 70px;" alt="${productName}"></td>
                                        <td>
                                            <a href="/product?slug_url=${slugUrl}" class="btn btn-outline-info btn-sm mr-2">
                                                <i class="fa-regular fa-eye"></i> View
                                            </a>
                                            <button class="btn btn-danger btn-sm favorite-btn" data-id="${id}" data-product-id="${productId}">
                                                <i class="fa-solid fa-xmark"></i>
                                            </button>
                                        </td>
                                    </tr>
                                `;
                            favoriteList.innerHTML += rowHtml;
                        });
                    }

                    // Gắn sự kiện cho các nút yêu thích
                    const favoriteButtons = document.querySelectorAll('.favorite-btn');
                    favoriteButtons.forEach(button => {
                        button.addEventListener('click', (event) => {
                            event.preventDefault();
                            const id = button.getAttribute('data-id');
                            const productId = button.getAttribute('data-product-id');

                            const swalWithBootstrapButtons = Swal.mixin({
                                customClass: {
                                    confirmButton: "btn btn-success",
                                    cancelButton: "btn btn-danger"
                                },
                                buttonsStyling: false
                            });

                            swalWithBootstrapButtons.fire({
                                title: "Are you sure?",
                                text: "Do you want to remove this product from favorites?",
                                icon: "warning",
                                showCancelButton: true,
                                confirmButtonText: "Yes, remove it!",
                                cancelButtonText: "No, cancel!",
                                reverseButtons: true
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    fetch('/api/favorite/delete-favorite', {
                                        method: 'POST',
                                        headers: {
                                            'Content-Type': 'application/x-www-form-urlencoded',
                                        },
                                        body: new URLSearchParams({
                                            id: id,
                                            productId: productId
                                        })
                                    })
                                        .then(response => response.json())
                                        .then(data => {
                                            if (data.status === true) {
                                                swalWithBootstrapButtons.fire({
                                                    title: "Removed!",
                                                    text: "Product removed from favorites successfully.",
                                                    icon: "success"
                                                }).then(() => {
                                                    fetch('/api/favorite/show-favorite')
                                                        .then(response => response.json())
                                                        .then(newData => {
                                                            if (newData.status === true) {
                                                                favorites = newData.data;
                                                                updatePagination();
                                                                displayCurrentPage();
                                                            } else {
                                                                console.error('Error fetching updated favorite list:', newData.message);
                                                            }
                                                        })
                                                        .catch(error => {
                                                            console.error('Failed to fetch updated favorite list:', error);
                                                        });
                                                });
                                            } else {
                                                swalWithBootstrapButtons.fire({
                                                    title: "Failed!",
                                                    text: "Product removal unsuccessful.",
                                                    icon: "error"
                                                });
                                            }
                                        })
                                        .catch(error => {
                                            console.error('Error calling delete API:', error);
                                        });
                                } else if (result.dismiss === Swal.DismissReason.cancel) {
                                    swalWithBootstrapButtons.fire({
                                        title: "Cancelled",
                                        text: "Your favorite product is safe :)",
                                        icon: "error"
                                    });
                                }
                            });
                        });
                    });
                };
                totalPages = Math.ceil(favorites.length / itemsPerPage);
                const updatePagination = () => {
                    pagination.innerHTML = '';

                    const totalPages = Math.ceil(favorites.length / itemsPerPage);
                    let startPage = currentPage - Math.floor(maxVisiblePages / 2);
                    startPage = Math.max(startPage, 1);
                    let endPage = startPage + maxVisiblePages - 1;
                    endPage = Math.min(endPage, totalPages);

                    if (endPage - startPage + 1 < maxVisiblePages) {
                        startPage = Math.max(endPage - maxVisiblePages + 1, 1);
                    }

                    if (currentPage <= Math.floor(maxVisiblePages / 2)) {
                        startPage = 1;
                        endPage = Math.min(maxVisiblePages, totalPages);
                    } else if (currentPage > totalPages - Math.floor(maxVisiblePages / 2)) {
                        startPage = Math.max(totalPages - maxVisiblePages + 1, 1);
                        endPage = totalPages;
                    }

                    // Tạo nút Previous
                    const liPrevious = document.createElement('li');
                    liPrevious.classList.add('page-item');
                    const aPrevious = document.createElement('a');
                    aPrevious.classList.add('page-link');
                    aPrevious.href = '#';
                    aPrevious.setAttribute('aria-label', 'Previous');
                    aPrevious.innerHTML = `
                            <span aria-hidden="true">&laquo;</span>
                            <span class="visually-hidden">Previous</span>
                        `;
                    liPrevious.appendChild(aPrevious);
                    pagination.appendChild(liPrevious);

                    // Tạo nút trang đầu tiên và dấu "..."
                    if (startPage > 1) {
                        const liFirst = document.createElement('li');
                        liFirst.classList.add('page-item');
                        const aFirst = document.createElement('a');
                        aFirst.classList.add('page-link');
                        aFirst.href = '#';
                        aFirst.textContent = '1';
                        liFirst.appendChild(aFirst);
                        pagination.appendChild(liFirst);

                        if (startPage > 2) {
                            const liEllipsis = document.createElement('li');
                            liEllipsis.classList.add('page-item');
                            const spanEllipsis = document.createElement('span');
                            spanEllipsis.classList.add('page-link');
                            spanEllipsis.textContent = '...';
                            liEllipsis.appendChild(spanEllipsis);
                            pagination.appendChild(liEllipsis);
                        }
                    }

                    // Tạo các nút trang
                    for (let i = startPage; i <= endPage; i++) {
                        const li = document.createElement('li');
                        li.classList.add('page-item');
                        if (i === currentPage) {
                            li.classList.add('active');
                        }
                        const a = document.createElement('a');
                        a.classList.add('page-link');
                        a.href = '#';
                        a.textContent = i;
                        li.appendChild(a);
                        pagination.appendChild(li);
                    }

                    // Tạo nút trang cuối cùng và dấu "..."
                    if (endPage < totalPages) {
                        if (endPage < totalPages - 1) {
                            const liEllipsis = document.createElement('li');
                            liEllipsis.classList.add('page-item');
                            const spanEllipsis = document.createElement('span');
                            spanEllipsis.classList.add('page-link');
                            spanEllipsis.textContent = '...';
                            liEllipsis.appendChild(spanEllipsis);
                            pagination.appendChild(liEllipsis);
                        }
                        const liLast = document.createElement('li');
                        liLast.classList.add('page-item');
                        const aLast = document.createElement('a');
                        aLast.classList.add('page-link');
                        aLast.href = '#';
                        aLast.textContent = totalPages;
                        liLast.appendChild(aLast);
                        pagination.appendChild(liLast);
                    }

                    // Tạo nút Next
                    const liNext = document.createElement('li');
                    liNext.classList.add('page-item');
                    const aNext = document.createElement('a');
                    aNext.classList.add('page-link');
                    aNext.href = '#';
                    aNext.setAttribute('aria-label', 'Next');
                    aNext.innerHTML = `
                        <span aria-hidden="true">&raquo;</span>
                        <span class="visually-hidden">Next</span>
                    `;
                    liNext.appendChild(aNext);
                    pagination.appendChild(liNext);
                };

                displayCurrentPage();
                updatePagination();

                pagination.addEventListener('click', (event) => {
                    event.preventDefault();
                    const targetPage = event.target.closest('.page-link');
                    if (!targetPage || targetPage.textContent === '') return;

                    const pageNumber = parseInt(targetPage.textContent);
                    if (!isNaN(pageNumber)) {
                        currentPage = pageNumber;
                    } else if (targetPage.getAttribute('aria-label') === 'Previous') {
                        if (currentPage > 1) {
                            currentPage--;
                        }
                    } else if (targetPage.getAttribute('aria-label') === 'Next') {
                        if (currentPage < totalPages) {
                            currentPage++;
                        }
                    }
                    updatePagination();
                    displayCurrentPage();
                });

            } else {
                console.error('Lỗi khi lấy danh sách sản phẩm yêu thích:', data.message);
            }
        })
        .catch(error => {
            console.error('Đã xảy ra lỗi khi gọi API:', error);
        });
});