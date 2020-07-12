import React, {useEffect, useState} from 'react';
import ProductsComponent from "./ProductsComponent";
import {createNewProduct, getAllProducts} from "../../../client/BackendApiClient";

const ProductsContainer = () => {
  const [newProductName, setNewProductName] = useState("");
  const [errors, setErrors] = useState(null);
  const [newProductPrice, setNewProductPrice] = useState("");
  const [isModalOpen, setModalOpen] = useState(false);
  const [products, setProducts] = useState([]);

  useEffect(() => {
    getAllProducts().then((res) => {
      console.log(res.data);
      setProducts(mapData(res.data));
    })
  }, [])

  function createData(id, name, price) {
    return {id, name, price};
  }

  const mapData = (productsData) => (
    productsData.map((product) => (
      createData(product.id, product.name, product.price)
    ))
  )

  const saveNewProduct = () => {
    createNewProduct(newProductName, newProductPrice).then(() => {
      getAllProducts()
        .then((res) => {
          console.log(res.data);
          setProducts(mapData(res.data));
          setModalOpen(false);
        })
    })
      .catch((error) => {
        console.log(error);
        const {response: {data}} = error;
        setErrors(data);
      })
  }

  return (
    <ProductsComponent
      products={products}
      newProductName={newProductName}
      setNewProductName={setNewProductName}
      newProductPrice={newProductPrice}
      setNewProductPrice={setNewProductPrice}
      isModalOpen={isModalOpen}
      setModalOpen={setModalOpen}
      saveNewProduct={saveNewProduct}
      errors={errors}
    />
  );
};

ProductsContainer.propTypes = {};

export default ProductsContainer;
