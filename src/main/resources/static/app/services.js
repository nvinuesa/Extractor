(function(angular) {
  var ItemFactory = function($resource) {
    return $resource('/api/:id', {id: '@id'}, {
      save : {
        method: 'POST',
        params: { format : 'json'}
      },
      update: {
        method: 'PUT'
      },
      remove: {
        method: 'DELETE'
      }
    });
  };
  
  ItemFactory.$inject = ['$resource'];
  angular.module("myApp.services").factory("Item", ItemFactory);
}(angular));