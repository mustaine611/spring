import { BrowserRouter, Routes, Route } from "react-router-dom";
import CatalogPage from "../pages/CatalogPage";
import OrdersPage from "../pages/OrdersPage";
import Navbar from "../components/Navbar";
import LoginPage from "../pages/LoginPage";
import RegisterPage from "../pages/RegisterPage";
import RecoverPage from "../pages/RecoverPage";

const AppRouter = () => {
  return (
    <BrowserRouter>

      <Navbar />

      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/recover" element={<RecoverPage />} />
        <Route path="/" element={<CatalogPage />} />
        <Route path="/orders" element={<OrdersPage />} />
      </Routes>

    </BrowserRouter>
  );
};

export default AppRouter;