import { apiClient } from "../utils/apiClient";


export const createOrder = (productId) => {
  return apiClient("/orders", {
    method: "POST",
    body: JSON.stringify({ productId }),
  });
};

export const getOrders = () => {
  return apiClient("/orders");
};