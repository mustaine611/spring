import { useProducts } from "../hooks/useProducts";
import ProductCard from "../components/ProductCard";
import { createOrder } from "../services/ordersService";

const CatalogPage = () => {
  const { products } = useProducts();

  const handleBuy = async (productId) => {
    await createOrder(productId);
    alert("Pedido creado correctamente");
  };

  return (
    <div>
      <h1>Catalogo</h1>

      {products.map((product) => (
        <ProductCard
          key={product.id}
          product={product}
          onBuy={handleBuy}
        />
      ))}
    </div>
  );
};

export default CatalogPage;