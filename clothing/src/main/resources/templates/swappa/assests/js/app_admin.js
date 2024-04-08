angular.module("myApp", ["ngRoute"])
    .run(($rootScope, $timeout) => {
        $rootScope.$on('$routeChangeStart', () => {
            $rootScope.loading = true;
        })
        $rootScope.$on('$routeChangeSuccess', () => {
            $timeout(() => {
                $rootScope.loading = false;
            }, 500);
        })
        $rootScope.$on('$routeChangeError', () => {
            $rootScope.loading = false;
            alert("Lỗi đc chưa");
        })
    })
    .config(['$locationProvider', function ($locationProvider) {
        $locationProvider.hashPrefix('!');
    }])
    .filter('uppercaseFirst', function () {
        return function (input) {
            if (input) {
                return input.charAt(0).toUpperCase() + input.slice(1);
            } else {
                return '';
            }
        };
    })
    .filter('timeAgo', function () {
        return function (input) {
            if (input) {
                var date = new Date(input);
                var now = new Date();
                var timeDifference = now - date;

                var seconds = Math.floor(timeDifference / 1000);
                var minutes = Math.floor(seconds / 60);
                var hours = Math.floor(minutes / 60);
                var days = Math.floor(hours / 24);
                var months = Math.floor(days / 30);
                var years = Math.floor(months / 12);

                if (years > 0) {
                    return years + ' year' + (years > 1 ? 's' : '') + ' ago';
                } else if (months > 0) {
                    return months + ' month' + (months > 1 ? 's' : '') + ' ago';
                } else if (days > 0) {
                    return days + ' day' + (days > 1 ? 's' : '') + ' ago';
                } else if (hours > 0) {
                    return hours + ' hour' + (hours > 1 ? 's' : '') + ' ago';
                } else if (minutes > 0) {
                    return minutes + ' minute' + (minutes > 1 ? 's' : '') + ' ago';
                } else {
                    return (seconds == 0) ? 'just now' : seconds + ' second' + (seconds > 1 ? 's' : '') + ' ago';
                }
            } else {
                return '';
            }
        };
    })
    .service('sharedDataService', function () {
        return {};
    })
    .controller("accountCtrl", function ($scope, $rootScope, $location, $timeout) {
        /*
        SIGN UP
        */
        $scope.firstNameBlurred = false;
        $scope.lastNameBlurred = false;
        $scope.email2Blurred = false;
        $scope.password21Blurred = false;
        $scope.password22Blurred = false;
        // $rootScope.user = true;
        //firstname sign up
        $scope.isInvalidFirstName = function () {
            // console.log($scope.firstName);
            return $scope.formSignUp.firstName.$invalid && $scope.firstNameBlurred;
        };
        //lastname sign up
        $scope.isInvalidLastName = function () {
            // console.log($scope.password21 + " | " + $scope.password22);
            return $scope.formSignUp.lastName.$invalid && $scope.lastNameBlurred;
        };
        //email2 sign up
        $scope.isInvalidEmail2 = function () {
            // console.log($scope.password21 + " | " + $scope.password22);
            return $scope.formSignUp.email2.$invalid && $scope.email2Blurred;
        };
        //password21 sign up
        $scope.isInvalidPassword21 = function () {
            // console.log($scope.password21 + " | " + $scope.password22);
            return $scope.formSignUp.password21.$invalid && $scope.password21Blurred;
        };
        //email21 sign up
        $scope.isInvalidPassword22 = function () {
            console.log($scope.password21 + " | " + $scope.password22);
            return $scope.formSignUp.password22.$invalid && $scope.password22Blurred;
        };
        /*
        LOG IN
        */
        $scope.emailBlurred = false;
        $scope.usernameBlurred = false;
        //email
        $scope.isInvalidEmail = function () {
            return $scope.formLogIn.email.$invalid && $scope.emailBlurred;
        };
        //username
        $scope.isInvalidUserName = function () {
            return $scope.formLogIn.username.$invalid && $scope.usernameBlurred;
        };
        $scope.checkUsernameValidity = function() {
            $scope.usernameBlurred = true; // Đặt biến usernameBlurred thành true khi mất focus
        };

        // $scope.loginSuccess = true;
        $scope.textStatus = '';
        $rootScope.isLogin = false;
        $rootScope.user = null;

        $rootScope.login = () => {
            $scope.status = -1;
            let log = 'Your email hasn\'t been registered yet!';
            if ($scope.formLogIn.$valid) {
                for (acc of $rootScope.accounts) {
                    if (acc.email.toLowerCase() == $scope.email.toLowerCase()) {
                        if (acc.password == $scope.password) {
                            // $scope.loginSuccess = true;
                            log = 'Welcome back, ' + acc.lastName + ' ' + acc.firstName + '!! You\'re automatically redirect to your previous page in 3 seconds';
                            $scope.status = 1;
                            $rootScope.user = acc;
                            break;
                        } else {
                            // $scope.loginSuccess = false;
                            $scope.textStatus = 'Wrong Password, please try again!!'
                            $scope.status = 0;
                            return;
                        }
                    }
                }
            }
            console.log(log);
            $scope.textStatus = log;
            $rootScope.isLogin = true;
            $timeout(() => {
                // Redirect to the desired page after the delay
                $location.path($rootScope.currentPath);  // Replace '/your-page' with the actual URL
            }, 2250);
        }
    })

    .controller("accountForgotCtrl", function ($scope, $rootScope, $location, $timeout){
        /*
        SIGN UP
        */
        $scope.firstNameBlurred = false;
        $scope.lastNameBlurred = false;
        $scope.email2Blurred = false;
        $scope.password21Blurred = false;
        $scope.password22Blurred = false;
        // $rootScope.user = true;
        //firstname sign up
        $scope.isInvalidFirstName = function () {
            // console.log($scope.firstName);
            return $scope.formSignUp.firstName.$invalid && $scope.firstNameBlurred;
        };
        //lastname sign up
        $scope.isInvalidLastName = function () {
            // console.log($scope.password21 + " | " + $scope.password22);
            return $scope.formSignUp.lastName.$invalid && $scope.lastNameBlurred;
        };
        //email2 sign up
        $scope.isInvalidEmail2 = function () {
            // console.log($scope.password21 + " | " + $scope.password22);
            return $scope.formSignUp.email2.$invalid && $scope.email2Blurred;
        };
        //password21 sign up
        $scope.isInvalidPassword21 = function () {
            // console.log($scope.password21 + " | " + $scope.password22);
            return $scope.formSignUp.password21.$invalid && $scope.password21Blurred;
        };
        //email21 sign up
        $scope.isInvalidPassword22 = function () {
            console.log($scope.password21 + " | " + $scope.password22);
            return $scope.formSignUp.password22.$invalid && $scope.password22Blurred;
        };
        /*
        LOG IN
        */
        $scope.emailBlurred = false;
        $scope.passwordBlurred = false;
        //email
        $scope.isInvalidEmail = function () {
            return $scope.formForgot.email.$invalid && $scope.emailBlurred;
        };
        //email
        $scope.isInvalidPassword = function () {
            return $scope.formForgot.password.$invalid && $scope.passwordBlurred;
        };

        $scope.validateInputs = function() {
            // Kiểm tra các ô input
            if (!$scope.username || !$scope.email) {
                return false; // Nếu có ô input nào chưa được điền, trả về false
            }
            return true; // Nếu tất cả các ô input đều được điền, trả về true
        };

        $scope.showAlert = function() {
                // Nếu dữ liệu hợp lệ, hiển thị cảnh báo thành công và thực hiện hành động tiếp theo
                Swal.fire({
                    title: 'Thông Báo từ hệ thống',
                    text: 'Đã gửi link vào gmail, Vui lòng nhấn vào để link để lấy lại mật khẩu',
                    icon: 'success',
                    confirmButtonText: 'Xác nhận',
                    allowOutsideClick: false
                })
        };
    })

    .controller("accountForgotXacNhanCtrl", function ($scope, $rootScope, $location, $timeout){
        $scope.showAlert = function() {
            if ($scope.newPassword !== $scope.confirmPassword) {
                    Swal.fire({
                    title: 'Thông Báo từ hệ thống',
                    text: 'Hai mật khẩu của bạn xác nhận chưa giống nhau, Vui lòng kiểm tra lại mật khẩu',
                    icon: 'error', // Có thể thay đổi icon thành 'error', 'warning', 'info', hoặc 'question'
                    confirmButtonText: 'Xác nhận',
                    allowOutsideClick: false
                });
                return;
            }else{
                Swal.fire({
                title: 'Thông Báo từ hệ thống',
                text: 'Bạn đã thay đổi mật khẩu thành công.',
                icon: 'success', // Có thể thay đổi icon thành 'error', 'warning', 'info', hoặc 'question'
                confirmButtonText: 'Xác nhận',
                allowOutsideClick: false
            }).then((result) => {
                // Nếu người dùng nhấn OK, chuyển trang
                if (result.isConfirmed) {
                    window.location.href = "swappa/assests/html/acc_login.html"; // Thay đổi URL để chuyển trang
                    }
                });
            }
        }
    })

    .controller("AdminController",function($scope, $rootScope, $location, $timeout){
        $scope.showAlert = function(){
            Swal.fire({
                title: 'Thông Báo từ hệ thống',
                text: 'Tài khoản của bạn đã được thêm thành công',
                icon: 'success', // Có thể thay đổi icon thành 'error', 'warning', 'info', hoặc 'question'
                confirmButtonText: 'Xác nhận',
                allowOutsideClick: false

            });
            return;
        }

        $scope.showEdit = function(){
            Swal.fire({
                title: 'Thông Báo từ hệ thống',
                text: 'Tài khoản của bạn đã được chỉnh sửa thành công',
                icon: 'success', // Có thể thay đổi icon thành 'error', 'warning', 'info', hoặc 'question'
                confirmButtonText: 'Xác nhận',
                allowOutsideClick: false
            });
            return;
        }


        $scope.showDelete = function(){
            Swal.fire({
                title: 'Thông Báo từ hệ thống',
                text: 'Bạn có chắc là xóa tài khoản này',
                icon: 'warning', // Có thể thay đổi icon thành 'error', 'warning', 'info', hoặc 'question'
                confirmButtonText: 'Xác nhận',
                allowOutsideClick: true
            });
            return;
        }
    })
    
    
    