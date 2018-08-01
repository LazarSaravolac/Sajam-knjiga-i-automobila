var app = angular.module("Wafepa", ["ngRoute"]);

app.controller("ctrl", function($scope){
	$scope.message = "Hello JWD 29!";
});


app.controller("kolaCtrl", function($scope, $http, $location){
	var baseUrl="/api/automobili";
	var baseUrlKompanija="/api/kompanije";
	$scope.novoAuto={};
	$scope.novoAuto.iznajmljen=false;
	$scope.auta=[];
	$scope.kompanije=[];
	$scope.page=0;
	$scope.totalPages=1;
	$scope.pretraga={};
	$scope.autaa={};
	
	$scope.bre=true;
var getAuta=function(){
		
		var config = { params: {}};
		
		if($scope.pretraga.model != ""){
			config.params.model = $scope.pretraga.model;
		}
		if($scope.pretraga.godiste != ""){
			config.params.godiste = $scope.pretraga.godiste;
		}
		
		if($scope.pretraga.dajId != ""){
			config.params.dajId = $scope.pretraga.dajId;
		}
		
		if($scope.pretraga.potrosnja != ""){
			config.params.potrosnja = $scope.pretraga.potrosnja;
		}
		
		if($scope.pretraga.velicina != ""){
			config.params.velicina = $scope.pretraga.velicina;
		}
		
		config.params.page = $scope.page;
		
		
		
		var promise=$http.get(baseUrl,config);
		promise.then(
				function uspeh(res){
					$scope.auta=res.data;
					$scope.totalPages = res.headers("totalPages");
					if($scope.auta[0]!=null){
					$scope.autaa.potrosnja=$scope.auta[0].potrosnja;
					for(i=0;i<$scope.auta.length;i++){
						if($scope.auta[i].potrosnja<=$scope.autaa.potrosnja){
							$scope.autaa=$scope.auta[i];
						}
					}
					$scope.bre=true;
				}else{$scope.autaa.potrosnja=0;
					$scope.bre=false;
					$scope.autaa.model="";
					
				}
					},
					function greska(){
						alert("Nesto je poslo po zlu");
					}
		
		);
		
		
	}
	
	getAuta();
	
	var getKompanije = function(){
		$http.get(baseUrlKompanija).then(
			function success(res){
				$scope.kompanije = res.data;
			},
			function error(res){
				alert("Something went wrong");
			}
		);
	}
	
	getKompanije();
	
	$scope.dodaj = function(){
		if($scope.novoAuto.id==null){
		$http.post(baseUrl, $scope.novoAuto).then(
			function success(){
				getAuta();
				console.log("d");
			},
			function error(){
				alert("Could not create.")
			}
		);
		}else{
			$http.put(baseUrl + "/" + $scope.novoAuto.id, $scope.novoAuto).then(
					function success(res){
						//alert("Uspeh");
						getAuta();
						
					},
					function error(res){
						alert("Something went wrong");
					}
				);
		}
		
	
	}
	
	
	$scope.filtriraj=function(){
		$scope.page=0;
		getAuta();
	}
	
	$scope.pretraziMe=function(){
		$scope.pretraga.model="s";
		getAuta();
		$scope.pretraga.model="";
	}
	
	
	
	$scope.go = function(direction){
		$scope.page = $scope.page + direction;
		getAuta();
	}

	$scope.obrisi = function(id){
		var promise = $http.delete(baseUrl + "/" + id);
		promise.then(
			function success(){
				getAuta();
			},
			function error(){
				alert("Could not delete the festivali.");
			}
		);
	}
	

	$scope.goToEdit = function(id){
//		$location.path("/kola/edit/" + id);
		$http.get(baseUrl + "/" + id).then(
				function success(res){
					$scope.novoAuto = res.data;
				},
				function error(res){
					alert("Something went wrong");
				}
			);
	}
	
	
	$scope.iznajmi=function(id){
		$http.post(baseUrl + "/" + id + "/iznajmi", id).then(
				function success(){
					getAuta();
				},
				function error(){
					alert("Could not create.")
				}
			);	
	}
	
	$scope.reset=function(){
		$scope.pretraga.model="";
		$scope.pretraga.godiste="";
		$scope.pretraga.potrosnja="";
		$scope.pretraga.dajId="";
		$scope.filtriraj();
	}
});



