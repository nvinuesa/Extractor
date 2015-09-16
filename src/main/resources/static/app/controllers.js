angular.module("myApp.controllers", [])
.controller("AppController", function($scope, $http, $window) {
	$http.get('/api').success(function (response) {
		$scope.items = response;
	});	
	$scope.extraction = {};
	$scope.extraction.atlas = '';
	$scope.extraction.anatcon = [];
	$scope.extraction.boldcon = [];
	$scope.extraction.corranatcon = [];
	$scope.extraction.corrboldcon = [];
	$scope.extraction.roi = [];
	$scope.extraction.bisroi = [];
	$scope.extraction.custom = '';
	$scope.extraction.customname = '';
	$scope.extraction.asymmetry = false;
	
	$scope.toggledAllRoi = false;
	
	$scope.update = function (x) {
		$scope.extraction = {};
		$scope.extraction.atlas = x.atlas;
		$scope.extraction.anatcon = [];
		$scope.extraction.boldcon = [];
		$scope.extraction.corranatcon = [];
		$scope.extraction.corrboldcon = [];
		$scope.extraction.roi = [];
		$scope.extraction.bisroi = [];
		$scope.extraction.custom = '';
		$scope.extraction.customname = '';
		$scope.extraction.asymmetry = false;
	}
	
	$scope.updateCon = function () {
		$scope.extraction.anatcon = [];
		$scope.extraction.boldcon = [];
		$scope.extraction.corranatcon = [];
		$scope.extraction.corrboldcon = [];	
		$scope.extraction.roi = [];
		$scope.extraction.bisroi = [];
		$scope.extraction.custom = '';
		$scope.extraction.customname = '';
		$scope.extraction.asymmetry = false;
	}
	
	$scope.updateRoi = function () {
		$scope.extraction.roi = [];
	}
	
	$scope.toggleAllRoi = function (atlas) {
		$scope.toggledAllRoi = !$scope.toggledAllRoi;
		if ($scope.extraction.asymmetry) {
			angular.forEach($scope.items[atlas].roiAsym, function(x) {
				if ($scope.toggledAllRoi) {
					$scope.extraction.roi.push(x);					
				}
				else {
					$scope.extraction.roi = [];
				}
			});
		} 
		else {
			angular.forEach($scope.items[atlas].roi, function(x) {
				if ($scope.toggledAllRoi) {
					$scope.extraction.roi.push(x);
					$scope.extraction.bisroi.push(x);
				}
				else {
					$scope.extraction.roi = [];
					$scope.extraction.bisroi = [];
				}
			});
		}			
	}
	
	$scope.toggleLhRoi = function (atlas) {
		$scope.extraction.roi = [];
		$scope.extraction.bisroi = [];
		angular.forEach($scope.items[atlas].roi, function(x) {
			if ($scope.items[atlas].laterality[$scope.items[atlas].roi.indexOf(x)] == 1) {
				$scope.extraction.roi.push(x);
				$scope.extraction.bisroi.push(x);
			}
		});	
	}
	
	$scope.toggleRhRoi = function (atlas) {
		$scope.extraction.roi = [];
		$scope.extraction.bisroi = [];
		angular.forEach($scope.items[atlas].roi, function(x) {
			if ($scope.items[atlas].laterality[$scope.items[atlas].roi.indexOf(x)] == 2) {
				$scope.extraction.roi.push(x);
				$scope.extraction.bisroi.push(x);
			}
		});	
	}
	
	$scope.toggleSelAnatCon = function toggleSelAnatCon(x) {
		var idx = $scope.extraction.anatcon.indexOf(x);
		if (idx > -1) {
			$scope.extraction.anatcon.splice(idx, 1);
		}
		else {
			$scope.extraction.anatcon.push(x);
		}
	};
	
	$scope.toggleSelBoldCon = function toggleSelBoldCon(x) {
		var idx = $scope.extraction.boldcon.indexOf(x);
		if (idx > -1) {
			$scope.extraction.boldcon.splice(idx, 1);
		}
		else {
			$scope.extraction.boldcon.push(x);
		}
	};
	
	$scope.toggleSelCorrAnatCon = function toggleSelCorrAnatCon(x) {
		var idx = $scope.extraction.corranatcon.indexOf(x);
		if (idx > -1) {
			$scope.extraction.corranatcon.splice(idx, 1);
		}
		else {
			$scope.extraction.corranatcon.push(x);
		}
	};
	
	$scope.toggleSelCorrBoldCon = function toggleSelCorrBoldCon(x) {
		var idx = $scope.extraction.corrboldcon.indexOf(x);
		if (idx > -1) {
			$scope.extraction.corrboldcon.splice(idx, 1);
		}
		else {
			$scope.extraction.corrboldcon.push(x);
		}
	};
	
	$scope.toggleSelectionRoi = function toggleSelectionRoi(x,atlas) {		
		var idx = $scope.extraction.roi.indexOf(x);
		if (idx > -1) {
			$scope.extraction.roi.splice(idx, 1);
		}
		else {
			$scope.extraction.roi.push(x);
		}
	};	
		
	$scope.toggleSelectionRoiBis = function toggleSelectionRoiBis(x,atlas) {			
		var idx = $scope.extraction.bisroi.indexOf(x);		
		if (idx > -1) {
			$scope.extraction.bisroi.splice(idx, 1);
		}
		else {
			$scope.extraction.bisroi.push(x);
		}
	};		
	
	$scope.postExtractionJSON = function (atlas) {
		$http.post('/api', $scope.extraction).
		success(function(data, status, headers, config) {
			$window.open('/api/downloadFile/'+data.id);
		}).
		error(function(data, status, headers, config) {
			
		});
	};

	$scope.getConanat = function(atlas) {
		return $scope.items[atlas].anatcon;  
	};
	$scope.getConbold = function(atlas) {
		return $scope.items[atlas].boldcon;  
	};
	$scope.getConcorranat = function(atlas) {
		return $scope.items[atlas].corranatcon;  
	};
	$scope.getConcorrbold = function(atlas) {
		return $scope.items[atlas].corrboldcon;  
	};
	$scope.getRoi = function(atlas) {
		if ($scope.extraction.asymmetry) {
			return $scope.items[atlas].roiAsym;  
		} 
		else {
			return $scope.items[atlas].roi;  
		}		
	};	
});		