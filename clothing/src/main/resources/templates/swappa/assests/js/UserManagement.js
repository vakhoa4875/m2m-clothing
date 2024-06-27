class UserManagement {
    listUser = [];
    user = undefined;
    userIdentifier = undefined;
    uploadedImg = {
        base64: undefined,
        name: undefined
    }

    constructor() {
        this.self = this;
    }

    imageDealer = (event) => {
        let input = event.target;
        let file = input.files[0];
        this.uploadedImg.name = file.name;
        if (file) {
            let reader = new FileReader();
            let previewImage = document.getElementById('previewImage');

            reader.onload = function (e) {
                previewImage.src = e.target.result;
                previewImage.style.display = 'block';
            }
            reader.readAsDataURL(file);
        }
    }
// Load all users to table
    loadInit = async () => {
        await this.getListUser()
        this.createTableUsers();
    }
    getListUser = async () => {
        await $.ajax({
            type: 'GET',
            url: '/api-public/users/getAllUsers',
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
        let tableHeader = `<table class="table mb-0 perfect-container">
                                    <thead>
                                        <tr class="text-center align-middle">
                                            <th></th>
                                            <th scope="col">User's name</th>
                                            <th scope="col">Role</th>
                                            <th scope="col">Gender</th>
                                            <th scope="col">Date of birth</th>
                                            <th scope="col"></th>
                                        </tr>
                                    </thead>
                                <tbody>`;
        let tableBody = '';
        this.listUser.forEach(e => {
            tableBody += `<tr class="text-center align-middle">
                            <td>
                                <div class="d-flex flex-row justify-content-start align-items-center gap-3 px-lg-4">                                    
                                <img src="/assests/imagesUser/${e.avatar}"
                                     alt="${e.fullname}" class="circle-image">
                                    <h6 class="mb-0 text-sm"">${e.username}</h6>
                                </div>
                            </td>
                            <td>
                                <h6 class="mb-0 text-sm"">${e.fullname}</h6>
                            </td>
                            <td>
                                <span class="badge text-bg-${e.roleId === 1 ? 'primary' : (e.roleId === 3 ? 'secondary' : 'warning')}" style="font-size: 14px;">${e.roleName}</span>
<!--						label class="form-check-label" for="inlineCheckbox1">1</label> -->
                            </td>
                            <td>
                                <span class="text-secondary text-xs font-weight-bold">
                                    ${e.gender === 'Nam' || e.gender === 'Male' ? 'Male' : 'Female'}
                                </span>
                            </td>
                            <td>
                                <span class="text-secondary text-xs font-weight-bold">${e.dob}</span>
                            </td>
                            <td class="d-flex flex-row align-items-center justify-content-end gap-3">
                                <button class="btn border-0 rounded btn-outline-info" onclick="services.self.updateModal('${e.username}', '${e.email}')"
                                    data-bs-toggle="modal" data-bs-target="#exampleModalSua1"><i class="fa-solid fa-pen-to-square"></i></button>
                                <button class="border border-0 rounded btn-outline-danger"
                                        data-bs-toggle="modal" data-bs-target="#exampleModalXoa">
                                        <i class="fa-solid fa-trash" onclick="services.self.initUserIdentifier('${e.username}', '${e.email}')"></i></button>
                            </td>
                        </tr>`;
        })
        let tableFooter = `</tbody>
                            </table>`;

        let result = tableHeader + tableBody + tableFooter;
        $('#tableUserContainer').html(result)
    }
// Load specific user to update modal
    updateModal = async (username, email) => {
        await this.doGetUserByUsernameAndEmail(username, email);
        this.fillToUpdateModal();
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
            }.bind(this),
            error: (error) => {
                console.error("Error:", error);
            }

        })
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
                            <input id="upgenderMale" class="" type="radio" name="gender" ${(services.self.user.gender === 'Male' || services.self.user.gender === 'Nam') ? 'checked' : ''}
                                   style="border: 1px solid #d2d6da;" value="Male"> Male
                            <input id="upgenderFemale" class="ms-1" type="radio" name="gender" ${services.self.user.gender === 'Female' || services.self.user.gender === 'Nữ' ? 'checked' : ''}
                                   style="border: 1px solid #d2d6da;" value="Female"> Female
                        </div>
                        <div class="mb-2 ">
                            <label for="upRole" class="form-label me-3 m-0">Role</label>
                            <select class="form-select" id="upRole" aria-label="Default select example" >
                                <option ${services.self.user.roleId === 1 ? 'selected' : ''} value="1">Admin</option>
                                <option ${services.self.user.roleId === 2 ? 'selected' : ''}  value="2">Manager</option>
                                <option ${services.self.user.roleId === 3 ? 'selected' : ''}  value="3">User</option>
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
                                <img src="/assests/imagesUser/${services.self.user.avatar}" class="avatar me-3 border-radius-lg"
                                     id="previewImage" alt="Ảnh đã chọn">
                            </div>
                            <div class="text-center my-3" style="margin-right: 10px;">
                                <input type="file" class="form-control" id="fileInput" accept="image/*" onchange="services.self.imageDealer(event)">
                                <label class="custom-file-label" for="fileInput">Chọn ảnh từ local</label>
                            </div>
                        </div>
                    </div>
                </div>`;
        $('#updateUserModalBody').html(modelBody);
    }
    extractImgName = (path) => {
        let arr = path.split('/');
        return arr[arr.length - 1];
    }
// Update specific user with data from Update modal
    updateUser = async () => {
        let username = $('#upUsername').text();
        let email = $('#upEmail').text();
        let fullname = $('#upFullname').val();
        let dob = $('#upBirth').val();
        let gender = $('input[name="gender"]:checked').val();
        let roleId = $('#upRole').val();
        let roleName = $('#upRole option:selected').text();
        let jobTitle = $('#upJobTitle').val();
        let description = $('#upDescription').text();
        this.uploadedImg.base64 = $('#previewImage').attr('src');
        let avatar = (this.uploadedImg.name)
            ? `${this.uploadedImg.name},${this.uploadedImg.base64}`
            : this.extractImgName(this.uploadedImg.base64);
        console.dir(this.uploadedImg);

        let userData = {
            username: username,
            email: email, // password: password,
            fullname: fullname,
            avatar: avatar,
            dob: dob,
            gender: gender,
            roleId: roleId,
            roleName: roleName,
            jobTitle: jobTitle,
            description: description, // avatar: avatar
        };


        await $.ajax({
            url: '/api-public/users/saveUser',
            method: 'POST',
            data: JSON.stringify(userData),
            contentType: 'application/json',
            success: function (updateStatus) {
                let icon =
                    updateStatus.includes('success') ? 'success'
                        : updateStatus.includes('error') ? 'error'
                            : 'info';
                Swal.fire({
                    title: 'System Announcement',
                    text: updateStatus,
                    icon: icon,
                    confirmButtonText: 'Confirm & Reload'
                }).then(result => {
                    if (result.isConfirmed) {
                        location.reload();
                    }
                });
            },
            error: (error) => {
                console.error("Error:", error);
            }
        });
        location.reload();
    }
//Insert new user withd data from Insert modal
    insertNewUser = async () => {

        let username = $('#inUsername').val();
        // let fullname = $('#inFullname').val();
        let email = $('#inEmail').val();
        let password = $('#inPass').val();
        let confirmPassword = $('#inConfirmPass').val();
        // let gender = $('input[name="inGender"]:checked').val();
        let roleId = $('#inRole').val();
        // let roleName = $('#inRole option:selected').text();
        // let description = $('#upDescription').text();

        let userData = {
            "username": username,
            "email": email,
            "password": password,
            "confirmPassword": confirmPassword, // "fullname": fullname,
            "isAdmin": roleId
            // "roleName": roleName,
            // "description": description
        };

        await $.ajax({
            url: '/api/admin/account/create',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(userData),
            success: (insertStatus) => {
                let icon =
                    insertStatus.includes('success') ? 'success'
                        : insertStatus.includes('error') ? 'error'
                            : 'info';
                Swal.fire({
                    title: 'System Announcement',
                    text: insertStatus,
                    icon: icon,
                    confirmButtonText: 'Confirm & Reload'
                }).then(result => {
                    if (result.isConfirmed) {
                        location.reload();
                    }
                });
            },
            error: (error) => {
                console.error('>>Error: ' + error);
            }
        });
        // location.reload();
    }

    initUserIdentifier = (username, email) => {
        this.userIdentifier = {
            "username": username, "email": email
        }
    }
// Disable user
    disableUser = async () => {
        await $.ajax({
            url: '/api-public/users/disableUser',
            method: 'POST',
            data: JSON.stringify(this.userIdentifier),
            contentType: 'application/json',
            success: (deleteStatus) => {
                Swal.fire({
                    title: 'Disable user successfully!',
                    icon: 'success',
                    confirmButtonText: 'Confirm & Reload'
                }).then(result => {
                    if (result.isConfirmed) {
                        location.reload();
                    }
                });
            },
            error: (error) => {
                Swal.fire({
                    title: 'Error occurring during the process',
                    icon: 'error'
                })
            }
        })
        this.userIdentifier = undefined;
        location.reload();
    }

}