import PostList from "@/components/PostList";
import AuthLayout from "@/layouts/AuthLayout";
import ProfileLayout from "@/layouts/ProfileLayout";
import CompleteProfilePage from "@/pages/CompleteProfilePage";
import LoginPage from "@/pages/LoginPage";
import RegisterPage from "@/pages/RegsiterPage";
import { Route, Routes } from "react-router-dom";

export const AppRoutes = () => {
  return (
    <Routes>
      {/* Public Routes */}
      <Route element={<AuthLayout />}>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/complete-profile" element={<CompleteProfilePage />} />
      </Route>
      {/* Protected Routes */}
      <Route path="/profile/:handleName" element={<ProfileLayout />}>
        <Route index element={<PostList />} />
      </Route>
    </Routes>
  );
};
