class UserManagement {
    listUser = [];
    constructor() {
        this.self = this;
    }
    loadInit = async () => {
        await this.getListUser()
        this.createTableUsers();
    }
    getListUser = async () => {
        await $.ajax({
            type: 'GET',
            url: '/api-admin/users/getAllUsers',
            contentType: 'application/json',
            success: function (data) {
                this.listUser = data;
            }.bind(this),
            error: (xhr, status, error) => {
                console.error('Error: ' + error);
            }
        })
    }

    createTableUsers = () => {
        let tableHeader = `<table class="table align-items-center mb-0">
                                <thead>
                                <tr class="text-center">
                                    <th scope="col">Người dùng</th>
                                    <th scope="col">Email</th>
                                    <th scope="col" class="max-width-col">Mật khẩu</th>
                                    <th scope="col">Ngày sinh</th>
                                    <th scope="col">Giới tính</th>
                                    <th scope="col">Vai trò</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>`;
        let tableBody = '';
        this.listUser.forEach(e => {
            tableBody += `<tr>
                            <td>
                                <div class="d-flex px-2 py-1">
                                    <div>
                                        <img src="${e.avatar}" class="avatar avatar-sm me-3 border-radius-lg" alt="">
                                    </div>
                                    <div class="d-flex w-100 flex-column justify-content-center">
                                        <h6 class="mb-0 text-sm"">${e.fullname}</h6>
                                    </div>
                                </div>
                            </td>
                            <td class="align-middle text-center">
                                <p class="text-xs text-secondary mb-0"> ${e.email} </p>
                            </td>
                            <td class="align-middle text-center">
                            <button type="button" class="btn btn-success mb-0 me-3 rounded-circle sidenav"
                                    data-bs-target="#exampleModalToggle" data-bs-toggle="modal"
                                    style="padding: 7px 12px 7px 12px;">
                                <i class="fa-solid fa-plus " aria-hidden="true"></i>
                            </button>
<!--                                <p class="text-xs text-secondary mb-0">${e.hashedPassword}</p>-->
                            </td>
                            <td class="align-middle text-center">
                                <span class="text-secondary text-xs font-weight-bold">${e.dob}</span>
                            </td>
                            <td class="align-middle text-center text-sm">
                                <span class="text-secondary text-xs font-weight-bold">${e.gender}</span>
                            </td>
                            <td class="align-middle text-center text-sm">
                                <input class="form-check-input " style="border: 2px solid #ccc;"
                                       type="checkbox" id="inlineCheckbox1" value="option1"
                                       checked="${e.isAdmin}">
                                <!--												 <label class="form-check-label" for="inlineCheckbox1">1</label> -->
                            </td>
                            <td class="align-middle text-center">
                                <button type="button" class="btn border-0 rounded btn-outline-info"
                                   data-bs-toggle="modal" data-bs-target="#exampleModalsua">Sửa</button>
                                <button class="border border-0 rounded text-secondary font-weight-bold text-xs ms-2 text-danger"
                                        data-bs-toggle="modal" data-bs-target="#exampleModalXoa">
                                        Xóa</button>
                            </td>
                        </tr>`;
        })
        let tableFooter = `</tbody>
                            </table>`;

        let result = tableHeader + tableBody + tableFooter;
        $('#tableUserContainer').html(result)
    }

}