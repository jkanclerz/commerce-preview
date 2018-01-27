
var template = [
    '<li>',
        '<span>__id__</span>',
        '<span>__price__</span>',
        '<button>Add to basket</button>',
    '</li>',
].join("");

console.log(template);

var productList = document.getElementsByClassName("products-list")[0];

axios.get('/products')
  .then(function (response) {
        response.data
            .map(function(productData) {
                var tmp = template.replace('__id__', productData.id);
                tmp = tmp.replace('__price__', productData.price);

                return tmp;
            })
            .forEach(function(html) {
                productList.insertAdjacentHTML("beforeend", html)
            })
        ;
  })
  .catch(function (error) {
    console.log(error);
});

