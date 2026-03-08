import { useEffect, useState } from "react";
import { getProducts } from "../services/catalogService";

export const useProducts = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    getProducts().then((data) => {
      console.log("Productos recibidos del backend:", data);
      setProducts(data);
    });
  }, []);

  return { products };
};