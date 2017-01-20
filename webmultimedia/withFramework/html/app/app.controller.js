angular.module("app").controller("appController", function($scope, httpService, LocalStorageService) {

    $scope.media = [];
    $scope.loginUserName = "";
    $scope.loginPassword = "";

    $scope.signupUserName = "";
    $scope.signupPassword = "";

    if(LocalStorageService.load("userId") !== undefined) {
        $scope.currentUser = LocalStorageService.load("userName");
        $scope.showLogin = false;
    }
    else {
        $scope.showLogin = true;
    }





    function addItem(it) {
        $scope.media.push(it);
        setTimeout(function() {
            $(".materialboxed").materialbox();
        }, 2000);


    }

    /*addItem(new PictureElement(
        "Alfa Romeo",
        "Alfa Romeo Automobiles S.p.A. is an Italian car manufacturer. Founded as A.L.F.A., translating to Anonymous Lombard Automobile Factory in English) on June 24, 1910, in Milan, the company has been involved in car racing since 1911.",
        "data/WP_20160804_07_44_49_Rich.jpg"
    ));

    addItem(new AudioElement(
        "Dance Song 97",
        "Dance Song 97 by Sleater Kinney on the Album Dig me out (1997)",
        "data/sleaterkinney-digmeout-1425.jpg",
        "data/sp1105-12_dance_song_97.mp3"
    ));

    addItem(new PictureElement(
        "Nikon Cameras",
        "A graphic showing the development of different Nikon Camera Models",
        "http://www.bythom.com/Images/lineage-chart.jpg"
    ));

    addItem(new VideoElement(
        "Sample Video",
        "This is a sample Video",
        "data/sample1.mp4"
    ));*/


    $scope.isPhoto = function(elm) {
        return elm instanceof PictureElement;
    };

    $scope.isAudio = function(elm) {
        return elm instanceof AudioElement;
    };

    $scope.isVideo = function(elm) {
        return elm instanceof VideoElement;
    };

    $scope.login = function() {
        httpService.post("user/login", {
                userName: $scope.loginUserName,
                userPassword: $scope.loginPassword
            },
            function(data) {
                $("#loginModal").closeModal();
                LocalStorageService.store("userId", data);
                LocalStorageService.store("userName", $scope.loginUserName);
                $scope.currentUser = $scope.loginUserName;
                $scope.loginUserName = "";
                $scope.loginPassword = "";
                $scope.showLogin = false;

            },
            function(data) {
                $scope.loginError = data.data.message;
            });
    };

    $scope.signup = function() {
        if ($scope.signupPassword === $scope.signupPasswordRep) {
            httpService.post("user/add", {
                    userName: $scope.signupUserName,
                    userPassword: $scope.signupPassword
                },
                function(data) { //success
                    $("#signupModal").closeModal();
                    LocalStorageService.store("userId", data);
                    LocalStorageService.store("userName", $scope.signupUserName);
                    $scope.currentUser = $scope.signupUserName;
                    $scope.signupUserName = "";
                    $scope.signupPassword = "";
                    $scope.showLogin = false;

                },
                function(data) { //error
                    $scope.error = data.data.message;
                }
            );

        } else {
            $scope.signupError = "Passwords are not equal";
        }
    };

    $scope.logout = function() {
        $scope.showLogin = true;
        LocalStorageService.del("userId");
        LocalStorageService.del("userName");
    };

    /*
    $scope.myformdata = new FormData();
    $scope.getTheFiles = function($files) {
        angular.forEach($files, function(value, key) {
            $scope.myformdata.append(key, value);
            console.log(key)
            console.log(value);
        });
    };
    */

    $scope.uploadFile = function() {

        $scope.myformdata.append("title", $scope.newFileTitle);
        $scope.myformdata.append("license", $scope.newFileLicense);

        console.log($scope.myformdata);
        let request = {
            method: 'POST',
            url: 'files/upload/',
            data: $scope.myformdata,
            headers: {
                'Content-Type': undefined
            }
        };

        httpService.http(request)
            .success(function(data) {
                console.log(data);
            });
    };

    console.log("g a m");
    httpService.get("media/all", function(data) {
        console.log("get all media");
        for (let d of data.data) {
            if (d.mediaType === "IMAGE") {
                addItem(new PictureElement(
                    d.mediaTitle,
                    d.mediaDescription,
                    d.mediaResourcePath,
                    d.mediaLicense
                ));
            } else if (d.mediaType === "AUDIO") {
                addItem(new AudioElement(
                    d.mediaTitle,
                    d.mediaDescription,
                    d.mediaPicturePath,
                    d.mediaResourcePath,
                    d.mediaLicense
                ));
            } else if (d.mediaType === "VIDEO") {
                addItem(new VideoElement(
                    d.mediaTitle,
                    d.mediaDescription,
                    d.mediaResourcePath,
                    d.mediaLicense
                ));
            }
        }
        setTimeout(initImageViewer, 1000);
    }, function(error) {
        console.log(error);
    });

    httpService.get("license/all", function(data) {
	$scope.licenses = data.data;
	setTimeout(function() {
	    $('select').material_select();
	},1000);
    }, function(error) {
	console.log(error);
    });

}).directive('ngFiles', function($parse) {

    function fn_link(scope, element, attrs) {
        var onChange = $parse(attrs.ngFiles);
        element.on('change', function(event) {
            onChange(scope, {
                $files: event.target.files
            });
        });
    };

    return {
        link: fn_link
    }
});
