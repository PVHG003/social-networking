import { Route, Routes } from "react-router-dom";

import AuthLayout from "@/layouts/AuthLayout";

import LoginPage from "@/pages/auth/LoginPage";
import RegisterPage from "@/pages/auth/RegisterPage";
import MainLayout from "@/layouts/MainLayout";
import HomePage from "@/pages/home/HomePage";

export default function AppRoutes() {
  const authenticated = true; // Replace with context/auth check

  return (
    <Routes>
      {/* Public Routes */}
      <Route element={<AuthLayout />}>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
      </Route>
      <Route element={<MainLayout />}>
        <Route index element={<HomePage />} />
        {/* <Route path="/feed" element={<FeedPage />} /> */}

        {/* <Route path="/profile/:username" element={<ProfilePage />} /> */}
        {/* <Route path="/profile/:username/followers" element={<FollowersPage />} /> */}
        {/* <Route path="/profile/:username/following" element={<FollowingPage />} /> */}
        {/* <Route path="/profile/update" element={<ProfileUpdatePage />} /> */}

        {/* <Route path="/auth/update-profile" element={<UpdateProfilePage />} /> */}

        {/* <Route path="/chat" element={<ChatPage />} /> */}
        {/* <Route path="/chat/:conversationId" element={<ConversationPage />} /> */}
      </Route>

      {/* <Route path="*" element={<NotFoundPage />} />  */}
    </Routes>
  );
}
