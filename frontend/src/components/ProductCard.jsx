const ProductCard = ({ product }) => {
  return (
    <div>
      <img src={product.imageUrl} alt={product.name} width="150" />
      <h3>{product.name}</h3>
    </div>
  );
};

export default ProductCard;