import { apiClient } from "../utils/apiClient";

export const getProducts = () => {
  return apiClient("/products");
};