document.addEventListener('DOMContentLoaded', function () {
    const comments = []; // Danh sách comment, bạn cần lấy dữ liệu từ backend

    const itemsPerPage = 5; // Số lượng comment hiển thị trên mỗi trang
    let currentPage = 1; // Trang hiện tại, bắt đầu từ trang 1
    let totalPages = Math.ceil(comments.length / itemsPerPage); // Tính tổng số trang

    const renderComments = () => {
        const startIndex = (currentPage - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;
        const currentComments = comments.slice(startIndex, endIndex);

        const commentListElement = document.getElementById('commentList');
        commentListElement.innerHTML = ''; // Xóa nội dung cũ trước khi render lại

        currentComments.forEach(comment => {
            const commentHtml = `
                <div class="card mb-4">
                    <div class="card-body">
                        <h5>${comment.content}</h5>
                        <div class="d-flex justify-content-between">
                            <div class="d-flex flex-row align-items-center">
                                <img src="${comment.userAvatar}" alt="avatar" width="50" style="border-radius: 30%" height="50"/>
                                <p class="small mb-0 ms-2">${comment.userName}</p>
                            </div>
                            <div class="d-flex flex-row align-items-center">
                                <i class="far fa-thumbs-up mx-2 fa-xs text-black" style="margin-top: -0.16rem;"></i>
                                <p class="small text-muted mb-0">${comment.date}</p>
                            </div>
                        </div>
                    </div>
                </div>
            `;
            commentListElement.innerHTML += commentHtml;
        });
    };

    const renderPagination = () => {
        const paginationElement = document.getElementById('commentPagination');
        paginationElement.innerHTML = '';

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
        paginationElement.appendChild(liPrevious);

        // Tạo các nút trang
        for (let i = 1; i <= totalPages; i++) {
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
            paginationElement.appendChild(li);
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
        paginationElement.appendChild(liNext);
    };

    renderComments();
    renderPagination();

    // Xử lý sự kiện khi click vào nút phân trang
    document.getElementById('commentPagination').addEventListener('click', function (event) {
        event.preventDefault();
        const targetPage = event.target.closest('.page-link');
        if (!targetPage || targetPage.textContent === '') return;

        const pageNumber = parseInt(targetPage.textContent);
        if (!isNaN(pageNumber)) {
            currentPage = pageNumber;
            renderComments();
            renderPagination();
        } else if (targetPage.getAttribute('aria-label') === 'Previous') {
            if (currentPage > 1) {
                currentPage--;
                renderComments();
                renderPagination();
            }
        } else if (targetPage.getAttribute('aria-label') === 'Next') {
            if (currentPage < totalPages) {
                currentPage++;
                renderComments();
                renderPagination();
            }
        }
    });
});
