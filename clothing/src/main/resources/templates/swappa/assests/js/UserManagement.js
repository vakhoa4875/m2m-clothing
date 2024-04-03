class UserManagement {
    listUser = [];
    user = undefined;
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
            url: 'http://localhost:8083/api-public/users/getAllUsers',
            contentType: 'application/json',
            success: function (data) {
                this.listUser = data;
                console.log(data)
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
                                    <th scope="col"></th>
                                    <th scope="col">Full Name</th>
                                    <th scope="col">Gender</th>
                                    <th scope="col">Date of Birth</th>
                                    <th scope="col">Role</th>
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
                            <td class="align-middle text-center text-sm">
                                <span class="text-secondary text-xs font-weight-bold">${e.gender}</span>
                            </td>
                            <td class="align-middle text-center">
                                <span class="text-secondary text-xs font-weight-bold">${e.dob}</span>
                            </td>
                            <td class="align-middle text-center text-sm">
                                <span class="badge text-bg-${e.roleId = 1 ? 'primary' : (e.roleId = 3)? 'secondary':'warning'}" style="font-size: 14px;">${e.isAdmin ? 'Admin' : 'User'}</span>
<!--						label class="form-check-label" for="inlineCheckbox1">1</label> -->
                            </td>
                            <td class="align-middle text-center">
                                <button class="btn border-0 rounded btn-outline-info" onclick="services.self.updateModal('${e.username}', '${e.email}')"
                                    data-bs-toggle="modal" data-bs-target="#exampleModalSua1"><i class="fa-solid fa-pen-to-square"></i></button>
                                <button class="border border-0 rounded btn-outline-danger"
                                        data-bs-toggle="modal" data-bs-target="#exampleModalXoa">
                                        <i class="fa-solid fa-trash"></i></button>
                            </td>
                        </tr>`;
        })
        let tableFooter = `</tbody>
                            </table>`;

        let result = tableHeader + tableBody + tableFooter;
        $('#tableUserContainer').html(result)
    }

    doGetUserByUsernameAndEmail = async (username, email) => {
        await $.ajax({
            url: '/api-public/users/getUserByUsernameAndEmail',
            method: 'GET',
            data: {
                "username": username,
                "email": email
            },
            success: function (apiResult) {
                this.user = apiResult;
                console.log(this.user)
            }.bind(this),
            error: (error) => {
                console.error("Error:", error);
            }

        })
    }

    updateModal = async (username, email) => {
        await this.doGetUserByUsernameAndEmail(username, email);
        this.fillToUpdateModal();
    }

    fillToUpdateModal = () => {
        // let services.self.user = services.self.user;
        let modelBody = `
        
                <div class="row">
                    <div class="col-8">
                        <div class="mb-2 ">
                            <label for="upUsername" class="form-label">Username</label>
                            <p class="form-control" id="upUsername">${services.self.user.username}</p>
                        </div>
                        <div class="mb-2 ">
                            <label for="upEmail" class="form-label">Email</label>
                            <p class="form-control" id="upEmail">${services.self.user.email}</p>
                        </div>

                        <div class="mb-2 ">
                            <label for="upPass" class="form-label">Password</label>
                            <input type="text" class="form-control ps-3" id="upPass" aria-describedby=""
                                   style="border: 1px solid #d2d6da;" value="${services.self.user.hashedPassword}">
                        </div>
                        <div class="mb-2 ">
                            <label for="upFullname" class="form-label">Fullname</label>
                            <input type="text" class="form-control ps-3" id="upFullname" value="${services.self.user.fullname}"
                                   aria-describedby="basic-addon3 basic-addon4" style="border: 1px solid #d2d6da;">
                        </div>

                        <div class="mb-2 ">
                            <label for="upBirth" class="form-label">Date of birth</label>
                            <input id="upBirth" class="form-control ps-3 pe-3" type="text"
                                   style="border: 1px solid #d2d6da;" value="${services.self.user.dob}">

                        </div>

                        <div class="mb-2 ">
                            <label for="upGender" class="form-label me-3 m-0">Gender</label>
                            <input id="upgenderMale" class="" type="radio" name="gender" ${(services.self.user.gender === 'Male' || services.self.user.gender === 'Nam')?'checked':''}
                                   style="border: 1px solid #d2d6da;" value="Male"> Male
                            <input id="upgenderFemale" class="ms-1" type="radio" name="gender" ${services.self.user.gender === 'Female'||services.self.user.gender === 'Nữ'?'checked':''}
                                   style="border: 1px solid #d2d6da;" value="Female"> Female

                        </div>
                        <div class="mb-2 ">
                            <label for="upRole" class="form-label me-3 m-0">Role</label>
                            <select class="form-select" id="upRole" aria-label="Default select example" >
                                <option ${services.self.user.roleId === 1?'selected':''} value="1">Admin</option>
                                <option ${services.self.user.roleId === 2?'selected':''}  value="2">Manager</option>
                                <option ${services.self.user.roleId === 3?'selected':''}  value="3">User</option>
                            </select>
                        </div>
                        <div class="mb-2 ">
                            <label for="upJobTitle" class="form-label">Job Title</label>
                            <input type="text" class="form-control ps-3" id="upJobTitle" aria-describedby=""
                                   style="border: 1px solid #d2d6da;" value="${services.self.user.jobTitle}">
                        </div>
                        <div class="mb-2">
                            <label for="upDescription" class="form-label">Description</label>
                            <textarea class="form-control" id="upDescription" rows="3">${services.self.user.description}</textarea>
                        </div>
                    </div>
                    <div class="col-4">
                        <div class="container " style="margin-top: 150px;">
                            <div class="d-flex justify-content-center">
                                <img src="https://placehold.co/36x36/" class="avatar me-3 border-radius-lg"
                                     id="previewImage" alt="Ảnh đã chọn">
                            </div>
                            <div class="text-center my-3" style="margin-right: 10px;">
                                <input type="file" class="form-control" id="fileInput" accept="image/*">
                                <label class="custom-file-label" for="fileInput">Chọn ảnh từ local</label>
                            </div>
                        </div>
                    </div>
                </div>`;
        $('#updateUserModalBody').html(modelBody);
    }

    updateUser = async () => {
        let username = $('#upUsername').text();
        let email = $('#upEmail').text();
        let password = $('#upPass').text();
        let fullname = $('#upFullname').text();
        let dob = $('#upBirth').text();
        let gender =  $('input[name="gender"]:checked').val();
        let roleId = $('#upRole').val();
        let roleName = $('#upRole option:selected').text();
        let jobTitle = $('#upJobTitle').text();
        let description = $('#upDescription').text();
        let avatar = $('#previewImage').attr('src');

        await $.ajax({
            url : '/api-public/users/saveUser',

        })
    }
}