app.controller("editKolaCtrl", function($scope, $routeParams, $http, $location){
	
	//console.log($routeParams);
	var kolaId = $routeParams.aid;
	var baseUrl = "/api/automobili";
	
	$scope.novoAutoE = {};

	var getActivity = function(){
		
		$http.get(baseUrl + "/" + kolaId).then(
			function success(res){
				$scope.novoAutoE = res.data;
			},
			function error(res){
				console.log("Something went wrong!");
			}	
		);
	}
	
	getActivity();
	
	$scope.izmeni = function(){
		$http.put(baseUrl + "/" + kolaId, $scope.novoAutoE).then(
			function success(res){
				//alert("Uspeh");
				
				$location.path("/");
			},
			function error(res){
				alert("Something went wrong");
			}
		);
	}
	
});








app.controller("festivaliCtrl", function($scope, $http, $location){
	var baseUrl = "/api/festivali";
	var baseUrlMesta= "/api/mesto"
	$scope.festivali=[];
	$scope.mesta=[];
	$scope.novFestival={};
	$scope.kupitiFestival={};
	
	$scope.novFestival.id="";
	$scope.novFestival.naziv="";
	$scope.novFestival.organizator="";
	$scope.novFestival.datumPocetka="";
	$scope.novFestival.cenaKarte="";
	$scope.novFestival.kolicinaNaStanju="";
	
	$scope.pretraga={};
	$scope.pretraga.naziv="";
	$scope.pretraga.maxCenaKarte="";
	$scope.pretraga.mestoNaziv="";
	
	$scope.pageNum=0;
	
	$scope.go = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getStandovi();
	}
	
	$scope.kupi = function(id){
		
		var promise = $http.get(baseUrl + "/" + id);
		promise.then(
			function success(res){
				//console.log(res);
				$scope.kupitiFestival = res.data;
				console.log($scope.kupitiFestival);
				if($scope.kupitiFestival.kolicinaNaStanju>0){
				$scope.kupitiFestival.kolicinaNaStanju=$scope.kupitiFestival.kolicinaNaStanju-1;
				$http.put(baseUrl + "/" + id, $scope.kupitiFestival).then(
						function success(res){
							//alert("Uspeh");
							getFestivali();
							console.log($scope.festivali);
							$location.path("/festivali");
						},
						function error(res){
							alert("Something went wrong");
						}
					);
				}
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}

	$scope.kupqi=function(id){
		
		$scope.kupitiFestival=getfestivalKupiti(id);
		console.log($scope.kupitiFestival.kolicinaNaStanju);
		$scope.kupitiFestival.kolicinaNaStanju=$scope.kupitiFestival.kolicinaNaStanju-1;
		$http.put(baseUrl + "/" + id, $scope.kupitiFestival).then(
				function success(res){
					//alert("Uspeh");
					getFestivali();
					console.log($scope.festivali);
					$location.path("/festivali");
				},
				function error(res){
					alert("Something went wrong");
				}
			);
	}
	
	var getFestivali=function(){
		
		var config = { params: {}};
		
		if($scope.pretraga.naziv != ""){
			config.params.naziv = $scope.pretraga.naziv;
		}
		if($scope.pretraga.maxCenaKarte != ""){
			config.params.maxCenaKarte = $scope.pretraga.maxCenaKarte;
		}
		
		if($scope.pretraga.mestoNaziv != ""){
			config.params.mestoNaziv = $scope.pretraga.mestoNaziv;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		
		
		var promise=$http.get(baseUrl,config);
		promise.then(
				function uspeh(res){
					$scope.festivali=res.data;
					$scope.totalPages = res.headers("totalPages");
					console.log($scope.totalPages);
					},
					function greska(){
						alert("Nesto je poslo po zlu");
					}
		
		);
		
		
	}
	
	getFestivali();
	
	var getMesta = function(){
		$http.get(baseUrlMesta).then(
			function success(res){
				$scope.mesta = res.data;
			},
			function error(res){
				alert("Something went wrong");
			}
		);
	}
	
	getMesta();
	
	$scope.dodaj = function(){
		$http.post(baseUrl, $scope.novFestival).then(
			function success(){
				getFestivali();
				console.log($scope.novFestival);
			},
			function error(){
				alert("Could not create.")
			}
		);
	}
	
	$scope.delete = function(id){
		var promise = $http.delete(baseUrl + "/" + id);
		promise.then(
			function success(){
				getFestivali();
			},
			function error(){
				alert("Could not delete the festivali.");
			}
		);
	}
	

	$scope.goToEdit = function(id){
		$location.path("/festivali/edit/" + id);
	}
});

app.controller("activitiesCtrl", function($scope, $http, $location){
	
	var baseUrl = "/api/activities";
	$scope.activities = [];
	
	$scope.newActivity = {};
	$scope.newActivity.name = "";
	
	var getActivities = function(){
		
		var promise = $http.get(baseUrl);
		promise.then(
			function success(res){
				//console.log(res);
				$scope.activities = res.data;
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	//console.log("Test");
	
	getActivities();
	
	$scope.goToEdit = function(id){
		$location.path("/activities/edit/" + id);
	}
	
	$scope.add = function(){
		var promise = $http.post(baseUrl, $scope.newActivity);
		promise.then(
			function success(res){
				getActivities();
			},
			function error(){
				console.log("Something went wrong!");
			}
		)
	}
	
	$scope.delete = function(id){
		var promise = $http.delete(baseUrl + "/" + id);
		promise.then(
			function success(){
				getActivities();
			},
			function error(){
				alert("Could not delete the activity.");
			}
		);
	}
});

app.controller("editFestivalCtrl", function($scope, $routeParams, $http, $location){
	var festivalId = $routeParams.aid;
	var baseUrl = "/api/festivali";
	
	var baseUrlPrivremena="/api/festivali/privremena"
	$scope.festivaliEdit=[];
	$scope.festival = {};
	
	var getFestival = function(){
		
		$http.get(baseUrl + "/" + festivalId).then(
			function success(res){
				$scope.festival = res.data;
			},
			function error(res){
				console.log("Something went wrong!");
			}	
		);
	}
	
	getFestival();
	
	$scope.edit = function(){
		$http.put(baseUrl + "/" + festivalId, $scope.festival).then(
			function success(res){
				//alert("Uspeh");
				getFestival();
				console.log($scope.festival);
				$location.path("/festivali");
			},
			function error(res){
				alert("Something went wrong");
			}
		);

}
	$scope.add = function(){
		getFestival();
		$scope.festivaliEdit.push($scope.festival);
	}
});






app.controller("editActivityCtrl", function($scope, $routeParams, $http, $location){
	
	//console.log($routeParams);
	var activityId = $routeParams.aid;
	var baseUrl = "/api/activities";
	
	$scope.activity = {};
	$scope.activity.name = "";
	
	var getActivity = function(){
		
		$http.get(baseUrl + "/" + activityId).then(
			function success(res){
				$scope.activity = res.data;
			},
			function error(res){
				console.log("Something went wrong!");
			}	
		);
	}
	
	getActivity();
	
	$scope.edit = function(){
		$http.put(baseUrl + "/" + activityId, $scope.activity).then(
			function success(res){
				//alert("Uspeh");
				$location.path("/activities");
			},
			function error(res){
				alert("Something went wrong");
			}
		);
	}
	
});


app.controller("standoviCtrl", function($scope, $http){
	
	var baseUrl = "/api/standovi";
	var baseUrlSajmovi = "/api/sajmovi";
	
	$scope.standovi = [];
	$scope.sajmovi = [];
	
	$scope.noviStand = {};
	$scope.noviStand.zakupac = "";
	$scope.noviStand.povrsina = "";
	$scope.noviStand.sajamId = "";
	
	$scope.pPretrage = {};
	$scope.pPretrage.zakupac = "";
	$scope.pPretrage.minP = "";
	$scope.pPretrage.maxP = "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	var getStandovi = function(){
		
		var config = { params: {}};
		
		if($scope.pPretrage.zakupac != ""){
			config.params.zakupac = $scope.pPretrage.zakupac;
		}
		if($scope.pPretrage.minP != ""){
			config.params.minP = $scope.pPretrage.minP;
		}
		if($scope.pPretrage.maxP != ""){
			config.params.maxP = $scope.pPretrage.maxP;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		
		$http.get(baseUrl, config).then(
			function success(res){
				$scope.standovi = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Something went wrong.");
			}
		);
	}
	
	getStandovi();
	
	var getSajmovi = function(){
		$http.get(baseUrlSajmovi).then(
			function success(res){
				$scope.sajmovi = res.data;
			},
			function error(res){
				alert("Something went wrong");
			}
		);
	}
	
	getSajmovi();
	
	$scope.dodaj = function(){
		$http.post(baseUrl, $scope.noviStand).then(
			function success(){
				getStandovi();
			},
			function error(){
				alert("Could not create.")
			}
		);
	}
	
	$scope.pretraga = function(){
		$scope.pageNum = 0;
		getStandovi();
	}
	
	$scope.go = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getStandovi();
	}
});

app.controller("knjigeCtrl", function($scope, $http, $location){
	var baseUrl= "/api/knjige";
	var baseUrlIzdavaci= "/api/izdavaci";
	$scope.knjige=[];
	$scope.novaKnjiga={};
	$scope.izdavaci=[];
	$scope.novaKnjiga.brojGlasova=0;
	$scope.pretraga={};
	
	$scope.page=0;
	$scope.totalPages=1;
	$scope.knjigee={};
var getKnjige = function(){
	var config = { params: {}};
	
	if($scope.pretraga.naziv != ""){
		config.params.naziv = $scope.pretraga.naziv;
	}
	if($scope.pretraga.pisac != ""){
		config.params.pisac = $scope.pretraga.pisac;
	}
	
	if($scope.pretraga.minGlasova != ""){
		config.params.minGlasova = $scope.pretraga.minGlasova;
	}
	
	config.params.page = $scope.page;
	
	
		$http.get(baseUrl,config).then(
			function success(res){
				$scope.knjige = res.data;
				$scope.totalPages = res.headers("totalPages");
				
				
				$scope.knjigee.brojGlasova=$scope.knjige[0].brojGlasova;
				console.log($scope.knjigee.brojGlasova);
				for(i=0;i<$scope.knjige.length;i++){
					if($scope.knjige[i].brojGlasova>=$scope.knjigee.brojGlasova){
						$scope.knjigee=$scope.knjige[i];
					}
				}
			},
			function error(res){
				console.log("Something went wrong!");
			}	
		);
	}
	
getKnjige();
	
var getIzdavaci = function(){
	
	$http.get(baseUrlIzdavaci).then(
		function success(res){
			$scope.izdavaci = res.data;
		},
		function error(res){
			console.log("Something went wrong!");
		}	
	);
}

getIzdavaci();

$scope.dodaj=function(){
	$http.post(baseUrl,$scope.novaKnjiga).then(
			function success(res){
				getKnjige();
				getIzdavaci();
			},
			function error(res){
				console.log("Something went wrong!");
			}	
	);
}

$scope.go = function(direction){
	$scope.page = $scope.page + direction;
	getKnjige();
}


$scope.filtriraj=function(){
	$scope.page=0;
	getKnjige();
}


$scope.goToEdit=function(id){
	 $location.path('/knjige/edit/' + id);
}


$scope.obrisi=function(id){
	$http.delete(baseUrl + "/" + id).then(
			function success(res){
				getKnjige();
				getIzdavaci();
			},
			function error(res){
				console.log("Something went wrong!");
			}	
	);
}

$scope.kupi=function(id){
	$http.post(baseUrl + "/" + id + "/kupiKnjigu").then(
			function success(res){
				getKnjige();
				getIzdavaci();
			},
			function error(res){
				console.log("Something went wrong!");
			}	
	);
	
}

});

app.controller("editKnjigeCtrl", function($scope, $routeParams, $http, $location){
	
	//console.log($routeParams);
	var knjigeId = $routeParams.aid;
	var baseUrl = "/api/knjige";
	
	$scope.novaKnjigaEdit = {};

	var getActivity = function(){
		
		$http.get(baseUrl + "/" + knjigeId).then(
			function success(res){
				$scope.novaKnjigaEdit = res.data;
			},
			function error(res){
				console.log("Something went wrong!");
			}	
		);
	}
	
	getActivity();
	
	$scope.izmena = function(){
		$http.put(baseUrl + "/" + knjigeId, $scope.novaKnjigaEdit).then(
			function success(res){
				//alert("Uspeh");
				$location.path("/knjige");
			},
			function error(res){
				alert("Something went wrong");
			}
		);
	}
	
});






app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/partial/kola.html',
		})
		.when('/kola', {
			templateUrl : '/app/html/partial/kola.html',
		})
		.when('/kola/edit/:aid', {
			templateUrl : '/app/html/partial/edit-kola.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);
