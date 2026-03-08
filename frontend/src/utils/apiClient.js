const API_BASE_URL = "http://localhost:8080";

export const apiClient = async (endpoint, options = {}) => {
  const response = await fetch(`${API_BASE_URL}${endpoint}`, {
    headers: {
      "Content-Type": "application/json",
    },
    ...options,
  });

  if (!response.ok) {
    throw new Error("Error en la petición");
  }

  return response.json();
};