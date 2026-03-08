import { BrowserRouter, Routes, Route } from "react-router-dom";
import CatalogPage from "../pages/CatalogPage";
import OrdersPage from "../pages/OrdersPage";
import Navbar from "../components/Navbar";

const AppRouter = () => {
  return (
    <BrowserRouter>

      <Navbar />

      <Routes>
        <Route path="/" element={<CatalogPage />} />
        <Route path="/orders" element={<OrdersPage />} />
      </Routes>

    </BrowserRouter>
  );
};

export default AppRouter;