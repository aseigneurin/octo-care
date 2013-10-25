!function(a){"use strict";a.module("angulartics.google.analytics",["angulartics"]).config(["$analyticsProvider",function(a){a.registerPageTrack(function(a){window._gaq&&_gaq.push(["_trackPageview",a]),window.ga&&ga("send","pageview",a)}),a.registerEventTrack(function(a,b){window._gaq&&_gaq.push(["_trackEvent",b.category,a,b.label,b.value]),window.ga&&ga("send","event",b.category,a,b.label,b.value)})}])}(angular),!function(a){"use strict";var b=window.angulartics||(window.angulartics={});b.waitForVendorApi=function(a,c,d){window.hasOwnProperty(a)?d(window[a]):setTimeout(function(){b.waitForVendorApi(a,c,d)},c)},a.module("angulartics",[]).provider("$analytics",function(){var b={pageTracking:{autoTrackFirstPage:!0,autoTrackVirtualPages:!0,basePath:"",bufferFlushDelay:1e3},eventTracking:{bufferFlushDelay:1e3}},c={pageviews:[],events:[]},d=function(a){c.pageviews.push(a)},e=function(a,b){c.events.push({name:a,properties:b})},f={settings:b,pageTrack:d,eventTrack:e},g=function(d){f.pageTrack=d,a.forEach(c.pageviews,function(a,c){setTimeout(function(){f.pageTrack(a)},c*b.pageTracking.bufferFlushDelay)})},h=function(d){f.eventTrack=d,a.forEach(c.events,function(a,c){setTimeout(function(){f.eventTrack(a.name,a.properties)},c*b.eventTracking.bufferFlushDelay)})};return{$get:function(){return f},settings:b,virtualPageviews:function(a){this.settings.pageTracking.autoTrackVirtualPages=a},firstPageview:function(a){this.settings.pageTracking.autoTrackFirstPage=a},withBase:function(b){this.settings.pageTracking.basePath=b?a.element("base").attr("href"):""},registerPageTrack:g,registerEventTrack:h}}).run(["$rootScope","$location","$analytics",function(a,b,c){c.settings.pageTracking.autoTrackFirstPage&&c.pageTrack(b.absUrl()),c.settings.pageTracking.autoTrackVirtualPages&&a.$on("$routeChangeSuccess",function(a,d){if(!d||!(d.$$route||d).redirectTo){var e=c.settings.pageTracking.basePath+b.url();c.pageTrack(e)}})}]).directive("analyticsOn",["$analytics",function(b){function c(a){return["a:","button:","button:button","button:submit","input:button","input:submit"].indexOf(a.tagName.toLowerCase()+":"+(a.type||""))>=0}function d(a){return c(a)?"click":"click"}function e(a){return c(a)?a.innerText||a.value:a.id||a.name||a.tagName}function f(a){return"analytics"===a.substr(0,9)&&-1===["on","event"].indexOf(a.substr(10))}return{restrict:"A",scope:!1,link:function(c,g,h){var i=h.analyticsOn||d(g[0]),j=h.analyticsEvent||e(g[0]),k={};a.forEach(h.$attr,function(a,b){f(a)&&(k[b.slice(9).toLowerCase()]=h[b])}),a.element(g[0]).bind(i,function(){b.eventTrack(j,k)})}}}])}(angular),angular.module("octoCare",["ui.bootstrap","angulartics","angulartics.google.analytics"]).config(["$routeProvider",function($routeProvider){$routeProvider.when("",{templateUrl:"partials/authentication.html",controller:AuthenticationCtrl}).when("/list",{templateUrl:"partials/octo-list.html",controller:OctoListCtrl}).when("/octo/:octoId",{templateUrl:"partials/octo-detail.html",controller:OctoDetailCtrl}).otherwise({redirectTo:""})}]);var AuthenticationCtrl=function($scope,$http){$http.get("api/hexa/current").success(function(data){$scope.hexa=data,void 0!==$scope.hexa.id&&(window.location="#/list")}).error(function(){alert("Failed loading the current Hexa...")})},OctoListCtrl=function($scope,$http){$http.get("api/octo").success(function(data){$scope.octos=data}).error(function(){alert("Failed loading the Octos...")}),$scope.openDetail=function(octo){window.location="#/octo/"+octo.id}},OctoDetailCtrl=function($scope,$routeParams,$http,$modal){$scope.octoId=$routeParams.octoId,$http.get("api/octo/"+$scope.octoId).success(function(data){$scope.octo=data}).error(function(){alert("Failed loading the Octo...")}),$http.get("api/octo/"+$scope.octoId+"/history").success(function(data){$scope.history=data}).error(function(){alert("Failed loading the history...")}),$scope.createEvent=function(){var modalInstance=$modal.open({templateUrl:"partials/new-event.html",controller:NewEventCtrl});modalInstance.result.then(function(result){var data={text:result.text};$http.post("api/octo/"+$scope.octoId+"/history",data).success(function(data){data.highlight="new",console.log(data),$scope.history.push(data)}).error(function(){alert("Failed saving the event...")})})}},NewEventCtrl=function($scope,$modalInstance){$scope.validate=function(){$modalInstance.close(this)},$scope.cancel=function(){$modalInstance.dismiss("cancel")}};