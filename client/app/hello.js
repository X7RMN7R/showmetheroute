function Hello($scope, $http) {
  $http.get('http://127.0.01:8090/greeting').
  success(function(data) {
            $scope.greeting = data;
          });
}
