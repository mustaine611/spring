const ProductCard = ({ product, onBuy }) => {
  return (
    <div>
      <img src={product.imageUrl} alt={product.name} width="150" />
      <h3>{product.name}</h3>

      <button onClick={() => onBuy(product.id)}>
        Comprar
      </button>
    </div>
  );
};

export default ProductCard;