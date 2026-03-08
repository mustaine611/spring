import { BrowserRouter, Routes, Route } from "react-router-dom";
import CatalogPage from "../pages/CatalogPage";

const AppRouter = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<CatalogPage />} />
      </Routes>
    </BrowserRouter>
  );
};

export default AppRouter;