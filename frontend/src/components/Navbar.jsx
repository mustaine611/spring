import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <nav style={{ padding: "10px", borderBottom: "1px solid #ccc" }}>
      <Link to="/" style={{ marginRight: "15px" }}>
        Catalogo
      </Link>

      <Link to="/orders">
        Mis pedidos
      </Link>
    </nav>
  );
};

export default Navbar;