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
                                    <th scope="col">Full Name</th>
                                    <th scope="col">Email</th>
                                    <th scope="col" class="max-width-col">Hashed Password</th>
                                    <th scope="col">Date of Birth</th>
                                    <th scope="col">Gender</th>
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
                                <span class="badge text-bg-${e.isAdmin ? 'primary' : 'secondary'}" style="font-size: 14px;">${e.isAdmin ? 'Admin' : 'User'}</span>
<!--						label class="form-check-label" for="inlineCheckbox1">1</label> -->
                            </td>
                            <td class="align-middle text-center">
                                <button class="btn border-0 rounded btn-outline-info" onclick="services.self.updateModal(${e.userId})"
                                    data-bs-toggle="modal" data-bs-target="#exampleModalToggle2"><i class="fa-solid fa-pen-to-square"></i></button>
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

    doGetUserById = async (id) => {
        await $.ajax({
            url: 'http://localhost:8083/api-admin/users/getUserById',
            method: 'GET',
            data: {"UserID": id},
            success: function (apiResult) {
                this.user = apiResult;
                console.log(this.user)
            }.bind(this),
            error: (error) => {
                console.error("Error:", error);
            }

        })
    }

    updateModal = async (id) => {
        await this.doGetUserById(id);
        this.fillToUpdateModal();
    }

    fillToUpdateModal = () => {
        // let services.self.user = services.self.user;
        let modelBody = `
                <div class="mb-2 ">
                    <label for="updatedFullname" class="form-label">Email</label>
                    <input type="text" class="form-control ps-3" id="updatedFullname" name="updatedFullname"  value="${services.self.user.fullname}"
                           aria-describedby="basic-addon3 basic-addon4" style="border: 1px solid #d2d6da;">
                </div>
                <div class="mb-2 ">
                    <label for="updatedEmail" class="form-label">Email</label>
                    <input type="text" class="form-control ps-3" id="updatedEmail" name="updatedEmail"  value="${services.self.user.email}"
                           aria-describedby="basic-addon3 basic-addon4" style="border: 1px solid #d2d6da;">
                </div>
                <div class="mb-2 ">
                    <label for="updatedPassword" class="form-label">Password</label>
                    <input type="text" class="form-control ps-3" id="updatedPassword" name="updatedPassword" value=""
                           aria-describedby="basic-addon3 basic-addon4" style="border: 1px solid #d2d6da;">
                </div>
                <div class="mb-2 ">
                    <label for="updatedDob" class="form-label">Date of Birth</label>
                    <input id="updatedDob" name="updatedDob" class="form-control ps-3 pe-3" type="text" placeholder="dd/MM/yyyy"
                           style="border: 1px solid #d2d6da;" value="${services.self.user.dob}">
                    <!-- <span id="startDateSelected"></span> -->
                </div>
                <div class="mt-3 ">
                    <label for="updatedGender" class="form-label me-3 m-0">Gender</label>
                    <input id="updatedGender1" class="" type="radio" name="updatedGender" style="border: 1px solid #d2d6da;" ${services.self.user.gender === 'Male'?"checked":''}>
                    Male
                    <input id="updatedGender0" class="ms-1" type="radio" name="updatedGender" style="border: 1px solid #d2d6da;" ${services.self.user.gender === 'Female'?"checked":''}>
                    Female
                    <!-- <span id="startDateSelected"></span> -->
                </div>
                <div class="mt-3 ">
                    <label for="updatedRole" class="form-label me-3 m-0">Role</label>
                    <input id="updatedRole1" class="" type="radio" name="updatedRole" style="border: 1px solid #d2d6da;" ${services.self.user.isAdmin ? 'checked' : ''}>
                    Admin
                    <input id="updatedRole0" class="ms-1" type="radio" name="updatedRole" style="border: 1px solid #d2d6da;" ${services.self.user.isAdmin ? '' : 'checked'}>
                    Site User
                </div>
                <div class="mb-2 ">
                    <label for="updatedDescription" class="form-label">Description</label>
                    <input type="text" class="form-control ps-3" id="updatedDescription" name="updatedDescription"  value="${services.self.user.description}"
                           aria-describedby="basic-addon3 basic-addon4" style="border: 1px solid #d2d6da;">
                </div>`;
        $('#updateUserModalBody').html(modelBody);
    }

    updateUser = () => {
        $.ajax({

        })
    }
}