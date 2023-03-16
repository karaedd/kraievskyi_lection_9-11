import axios from "axios";

const API_URL = "http://localhost:8080";

class ProductService {

    saveProduct(product) {
        return axios.post(API_URL + "/product", product);
    }

    getAllProduct() {
        return axios.get(API_URL + "/product");
    }

    getProductById(id) {
        return axios.get(API_URL + "/product/" + id);
    }

    deleteProduct(id) {
        return axios.get(API_URL + "/product/" + id);
    }

    editProduct(product) {
        return axios.post(API_URL + "/product/" + product.id, product);
    }

}

export default new ProductService();
