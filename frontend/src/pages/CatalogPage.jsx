import { useProducts } from "../hooks/useProducts";
import ProductCard from "../components/ProductCard";

const CatalogPage = () => {
  const { products } = useProducts();

  return (
    <div>
      <h1>Catalogo</h1>

      {products.map((product) => (
        <ProductCard key={product.id} product={product} />
      ))}
    </div>
  );
};

export default CatalogPage;