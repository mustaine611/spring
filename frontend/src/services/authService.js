import { apiClient } from "../utils/apiClient";

export const register = async (customer) => {
  return apiClient("/auth/register", {
    method: "POST",
    body: JSON.stringify(customer),
  });
};

export const login = async (credentials) => {
  return apiClient("/auth/login", {
    method: "POST",
    body: JSON.stringify(credentials),
  });
};

export const recover = async (data) => {
  return apiClient("/auth/recover", {
    method: "POST",
    body: JSON.stringify(data),
  });
};