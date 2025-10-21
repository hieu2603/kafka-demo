import axios from "axios";

const API_BASE = "http://localhost:8081/api/auth";

export const registerSync = async (user) => {
  return axios.post(`${API_BASE}/register-sync`, user);
};

export const registerAsync = async (user) => {
  return axios.post(`${API_BASE}/register-async`, user);
};
