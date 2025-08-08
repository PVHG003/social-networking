import AuthLayout from "@/layouts/AuthLayout";
import CompleteProfilePage from "@/pages/CompleteProfilePage";
import LoginPage from "@/pages/LoginPage";
import RegisterPage from "@/pages/RegsiterPage";
import { Routes, Route } from "react-router-dom";

export const AppRoutes = () => {
  return (
    <Routes>
      {/* Public Routes */}
      <Route element={<AuthLayout />}>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/complete-profile" element={<CompleteProfilePage />} />
      </Route>
    </Routes>
  );
};
