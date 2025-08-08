import api from "./api";

const authApi = {
  login: async (data: { email: string; password: string }) => {
    const response = await api.post(`/auth/login`, data);
    return response.data;
  },
  register: async (data: {
    email: string;
    password: string;
    confirmPassword?: string;
  }) => {
    const response = await api.post(`/auth/register`, data);
    return response.data;
  },
  logout: async () => {
    const response = await api.post(`/auth/logout`);
    localStorage.removeItem("token");
    return response.data;
  },
  completeProfile: async (data: FormData) => {
    const response = await api.put(`/users/me`, data, {
      headers: {
        "Content-Type": "multipart/form-data",
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    });
    return response.data;
  },
};

export default authApi;
