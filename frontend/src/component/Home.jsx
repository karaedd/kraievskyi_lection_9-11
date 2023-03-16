import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import productService from "../service/product.service";

const Home = () => {
    const [productList, setProductList] = useState([]);
    const [msg, setMsg] = useState("");
    useEffect(() => {
        init();
    }, []);

    const init = () => {
        productService
            .getAllProduct()
            .then((res) => {
                setProductList(res.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const deleteProduct = (id) => {
        productService
            .deleteProduct(id)
            .then((res) => {
                setMsg("Delete Sucessfully");
                init();
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <>
            <div className="container mt-3">
                <div className="row">
                    <div className="col-md-12">
                        <div className="card">
                            <div className="card-header fs-3 text-center">
                                All Product List
                                {msg && <p className="fs-4 text-center text-success">{msg}</p>}
                            </div>

                            <div className="card-body">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col">Sl No</th>
                                        <th scope="col">Product Name</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Category</th>
                                        <th scope="col">Date Manufacture</th>
                                        <th scope="col">Date Expire</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {productList.map((p, num) => (
                                        <tr>
                                            <td>{num + 1}</td>
                                            <td>{p.name}</td>
                                            <td>{p.price}</td>
                                            <td>{p.category}</td>
                                            <td>{p.dateManufacture}</td>
                                            <td>{p.dateExpire}</td>
                                            <td>
                                                <Link to={'editProduct/'+p.id} className="btn btn-sm btn-primary">
                                                    Edit
                                                </Link>
                                                <button
                                                    onClick={() => deleteProduct(p.id)}
                                                    className="btn btn-sm btn-danger ms-1"
                                                >
                                                    Delete
                                                </button>
                                            </td>
                                        </tr>
                                    ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};

export default Home;
